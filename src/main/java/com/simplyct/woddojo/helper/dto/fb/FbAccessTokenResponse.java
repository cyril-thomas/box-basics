package com.simplyct.woddojo.helper.dto.fb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by matt.olsen on 7/21/15.
 */
@JsonIgnoreProperties
public class FbAccessTokenResponse {
    private String access_token;
    private String token_type;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
