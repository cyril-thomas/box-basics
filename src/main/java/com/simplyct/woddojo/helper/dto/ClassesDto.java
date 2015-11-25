package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Classes;
import com.simplyct.woddojo.model.Pricing;
import com.simplyct.woddojo.model.User;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cyril on 8/20/15.
 */
@Data
public class ClassesDto {
    Long             id;
    String           name;
    String           description;
    String           coach;

    public ClassesDto(Classes classes) {
        this.id = classes.getId();
        this.name = classes.getName();
        this.description = classes.getDescription();
        this.coach = getCoachName(classes);
    }

    private String getCoachName(Classes classes) {
        User user = classes.getCoach().getUser();
        return user.getFirstName() + " " + user.getLastName();
    }
}
