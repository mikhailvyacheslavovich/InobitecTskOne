package ru.inobitec.order.servlets;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;
import ru.inobitec.order.dto.MessageDTO;
import ru.inobitec.order.dto.OrderPatientDTO;
import ru.inobitec.order.service.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

@WebServlet("/orderServlet")
@RequiredArgsConstructor
public class OrderServlet extends HttpServlet {

    @Autowired
    OrderService orderService;

    OrderServletHandler orderServletHandler;
    SAXParser parser;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            orderServletHandler = new OrderServletHandler();
        } catch (ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            OrderPatientDTO order = orderService.getOrderById(id);
            response.setContentType("text/html");
            response.getWriter().println(order.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            parser.parse(request.getInputStream(), orderServletHandler);
            MessageDTO result = orderServletHandler.getMessage();
            orderService.addOrder(result.getOrderDTO());
            response.setContentType("text/html");
            response.getWriter().println("create successfully");
        } catch (SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            parser.parse(request.getInputStream(), orderServletHandler);
            MessageDTO result = orderServletHandler.getMessage();
            Long id = Long.parseLong(request.getParameter("id"));
            orderService.updateOrder(result.getOrderDTO(), id);
            response.setContentType("text/html");
            response.getWriter().println("update successfully");
        } catch (SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            orderService.deleteOrderById(id);
            response.setContentType("text/html");
            response.getWriter().println("delete successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
