package com.sandi.authorizationsvc.config;

import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FeignRequestInterceptor implements RequestInterceptor{

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApplicationContext ctx;
    @Value("${oauth2.jwt.jks}")
    private String jwtJks;
    @Value("${oauth2.jwt.keypair}")
    private String jwtKeypair;
    @Value("${oauth2.jwt.keypass}")
    private String jwtKeypass;
    private Key key;

    @PostConstruct
    private void init() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException {
        Resource resource = ctx.getResource(jwtJks);
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(resource.getInputStream(), jwtKeypass.toCharArray());
        key = keyStore.getKey(jwtKeypair, jwtKeypass.toCharArray());
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(HttpHeaders.AUTHORIZATION, "Bearer "+ generateAccessToken());
    }

    private String generateAccessToken() {
        Map<String, Object> claims = new HashMap<>();
        List<String> authorities = new ArrayList<>();
        authorities.add("VIEW_CUSTOMER");
        claims.put("authorities", authorities);
        List<String> scopes = new ArrayList<>();
        scopes.add("read");
        scopes.add("write");
        claims.put("scope", scopes);
        claims.put("companyId", request.getParameter("companyId"));
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.RS256, key)
                .compact();
    }
}
