package ru.inobitec.order.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class RabbitSender {

    private final RabbitTemplate rabbitTemplate;

    private final Binding binding;

    public void sendMessage(String message) {
        try {
            rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
        }
    }
}
