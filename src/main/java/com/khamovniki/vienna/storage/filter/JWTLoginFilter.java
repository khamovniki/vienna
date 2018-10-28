package com.khamovniki.vienna.storage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.khamovniki.vienna.storage.entity.CredentialsRepository;

import lombok.RequiredArgsConstructor;

//super ugly, but we have some "security"
@RequiredArgsConstructor
public class JWTLoginFilter implements Filter {

    public static final String HEADER = "token";

    private final CredentialsRepository credentialsRepository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader(HEADER);
        credentialsRepository.findByToken(token).orElseThrow(SecurityException::new);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) {
    }

}