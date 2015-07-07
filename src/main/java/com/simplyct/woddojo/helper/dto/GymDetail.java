package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Address;
import com.simplyct.woddojo.model.Organization;
import lombok.Data;

/**
 * Created by cyril on 6/30/15.
 */
@Data
public class GymDetail {
    Long    orgId;
    String  name;
    Address postalAddress;
    String  orgEmail;
    String  phone;

    public GymDetail(Organization organization) {
        this.orgId = organization.getId();
        this.orgEmail = organization.getEmail();
        this.phone = organization.getPhone();
        this.name = organization.getName();
        this.postalAddress = organization.getPostalAddress();
    }
}
