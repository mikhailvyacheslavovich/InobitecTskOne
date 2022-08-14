package ru.inobitec.order.servlets;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.MessageDTO;
import ru.inobitec.order.dto.OrderItemDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.inobitec.order.util.StringConstants.DATE_FORMAT;

@Component
public class OrderServletHandler extends DefaultHandler {

    private static final String ORDER_STATUS_ID = "orderStatusId";

    private static final String LAST_NAME = "lastName";

    private static final String FIRST_NAME = "firstName";

    private static final String CUSTOMER_PHONE = "customerPhone";

    private static final String CUSTOMER_COMMENT = "customerComment";

    private static final String CUSTOMER_NAME = "customerName";

    private static final String ITEMS = "items";

    private static final String ITEM = "item";

    private static final String COMMAND = "command";

    private static final String BODY = "body";

    private static final String BIRTHDAY = "birthday";

    private static final String DEFAULT_MESSAGE = "This tag name is not provided";

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
            case FIRST_NAME -> elementValue = new StringBuilder();
            case LAST_NAME -> elementValue = new StringBuilder();
            case CUSTOMER_NAME -> elementValue = new StringBuilder();
            case CUSTOMER_PHONE -> elementValue = new StringBuilder();
            case CUSTOMER_COMMENT -> elementValue = new StringBuilder();
            case BIRTHDAY -> elementValue = new StringBuilder();
            case ITEMS -> messageDTO.getOrderDTO().setOrderItems(new ArrayList<>());
            case ITEM -> {
                messageDTO.getOrderDTO().getOrderItems().add(new OrderItemDTO());
                elementValue = new StringBuilder();
            }
            default -> {
                System.out.println(DEFAULT_MESSAGE);
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case COMMAND -> messageDTO.setCommand(elementValue.toString());
            case ORDER_STATUS_ID -> messageDTO.getOrderDTO().setOrderStatusId(Long.parseLong(elementValue.toString()));
            case BIRTHDAY -> {
                try {
                    Date birthdate = parseDate(elementValue.toString());
                    messageDTO.getOrderDTO().setBirthday(birthdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            case LAST_NAME -> messageDTO.getOrderDTO().setLastName(elementValue.toString());
            case CUSTOMER_NAME -> messageDTO.getOrderDTO().setCustomerName(elementValue.toString());
            case FIRST_NAME -> messageDTO.getOrderDTO().setFirstName(elementValue.toString());
            case CUSTOMER_PHONE -> messageDTO.getOrderDTO().setCustomerPhone(elementValue.toString());
            case CUSTOMER_COMMENT -> messageDTO.getOrderDTO().setCustomerComment(elementValue.toString());
            case ITEM -> lastOrderItem().setItemName(elementValue.toString());
            default -> {
                System.out.println(DEFAULT_MESSAGE);
            }
        }
    }

    private OrderItemDTO lastOrderItem() {
        List<OrderItemDTO> list = messageDTO.getOrderDTO().getOrderItems();
        return list.get(list.size() - 1);
    }

    public MessageDTO getMessage() {
        return messageDTO;
    }

    private Date parseDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(stringDate);
    }
}
