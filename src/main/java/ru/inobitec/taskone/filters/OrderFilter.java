package ru.inobitec.taskone.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inobitec.taskone.model.OrderSessionEntity;
import ru.inobitec.taskone.service.SessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;


@WebFilter("/orderServlet")
@RequiredArgsConstructor
public class OrderFilter implements Filter {

    @Autowired
    SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String session_id = request.getParameter("session_id");
        if (session_id == null) {
            response.getWriter().println("Invalid session");
        } else {
            OrderSessionEntity orderSessionEntity = sessionService.getSessionBySessionId(session_id);
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
