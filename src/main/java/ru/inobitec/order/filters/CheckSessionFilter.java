package ru.inobitec.order.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inobitec.order.model.OrderSessionEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import static ru.inobitec.order.util.StringConstants.*;


@WebFilter("/orderServlet")
@RequiredArgsConstructor
@Log4j2
public class CheckSessionFilter implements Filter {
    @Autowired
    private final SessionCache sessionCache;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String sessionId = request.getParameter(SESSION_ID);
            if (sessionId == null) {
                response.getWriter().println(INVALID_SESSION);
            } else {
                OrderSessionEntity orderSessionEntity = sessionCache.getSessions().get(sessionId);
                if (orderSessionEntity == null) {
                    response.getWriter().println(UNKNOWN_SESSION);
                } else {
                    if (checkExpiring(orderSessionEntity)) {
                        chain.doFilter(request, response);
                    } else {
                        response.getWriter().println(SESSION_EXPIRED);
                    }
                }
            }
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
        }
    }

    private boolean checkExpiring(OrderSessionEntity orderSessionEntity) {
        return (orderSessionEntity.getStartTime().getTime()
                + (Duration.ofMinutes(orderSessionEntity.getTimeoutMinutes()).toMillis())) > new Date().getTime();
    }

    @Override
    public void destroy() {

    }
}
