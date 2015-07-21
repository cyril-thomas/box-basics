package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Wod;
import lombok.Data;

/**
 * Created by cyril on 7/19/15.
 */
@Data
public class WODDetail {
    private Long id;
    private String name;
    private String description;

    public WODDetail(Wod wod){
        this.id = wod.getId();
        this.name = wod.getName();
        this.description = wod.getDescription();
    }
}
