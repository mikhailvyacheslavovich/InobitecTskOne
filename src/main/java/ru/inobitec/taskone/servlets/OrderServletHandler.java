package ru.inobitec.taskone.servlets;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.dto.MessageDTO;
import ru.inobitec.taskone.model.OrderEntity;
import ru.inobitec.taskone.model.OrderItemEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServletHandler extends DefaultHandler {
    private static final String ORDER = "order";

    private static final String ORDER_STATUS_ID = "orderStatusId";

    private static final String CUSTOMER_LAST_NAME = "customerLastName";

    private static final String CUSTOMER_FIRST_NAME = "customerFirstName";

    private static final String CUSTOMER_PHONE = "customerPhone";

    private static final String CUSTOMER_COMMENT = "customerComment";

    private static final String ITEMS = "items";

    private static final String ITEM = "item";

    private static final String COMMAND = "command";

    private static final String BODY = "body";

    private static final String BIRTHDAY = "customerBirthdate";

    private StringBuilder elementValue;

    private MessageDTO messageDTO;

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() {
        messageDTO = new MessageDTO();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case COMMAND -> elementValue = new StringBuilder();
            case BODY -> messageDTO.setOrderDTO(new OrderDTO());
            case ORDER_STATUS_ID -> elementValue = new StringBuilder();
            case CUSTOMER_FIRST_NAME -> elementValue = new StringBuilder();
            case CUSTOMER_LAST_NAME -> elementValue = new StringBuilder();
            case CUSTOMER_PHONE -> elementValue = new StringBuilder();
            case CUSTOMER_COMMENT -> elementValue = new StringBuilder();
            case BIRTHDAY -> elementValue = new StringBuilder();
            case ITEMS -> messageDTO.getOrderDTO().setOrderItems(new ArrayList<>());
            case ITEM -> {
                messageDTO.getOrderDTO().getOrderItems().add(new OrderItemEntity());
                elementValue = new StringBuilder();
            }
            case ORDER -> messageDTO.getOrderDTO().setOrderEntity(new OrderEntity());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case COMMAND -> messageDTO.setCommand(elementValue.toString());
            case ORDER_STATUS_ID ->
                    messageDTO.getOrderDTO().getOrderEntity().setOrderStatusId(Integer.parseInt(elementValue.toString()));
            case BIRTHDAY -> {
                try {
                    Date birthdate = parseDate(elementValue.toString());
                    messageDTO.getOrderDTO().getOrderEntity().setCustomerBirthday(birthdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            case CUSTOMER_LAST_NAME ->
                    messageDTO.getOrderDTO().getOrderEntity().setCustomerLastName(elementValue.toString());
            case CUSTOMER_FIRST_NAME ->
                    messageDTO.getOrderDTO().getOrderEntity().setCustomerFirstName(elementValue.toString());
            case CUSTOMER_PHONE -> messageDTO.getOrderDTO().getOrderEntity().setCustomerPhone(elementValue.toString());
            case CUSTOMER_COMMENT ->
                    messageDTO.getOrderDTO().getOrderEntity().setCustomerComment(elementValue.toString());
            case ITEM -> lastOrderItem().setItemName(elementValue.toString());
        }
    }

    private OrderItemEntity lastOrderItem() {
        List<OrderItemEntity> list = messageDTO.getOrderDTO().getOrderItems();
        return list.get(list.size() - 1);
    }

    public MessageDTO getMessage() {
        return messageDTO;
    }

    private Date parseDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(stringDate);
    }
}
