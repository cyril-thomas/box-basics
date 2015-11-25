package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Pricing;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cyril on 8/20/15.
 */
@Data
public class PricingDto {
    Long                id;
    String              level;
    BigDecimal          cost;

    public  PricingDto(Pricing pricing) {
        this.id = pricing.getId();
        this.level = pricing.getLevel();
        this.cost = pricing.getCost();
    }
}
