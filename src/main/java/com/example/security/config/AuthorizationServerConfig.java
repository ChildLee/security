package com.example.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //授权服务器安全配置器,允许客户的表单身份验证
        System.out.println("3.授权服务器安全配置器");
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("2.配置客户端");
        clients.inMemory() // 使用in-memory存储
                .withClient("client") // client_id
                .secret("secret") // client_secret
                .authorizedGrantTypes("password", "refresh_token", "client_credentials", "authorization_code", "implicit") // 该client允许的授权类型
                .scopes("all"); // 允许的授权范围
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //授权服务器端点配置器,默认情况下，除了密码之外，所有的授权类型都是受支持的
        System.out.println("1.授权服务器端点配置器");
        super.configure(endpoints);
    }
}
