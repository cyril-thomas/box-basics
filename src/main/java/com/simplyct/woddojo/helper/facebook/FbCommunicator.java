package com.simplyct.woddojo.helper.facebook;

import com.simplyct.woddojo.helper.dto.fb.FbAccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by molsen_admin on 7/21/15.
 */

@Service
public class FbCommunicator {
    private static final String API_VERSION = "2.4";
    private static final String BASE_URL = "https://graph.facebook.com/v" + API_VERSION + "/";

    @Value("${facebook.appId}")
    private String facebookAppId;

    @Value("${facebook.appSecret}")
    private String facebookAppSecret;

    public String getLoginUrl(String callbackUrl) {
        return "https://www.facebook.com/dialog/oauth?" +
                "client_id=" + facebookAppId +
                "&redirect_uri=" + callbackUrl;
    }

    public String getAccessToken(String redirectUrl, String code) {
        String accessTokenUrl = buildUrl(BASE_URL + "oauth/access_token", "client_id",
                "redirect_uri", "client_secret", "code");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = buildParamMap(
                "client_id", facebookAppId,
                "redirect_uri", redirectUrl,
                "client_secret", facebookAppSecret,
                "code", code);
        FbAccessTokenResponse tokenResponse =
                restTemplate.getForObject(accessTokenUrl, FbAccessTokenResponse.class, params);
        return tokenResponse.getAccess_token();
    }

    private String buildUrl(String baseUrl, String... params) {
        StringBuilder fullUrl = new StringBuilder(baseUrl);
        fullUrl.append("?");
        for (String param : params) {
            fullUrl.append(String.format("%1$s={%1$s}&", param));
        }
        return fullUrl.toString();
    }

    private Map<String, String> buildParamMap(String... params) {
        Map<String, String> retVal = new HashMap<>();
        for (int i=0; i < params.length; i += 2) {
            retVal.put(params[i], params[i+1]);
        }
        return retVal;
    }
}
