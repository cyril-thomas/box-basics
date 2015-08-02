package com.simplyct.woddojo.helper.facebook;

import com.simplyct.woddojo.helper.dto.fb.FbAccessTokenResponse;
import com.simplyct.woddojo.helper.dto.fb.FbDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    public String getLoginUrl(String callbackUrl, String... scopes) {
        StringBuilder loginUrl = new StringBuilder("https://www.facebook.com/dialog/oauth?");
        loginUrl.append("client_id=")
                .append(facebookAppId)
                .append("&redirect_uri=")
                .append(callbackUrl);
        if (scopes.length > 0) {
            loginUrl.append("&scope=");
            loginUrl.append(String.join(",", scopes));
        }
        return loginUrl.toString();
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

    public boolean hasPermissions(String accessToken, String... permissions) {
        String getPermissionsUrl = buildUrl(BASE_URL + "me/permissions",
                "access_token");
        Map<String, String> params = buildParamMap("access_token", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        FbDataResponse response = restTemplate.getForObject(getPermissionsUrl,
                FbDataResponse.class, params);
        List permissionList = response.getData();
        Set<String> permissionSet = new HashSet<>();
        for (Object o : permissionList) {
            Map permissionMap = (Map) o;
            String permission = (String) permissionMap.get("permission");
            String status = (String) permissionMap.get("status");
            if ("granted".equals(status)) {
                permissionSet.add(permission);
            }
        }
        for (String permission : permissions) {
            if (!permissionSet.contains(permission)) {
                return false;
            }
        }
        return true;
    }

    public Map getPageInfo(String accessToken) {
        String checkUrl = buildUrl(BASE_URL + "me/accounts", "access_token");
        Map<String, String> params = buildParamMap("access_token", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        FbDataResponse response = restTemplate.getForObject(checkUrl,
                        FbDataResponse.class, params);
        if (response.getData().size() > 0) {
            return (Map) response.getData().get(0);
        }
        return null;
    }

    public void postToPage(String pageId, String pageAccessToken, String contents) {
        String postUrl = buildUrl(BASE_URL + pageId + "/feed", "access_token");
        Map<String, String> params = buildParamMap("access_token", pageAccessToken);
        Map<String, String> post = buildParamMap("message", contents);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> response = restTemplate.postForObject(postUrl, post, Map.class, params);
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
