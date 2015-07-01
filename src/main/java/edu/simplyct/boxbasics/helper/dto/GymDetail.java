package edu.simplyct.boxbasics.helper.dto;

import edu.simplyct.boxbasics.model.Address;
import edu.simplyct.boxbasics.model.Organization;
import lombok.Data;
import lombok.ToString;

/**
 * Created by cyril on 6/30/15.
 */
@Data
@ToString
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
