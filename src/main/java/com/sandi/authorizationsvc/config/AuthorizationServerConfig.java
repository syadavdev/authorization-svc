package com.sandi.authorizationsvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import javax.annotation.PostConstruct;
import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private Environment env;

    private KeyPair keyPair;

    @PostConstruct
    private void init(){
        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
              ctx.getResource(env.getProperty("oauth2.jwt.jks")),
              env.getProperty("oauth2.jwt.keypass").toCharArray()
        );
        keyPair = keyStoreKeyFactory.getKeyPair(env.getProperty("oauth2.jwt.keypair"));
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwkTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        return converter;
    }
}
