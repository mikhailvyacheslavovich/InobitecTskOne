package ru.inobitec.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;

import java.util.Date;
import java.util.List;

import static ru.inobitec.order.util.StringConstants.DATE_FORMAT;

@Data
@RequiredArgsConstructor
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDTO {
    private Long id;
    private Long orderStatusId;
    private Long patientId;
    private String customerComment;
    @XmlElementWrapper(name ="items")
    @XmlElement(name = "item")
    private List<OrderItemDTO> orderItems;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Patient patient;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String firstName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String midName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String address;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String phone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Byte genderId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @DateTimeFormat(pattern = DATE_FORMAT)
    @JsonFormat(locale = "ru", timezone = "GMT+3", pattern = DATE_FORMAT)
    private Date birthday;

    public OrderEntity toEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatusId(getOrderStatusId());
        orderEntity.setCustomerComment(getCustomerComment());
        orderEntity.setOrderItems(orderItems.stream().map(OrderItemDTO::toEntity).toList());
        orderEntity.setPatientId(getPatientId());
        orderEntity.setId(getId());
        return orderEntity;
    }
}
