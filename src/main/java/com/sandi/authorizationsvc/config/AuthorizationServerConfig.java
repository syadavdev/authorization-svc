package com.sandi.authorizationsvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@Configuration
@EnableAut
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
}
