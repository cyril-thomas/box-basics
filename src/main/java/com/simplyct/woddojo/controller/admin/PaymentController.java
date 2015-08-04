package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.helper.payments.BraintreePayments;
import com.simplyct.woddojo.helper.payments.Customer;
import com.simplyct.woddojo.helper.payments.TransactionResult;
import com.simplyct.woddojo.model.Classes;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.Payment;
import com.simplyct.woddojo.model.User;
import com.simplyct.woddojo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by cyril on 7/14/15.
 */
@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BraintreePayments braintreePayments;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model,
                         HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");

        Organization organization = organizationRepository.findOne(orgId);
        String clientToken = braintreePayments.getClientToken();
        Payment payment = new Payment();
        payment.setOrganization(organization);
        model.addAttribute("payment", payment);
        model.addAttribute("payment_method_nonce", clientToken);

        model.addAttribute("users", userRepository.findByOrganizationId(orgId));
        return "admin/payments/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        Long orgId = (Long) session.getAttribute("orgId");
        String clientToken = braintreePayments.getClientToken();
        model.addAttribute("payment_method_nonce", clientToken);

        if (id != null) {
            Payment payment = paymentRepository.findOne(id);
            model.addAttribute("payment", payment);
        } else {

            Organization organization = organizationRepository.findOne(orgId);
            Payment payment = new Payment();
            payment.setOrganization(organization);
            model.addAttribute("payment", payment);

        }

        model.addAttribute("users", userRepository.findByOrganizationId(orgId));
        return "admin/payments/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @RequestParam("payment_method_nonce") String paymentNonce,
                           @ModelAttribute @Valid Payment payment,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/payments/edit";
        }

        User user = payment.getUser();

        Customer customer = new Customer();
        customer.setUserId(user.getId());
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setEmail(user.getEmail());
        customer.setPrice(payment.getAmount());

        TransactionResult transactionResult = braintreePayments.postTransaction(customer, paymentNonce);

        if (!transactionResult.isSuccessful()) {
            model.addAttribute("errors", transactionResult.getMessage());
            return "admin/payments/edit";
        } else {
            Long orgId = payment.getOrganization().getId();
            payment.setStatus(transactionResult.getStatus().name());
            payment.setConfirmationId(transactionResult.getConfirmationId());
            Organization organization = organizationRepository.findOne(orgId);
            payment.setOrganization(organization);
            paymentRepository.save(payment);

            model.addAttribute("payments", paymentRepository.findByOrganizationId(orgId));
            model.addAttribute("users", userRepository.findByOrganizationId(orgId));
            return "admin/payments/list";
        }
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("payments", paymentRepository.findByOrganizationId(orgId));
        return "admin/payments/list";
    }

}
