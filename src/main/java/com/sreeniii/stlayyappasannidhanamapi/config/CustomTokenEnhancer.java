package com.sreeniii.stlayyappasannidhanamapi.config;

import com.sreeniii.stlayyappasannidhanamapi.util.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("customized", "true");
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        additionalInfo.put("isAdmin", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains(Constants.ADMIN_ROLE));
        additionalInfo.put("firstName", user.getFirstName());
        additionalInfo.put("lastName", user.getLastName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return super.enhance(accessToken, authentication);
    }
}
