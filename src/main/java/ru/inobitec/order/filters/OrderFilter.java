package ru.inobitec.order.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inobitec.order.model.OrderSessionEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;


@WebFilter("/orderServlet")
@RequiredArgsConstructor
public class OrderFilter implements Filter {

    @Autowired
    SessionCache sessionCache;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String sessionId = request.getParameter("SESSION-ID");
        if (sessionId == null) {
            response.getWriter().println("Invalid session");
        } else {
            OrderSessionEntity orderSessionEntity = sessionCache.getSessions().get(sessionId);
            if (orderSessionEntity == null) {
                response.getWriter().println("Unknown session");
            } else {
                if (checkExpiring(orderSessionEntity)) {
                    chain.doFilter(request, response);
                } else {
                    response.getWriter().println("Session expired");
                }
            }
        }
    }

    private boolean checkExpiring(OrderSessionEntity orderSessionEntity) {
        return (orderSessionEntity.getStartTime().getTime() + (orderSessionEntity.getTimeoutMinutes() * 60 * 1000)) > new Date().getTime();
    }

    @Override
    public void destroy() {

    }
}
