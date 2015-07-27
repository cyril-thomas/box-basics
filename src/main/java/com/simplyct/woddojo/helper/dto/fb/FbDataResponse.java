package com.simplyct.woddojo.helper.dto.fb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by matt.olsen on 7/23/15.
 */
@JsonIgnoreProperties
public class FbDataResponse<T> {
    List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
