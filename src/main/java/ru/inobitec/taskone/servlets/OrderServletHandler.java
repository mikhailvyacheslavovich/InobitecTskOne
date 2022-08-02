package ru.inobitec.taskone.servlets;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Message;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderServletHandler extends DefaultHandler {
    private static final String ORDER = "order";

    private static final String ORDER_STATUS_ID = "orderStatusId";

    private static final String CUSTOMER_NAME = "customerName";

    private static final String CUSTOMER_PHONE = "customerPhone";

    private static final String CUSTOMER_COMMENT = "customerComment";

    private static final String ITEMS = "items";

    private static final String ITEM = "item";

    private static final String COMMAND = "command";

    private static final String BODY = "body";

    private StringBuilder elementValue;

    private Message message;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        message = new Message();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case COMMAND:
                elementValue = new StringBuilder();
                break;
            case BODY:
                message.setOrderDTO(new OrderDTO());
                break;
            case ORDER_STATUS_ID:
                elementValue = new StringBuilder();
                break;
            case CUSTOMER_NAME:
                elementValue = new StringBuilder();
                break;
            case CUSTOMER_PHONE:
                elementValue = new StringBuilder();
                break;
            case CUSTOMER_COMMENT:
                elementValue = new StringBuilder();
                break;
            case ITEMS:
                message.getOrderDTO().setOrderItems(new ArrayList<OrderItem>());
                break;
            case ITEM:
                message.getOrderDTO().getOrderItems().add(new OrderItem());
                elementValue = new StringBuilder();
                break;
            case ORDER:
                message.getOrderDTO().setOrder(new Order());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case COMMAND:
                message.setCommand(elementValue.toString());
                break;
            case ORDER_STATUS_ID:
                message.getOrderDTO().getOrder().setOrderStatusId(Integer.parseInt(elementValue.toString()));
                break;
            case CUSTOMER_NAME:
                message.getOrderDTO().getOrder().setCustomerLastName(elementValue.toString());
                break;
            case CUSTOMER_PHONE:
                message.getOrderDTO().getOrder().setCustomerPhone(elementValue.toString());
                break;
            case CUSTOMER_COMMENT:
                message.getOrderDTO().getOrder().setCustomerComment(elementValue.toString());
                break;
            case ITEM:
                lastOrderItem().setItemName(elementValue.toString());
                break;
        }
    }

    private OrderItem lastOrderItem() {
        List<OrderItem> list = message.getOrderDTO().getOrderItems();
        return list.get(list.size() - 1);
    }

    public Message getMessage() {
        return message;
    }
}
