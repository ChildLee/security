package com.example.security.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 自定义生成令牌和其他参数
 * 默认规则是：UUID。
 * Spring OAuth2提供了一个操作Token的接口TokenEnhancer，
 * 通过实现它可以任意操作accessToken和refreshToken。
 * 比如，这里实现将Token中的横线去掉。
 */
@Component
public class TokenEnhancerConfig implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        if (accessToken instanceof DefaultOAuth2AccessToken) {
            new DefaultOAuth2AccessToken(accessToken);
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
            token.setValue(getNewToken());

            OAuth2RefreshToken refreshToken = token.getRefreshToken();
            if (refreshToken instanceof DefaultOAuth2RefreshToken) {
                token.setRefreshToken(new DefaultOAuth2RefreshToken(getNewToken()));
            }

            Map<String, Object> additionalInformation = new HashMap<String, Object>();
            additionalInformation.put("client_id", authentication.getOAuth2Request().getClientId());
            token.setAdditionalInformation(additionalInformation);

            return token;
        }
        return accessToken;
    }

    private String getNewToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}