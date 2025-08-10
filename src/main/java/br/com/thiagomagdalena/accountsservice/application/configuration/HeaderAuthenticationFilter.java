package br.com.thiagomagdalena.accountsservice.application.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    private static final String ROLES_HEADER = "X-User-Roles";
    private final List<PathPattern> publicPatterns;

    public HeaderAuthenticationFilter(SecurityProperties securityProperties) {
        PathPatternParser parser = new PathPatternParser();
        this.publicPatterns = securityProperties.getPublicPaths().stream()
                .map(parser::parse)
                .collect(Collectors.toList());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isPublicPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String rolesHeader = request.getHeader(ROLES_HEADER);
        if (rolesHeader == null || rolesHeader.isEmpty()) {
            log.info("Header '{}' não encontrado para uma rota protegida: {}. Acesso será negado.", ROLES_HEADER, request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        List<SimpleGrantedAuthority> authorities = Stream.of(rolesHeader.split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                "pre-authenticated-user", null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(HttpServletRequest request) {
        RequestPath path = RequestPath.parse(request.getRequestURI(), request.getContextPath());
        return publicPatterns.stream().anyMatch(pattern -> pattern.matches(path));
    }
}
