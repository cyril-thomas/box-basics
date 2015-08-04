package com.simplyct.woddojo.helper.payments;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cyril on 8/1/15.
 */
@Data
public class Customer {
    Long userId;
    BigDecimal price;
    String firstName;
    String lastName;
    String email;
}
