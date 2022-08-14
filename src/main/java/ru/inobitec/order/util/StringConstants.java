package ru.inobitec.order.util;

import org.springframework.stereotype.Component;

@Component
public class StringConstants {
    public static final String CREATED = " created";
    public static final String UPDATED = " updated";
    public static final String BASE_PACKAGE = "ru.inobitec.order.controllers";
    public static final String ORDER_READ_NEGATIVE = "Unable to get order by ID ";
    public static final String ORDER_CREATED_POSITIVE = "Order was created successfully ";
    public static final String ORDER_CREATED_NEGATIVE = "Unable to update order ";
    public static final String ORDER_UPDATED_POSITIVE = "Order has been updated successfully ";
    public static final String ORDER_UPDATED_NEGATIVE = "Unable to update order by ID ";
    public static final String ORDER_DELETED_POSITIVE = "Order deleted successfully ";
    public static final String ORDER_DELETED_NEGATIVE = "Unable to delete order by ID ";
    public static final String INVALID_SESSION = "Invalid session";
    public static final String UNKNOWN_SESSION = "Unknown session";
    public static final String SESSION_EXPIRED = "Session expired";
    public static final String SESSION_ID = "SESSION-ID";
    public static final String URL = "http://localhost:8081/patient/";
    public static final String URL_PATIENT_NAME = "http://localhost:8081/patientName/";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTHDAY = "birthday";
    public static final String PHONE = "phone";
    public static final String SERVLET_CREATE = "Create successfully";
    public static final String SERVLET_UPDATE = "Update successfully";
    public static final String SERVLET_DELETE = "Delete successfully";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String INVALID_COMMAND = "Invalid command";
    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static final String UNABLE_TO_READ = "Unable to read file";

}
