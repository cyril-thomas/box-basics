package com.simplyct.woddojo.config;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cyril on 7/6/15.
 */
@Configuration
public class SocialMediaConfig {

    @Value("${instagram.clientId}")
    private String igClientId;

    @Value("${instagram.clientSecret}")
    private String igClientSecret;

    @Value("${instagram.redirectUri}")
    private String igCallbackUrl;

    @Bean(name = "instagramService")
    public InstagramService getInstagram() {
        InstagramService instagramService = new InstagramAuthService()
                .apiKey(igClientId)
                .apiSecret(igClientSecret)
                .callback(igCallbackUrl)
                .build();
        return instagramService;
    }
}
