package com.github.dariozubaray.springboot.zuul.oath;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${config.security.oauth.jwt.key}")
    private String jwtKey;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] listar = {"/api/productos/listar", "/api/productos/listar", "/api/usuarios/usuarios"};
        String[] ver = {"/api/productos/ver/{id}", "/api/productos/ver/{id}/cantidad/{cantidad}", "/api/usuarios/usuarios/{id}"};
        String[] crear = {"/api/productos/crear", "/api/items/crear", "/api/usuarios/usuarios"};
        String[] editar = {"/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuarios/{id}"};
        String[] eliminar = {"/api/productos/eliminar/{id}", "/api/items/eliminar/{id}", "/api/usuarios/usuarios/{id}"};

        http.authorizeRequests().antMatchers("/api/security/oauth/token").permitAll()
            .antMatchers(HttpMethod.GET, listar).permitAll()
            .antMatchers(HttpMethod.GET, ver).hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.POST, crear).hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, editar).hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, eliminar).hasRole("ADMIN")
            .antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
            .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtKey);
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
}
