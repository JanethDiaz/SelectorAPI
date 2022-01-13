package com.danfoss.api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConf  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("selector-web")
                .secret(passwordEncoder.encode("selector123"))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(86400)
                .refreshTokenValiditySeconds(86400);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(RSA_PRIVATE);
        jwtAccessTokenConverter.setVerifierKey(RSA_PUBLIC);
        return jwtAccessTokenConverter;
    }

    private static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA166nN8MIhU4/BiEe5oGyl/9yIHMuj/PUrXq6sHHszWA4OC7E\n" +
            "lneCa/sZS4WWywYZFGzKO7zVOt8WF5qOxk69XClsEfoQxW4ygBDRxxkJUyoAp5Rd\n" +
            "jlYGzhsDlcl1fpH5ANQfZEfcCh39lG3cjOxIbl+wVF4IDEF8+gYHbqb69PPLoAkE\n" +
            "GLu/gdsN1th4wcUQcD30iC/MnpOUdlAcckgjCcSsYdHsr18Oa+lV20RUg8sLK4e0\n" +
            "/9U4iBJzJWaHtkngKvx+M9dRXda9Au93E6QBy+epgcGZVsExnsj7RM5RGrc+U1Kw\n" +
            "TrUEjW5ZoPxPB7nUina7+sf6otcY4uj+ANVegQIDAQABAoIBAQDCtW1ILc3XalgD\n" +
            "FIts1NGB3p4NJW53jkwFgLJg77gzgBXAwpe+ro0TJlAjmaoUBqFFiyDW5RYqW5L6\n" +
            "JBGYfST9undRk0rosR7t9kErWDLO3TBSGOKMyKTEFCccZg6oAOemE9lzElxW1dRC\n" +
            "1xswLW8GFWR+BZWGWuiUXjT/c8dCsdxpe8NCV8RoOU7qWvab0LZW3JmMwh0/gpmM\n" +
            "2meKVaWJZ0W3TXNsN4gbvFRcQP4Wm+DM2Y9hPOW5Z3ifIQNx9oG1/3nshcmItopT\n" +
            "AiZ/LGzKzmvm8F+wsNksQ41i4G1v8mRsLncG2vZhHv25SXYfpS5xqBUtK4e9Aqpv\n" +
            "1z1MLfdBAoGBAO4KRFoQO6dtAqWOpcMhGusHf8xPJCIoVZDAOeFKYP+47oxBBXz4\n" +
            "YhRvV01wJ1tOGWbbKw619W/NZcAtTp05Eqjdg8A5FS26zI5m3VN1svVT+9l8J5pX\n" +
            "5tBhs1G/ACfp3YrehCnFxlKDIovp4MhEJHYmWTSrHKe+Z00vLjCPnlcdAoGBAOf0\n" +
            "i4uP9KIPNn8T8AkbxzJXBjDEW5FBMcX1luVOTcYeBGSjfllYiz7SrwF5txJ7G2ma\n" +
            "Rf4dGP2uYYFRq/BKqxxRSqJofb9CC+HlCeB09hpM+9zOdSdV39u9EqS8NphOMBmc\n" +
            "+6aDl84LPfrIm/hnAcafsfuY1/lbS+JmKoUC8zO1AoGAUk7ghZfZWLIb2S0y6f7X\n" +
            "dO855GZ1Iw55T7z4BouWcLX8XijZgfXMzC4XrOIb6blelS8xezu1qzwFc+b2qOyk\n" +
            "JBm8Rco3JYHS22Z8HzCyeZvZmMESXI8Kc4Fk5N47apG2Fh+M6XjGc59+YYy0Oes+\n" +
            "BFCMRebo2KPAynKG8Smn4Y0CgYEAxP2QjYRCdGeLFr1t+65Vq9DwdvYr9d5yUPbT\n" +
            "ogawUnMns/iN0385Mq9m5U/jRJS8oSDeFIPNt5QjRtp/tWEUlMYNJoSrVoTW5o/H\n" +
            "pZJvd8Ktm8bTBAcQSOP4M2OM0iiE/RuvNKlJgTlW9+brXzjL702ssEoMEb+RYML3\n" +
            "lWL9/IkCgYBT8RxpQnlTq90Zq69flNUU991uT8HTxcaB6sx4qoCk03k+uSPI2xpP\n" +
            "WOAe7IwN/j4M9WRQqfshkjH5RkwEpnExP4fAVCkiFyZsmUjlyI0dlP0tJE+OcsxZ\n" +
            "3ZLjSuOCAS7qGs19MCtFxJWLKaB+T0sb87jPoyFq6PSvOc3Uq2qchg==\n" +
            "-----END RSA PRIVATE KEY-----";

    private static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA166nN8MIhU4/BiEe5oGy\n" +
            "l/9yIHMuj/PUrXq6sHHszWA4OC7ElneCa/sZS4WWywYZFGzKO7zVOt8WF5qOxk69\n" +
            "XClsEfoQxW4ygBDRxxkJUyoAp5RdjlYGzhsDlcl1fpH5ANQfZEfcCh39lG3cjOxI\n" +
            "bl+wVF4IDEF8+gYHbqb69PPLoAkEGLu/gdsN1th4wcUQcD30iC/MnpOUdlAcckgj\n" +
            "CcSsYdHsr18Oa+lV20RUg8sLK4e0/9U4iBJzJWaHtkngKvx+M9dRXda9Au93E6QB\n" +
            "y+epgcGZVsExnsj7RM5RGrc+U1KwTrUEjW5ZoPxPB7nUina7+sf6otcY4uj+ANVe\n" +
            "gQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

}
