package com.phonerastreador.backend.config;

import javax.servlet.http.HttpServletResponse;

import com.phonerastreador.backend.repository.UserRepository;
import com.phonerastreador.backend.service.AutenticacaoService;
import com.phonerastreador.backend.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
        // habilitar CORS e desabilitar CSRF
        .cors().and().csrf().disable()

        // definindo as sessões como stateless
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

        // definindo excessões para requisições não autorizadas
        .exceptionHandling().authenticationEntryPoint((req, res, ex) -> {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }).and()

        // definindo permissões de acessos à endpoints
        .authorizeHttpRequests()
            // endpoint publico
            .antMatchers("/api/public/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .antMatchers(HttpMethod.POST, "/user").permitAll()
            // privado
            .anyRequest().authenticated();

        http.addFilterBefore(new AuthTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }
}
