package com.example.chat_spring_thymeleaf.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {
    List<String> openPages = List.of(
            "/login",
            "/auth/login"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (openPages.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            Object object = request.getSession().getAttribute("currentUser");
            if (object != null) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/login");
            }
        }
    }
}
