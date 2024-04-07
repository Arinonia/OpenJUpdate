package fr.arinonia.openjupdate.config;

import fr.arinonia.openjupdate.service.SetupService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class SetupCheckFilter extends GenericFilterBean {

    private final SetupService setupService;

    public SetupCheckFilter(final SetupService setupService) {
        this.setupService = setupService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!this.setupService.isCmsSetUp() && !request.getRequestURI().equals("/setup") && !request.getRequestURI().startsWith("/css/") && !request.getRequestURI().startsWith("/js/") && !request.getRequestURI().startsWith("/images/")) {
            response.sendRedirect("/setup");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
