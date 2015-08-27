package com.simplyct.woddojo.helper.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String emailTo;
    private String emailFrom;
    private String subject;
    private String confirmationId;
    private String orgName;
    private String userName;
    private String logoUrl;

    public EmailDto(String emailTo, String emailFrom, String subject) {
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.subject = subject;
    }
}
