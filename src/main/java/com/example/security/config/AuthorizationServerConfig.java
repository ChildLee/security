package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenEnhancerConfig tokenEnhancerConfig;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //授权服务器安全配置器,允许客户的表单身份验证
        System.out.println("3.授权服务器安全配置器,此处必须设置");
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("2.配置客户端");
        clients.inMemory() // 使用in-memory存储
                .withClient("client") // client_id
                .secret("secret") // client_secret
                .authorizedGrantTypes("client_credentials", "refresh_token") // 该client允许的授权类型
                .scopes("all") // 允许的授权范围
                .accessTokenValiditySeconds(60 * 30)//access_token有效期,单位秒
                .refreshTokenValiditySeconds(60 * 60 * 24);//refresh_token有效期一天

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //授权服务器端点配置器,默认情况下，除了密码之外，所有的授权类型都是受支持的
        System.out.println("1.授权服务器端点配置器,可不配置,默认就行");
        //设置自定义的参数值
        endpoints.tokenEnhancer(tokenEnhancerConfig);
    }
}
