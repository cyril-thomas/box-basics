package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Service;
import lombok.Data;

/**
 * Created by cyril on 7/5/15.
 */
@Data
public class ServiceDetail {
    Long   serviceId;
    String content;
    String title;

    public ServiceDetail(Service service) {
        this.serviceId = service.getId();
        this.title = service.getTitle();
        this.content = service.getContent();
    }
}
