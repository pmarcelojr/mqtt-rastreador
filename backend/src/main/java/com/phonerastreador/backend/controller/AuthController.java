package com.phonerastreador.backend.controller;

import com.phonerastreador.backend.controller.dto.LoginForm;
import com.phonerastreador.backend.controller.dto.TokenDto;
import com.phonerastreador.backend.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Value("${app.jwt.expiracao}")
    private Long expires;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form) {
        log.info("Realizando login: {}", form);
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        Authentication autenticacao = this.authManager.authenticate(dadosLogin);

        String token = this.tokenService.gerarToken(autenticacao);

        return ResponseEntity.ok(new TokenDto(token, "Bearer", expires));
    }

}
