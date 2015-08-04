package com.simplyct.woddojo.helper.payments;

import com.braintreegateway.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by cyril on 8/1/15.
 */
@Service
public class BraintreePayments {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${paymentgateway.brain.tree.Environment}")
    String environtmentProperty;
    @Value("${paymentgateway.brain.tree.mid}")
    String mid;
    @Value("${paymentgateway.brain.tree.public.key}")
    String publicKey;
    @Value("${paymentgateway.brain.tree.private.key}")
    String privateKey;
    private Environment      environment;
    private BraintreeGateway gateway;
    @Value("${paymentgateway.brain.tree.company.name}")
    private String           companyName;

    @Value("${paymentgateway.brain.tree.merchant.account}")
    private String merchantAccountId;

    @Value("${paymentgateway.brain.tree.company.support}")
    private String supportNumber;

    @Value("${paymentgateway.brain.tree.company.site}")
    private String companyUrl;


    @PostConstruct
    public void initBean() {
        switch (environtmentProperty) {
            case "PRODUCTION":
                environment = Environment.PRODUCTION;
                break;
            case "DEVELOPMENT":
                environment = Environment.DEVELOPMENT;
                break;
            default:
                environment = Environment.SANDBOX;
        }

        if (mid.isEmpty() || privateKey.isEmpty() || publicKey.isEmpty()) {
            String errorMessage = String.format("Cannot create connection to gateway with one of these being null. MID:%s PrivateKey:%s PublicKey:%s", mid, privateKey, publicKey);
            throw new IllegalArgumentException(errorMessage);
        }

        gateway = new BraintreeGateway(environment, mid, publicKey, privateKey);
    }

    public String getClientToken() {
        return gateway.clientToken().generate();
    }

    public TransactionResult postTransaction(Customer customer, String paymentNonce) {
        TransactionRequest transactionRequest = new TransactionRequest()
                .paymentMethodNonce(paymentNonce);
        if (merchantAccountId != null && !merchantAccountId.isEmpty()) {
            transactionRequest.merchantAccountId(merchantAccountId);
        }

        setCustomerFields(customer, transactionRequest);
        setOrder(customer, transactionRequest);
        setCompanyInfo(transactionRequest);
        transactionRequest.options().submitForSettlement(true);


        Result<Transaction> result = gateway.transaction().sale(transactionRequest);

        TransactionResult transactionResult = new TransactionResult();

        //Validation errors
        if (result.getErrors() != null && result.getErrors().size() > 0) {
            createTransactionResultForErrors(
                    customer,
                    result,
                    transactionResult);
        } else if (result.getMessage() != null && !result.getMessage().isEmpty()) {
            createTransactionResultForErrorMessage(customer, result.getMessage(), transactionResult);
        } else {
            Transaction.Status status = result.getTarget().getStatus();
            switch (status) {
                case AUTHORIZED:
                    transactionResult.setStatus(TransactionResult.TransactionStatus.AUTHORIZED);
                    break;
                case SUBMITTED_FOR_SETTLEMENT:
                    transactionResult.setStatus(TransactionResult.TransactionStatus.AUTHORIZED);
                    break;
                case GATEWAY_REJECTED:
                    createTransactionResultForGatewayReject(
                            customer,
                            result,
                            transactionResult);
                    break;
                case PROCESSOR_DECLINED:
                    createTransactionResultForProcessorDeclined(
                            customer,
                            result,
                            transactionResult);
                    break;
                case SETTLEMENT_DECLINED:
                    createTransactionResultForSettlementDeclined(
                            customer,
                            result,
                            transactionResult);
                    break;
                default:
                    throw new RuntimeException("Transaction result type is not handled. Status : " + status);
            }
        }

        if (result.getTarget() != null) {
            transactionResult.setAmount(result.getTarget().getAmount());
            transactionResult.setConfirmationId(result.getTarget().getId());
        }
        return transactionResult;
    }

    private void setCompanyInfo(TransactionRequest transactionRequest) {
        transactionRequest.descriptor()
                          .name(companyName)
                          .phone(supportNumber)
                          .url(companyUrl)
                          .done();
    }

    private void setCustomerFields(Customer customer, TransactionRequest transactionRequest) {
        transactionRequest
                .customer()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail());
    }

    private void setOrder(Customer customer, TransactionRequest transactionRequest) {
        transactionRequest
                .amount(customer.getPrice());
    }

    private void createTransactionResultForErrors(Customer customer, Result<com.braintreegateway.Transaction> result, TransactionResult transactionResult) {
        log.error("======================================================================================");
        log.error(String.format("Credit card processing was rejected for User Id: %s, for customer: %s", customer.getUserId(), customer.getFirstName()));
        ValidationErrors validationErrors = result.getErrors();
        validationErrors
                .getAllValidationErrors()
                .stream()
                .forEach(e -> handleErrors(e, transactionResult));
        log.error("======================================================================================");
        transactionResult.setStatus(TransactionResult.TransactionStatus.ERROR);
    }

    private void createTransactionResultForErrorMessage(Customer customer, String message, TransactionResult transactionResult) {
        log.error("======================================================================================");
        log.error(String.format("Credit card processing was rejected for User Id: %s, for customer: %s", customer.getUserId(), customer.getFirstName()));
        log.error("======================================================================================");
        transactionResult.setStatus(TransactionResult.TransactionStatus.ERROR);
        transactionResult.setMessage(message);
    }

    private void createTransactionResultForSettlementDeclined(Customer customer, Result<com.braintreegateway.Transaction> result, TransactionResult transactionResult) {
        log.error("======================================================================================");
        log.error(String.format("Credit card processing was rejected for User Id: %s, for customer: %s", customer.getUserId(), customer.getFirstName()));
        log.error(String.format(" Response code: %s Response Message: %s ", result.getTransaction().getProcessorSettlementResponseCode(), result.getTransaction().getProcessorSettlementResponseText()));
        log.error("======================================================================================");
        transactionResult.setMessage(result.getTransaction().getProcessorSettlementResponseText());
        transactionResult.setStatus(TransactionResult.TransactionStatus.DECLINED);
    }

    private void createTransactionResultForProcessorDeclined(Customer customer, Result<com.braintreegateway.Transaction> result, TransactionResult transactionResult) {
        log.error("======================================================================================");
        log.error(String.format("Credit card processing was rejected for User Id: %s, for customer: %s", customer.getUserId(), customer.getFirstName()));
        log.error(String.format(" Response code: %s Response Message: %s ", result.getTransaction().getProcessorResponseCode(), result.getTransaction().getProcessorResponseText()));
        log.error(String.format(" Additional Response Message: %s ", result.getTransaction().getAdditionalProcessorResponse()));
        log.error("======================================================================================");
        transactionResult.setMessage(result.getTransaction().getProcessorResponseText());
        transactionResult.setStatus(TransactionResult.TransactionStatus.DECLINED);
    }

    private void createTransactionResultForGatewayReject(Customer customer, Result<com.braintreegateway.Transaction> result, TransactionResult transactionResult) {
        log.error("======================================================================================");
        log.error(String.format("Credit card processing was rejected for User Id: %s, for customer: %s", customer.getUserId(), customer.getFirstName()));
        log.error(String.format(" Response Message: %s ", result.getTransaction().getGatewayRejectionReason()));
        log.error("======================================================================================");
        transactionResult.setMessage(result.getTransaction().getGatewayRejectionReason().name());
        transactionResult.setStatus(TransactionResult.TransactionStatus.DECLINED);
    }


    private void handleErrors(ValidationError e, TransactionResult transactionResult) {
        transactionResult.setMessage(e.getMessage());
        log.error(String.format(" Response code: %s Response Message: %s ", e.getCode().code, e.getMessage()));
    }

}
