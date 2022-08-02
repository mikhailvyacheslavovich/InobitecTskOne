package ru.inobitec.taskone.servlets;

import org.xml.sax.SAXException;
import ru.inobitec.taskone.model.Message;

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
public class OrderServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            OrderServletHandler orderServletHandler = new OrderServletHandler();

            parser.parse(request.getInputStream(), orderServletHandler);
            Message result = orderServletHandler.getMessage();

            System.out.println(result);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
