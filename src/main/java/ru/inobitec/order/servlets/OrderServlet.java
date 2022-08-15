package ru.inobitec.order.servlets;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;
import ru.inobitec.order.dto.MessageDTO;
import ru.inobitec.order.dto.OrderDTO;
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
import java.io.PrintWriter;
import java.io.StringWriter;

import static ru.inobitec.order.util.StringConstants.*;

@WebServlet("/orderServlet")
@RequiredArgsConstructor
@Log4j2
public class OrderServlet extends HttpServlet {

    private final OrderService orderService;
    private OrderServletHandler orderServletHandler;
    private SAXParser parser;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            orderServletHandler = new OrderServletHandler();
        } catch (ParserConfigurationException | SAXException ex) {
            log.error(ex.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            parser.parse(request.getInputStream(), orderServletHandler);
            MessageDTO result = orderServletHandler.getMessage();
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            switch (result.getCommand()) {
                case CREATE -> {
                    orderService.addOrder(result.getOrderDTO());
                    writer.println(SERVLET_CREATE);
                }
                case UPDATE -> {
                    orderService.updateOrder(result.getOrderDTO());
                    writer.println(SERVLET_UPDATE);
                }
                case DELETE -> {
                    orderService.deleteOrderById(result.getOrderDTO().getId());
                    writer.println(SERVLET_DELETE);
                }
                case READ -> {
                    OrderDTO order = orderService.getOrderById(result.getOrderDTO().getId());
                    try {
                        JAXBContext context = JAXBContext.newInstance(OrderDTO.class);
                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                        StringWriter stringWriter = new StringWriter();
                        marshaller.marshal(order, stringWriter);
                        writer.println(stringWriter);
                    } catch (JAXBException e) {
                        writer.println(UNABLE_TO_READ);
                        throw new RuntimeException(e);
                    }
                }
                default -> writer.println(INVALID_COMMAND);
            }
        } catch (SAXException | IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
