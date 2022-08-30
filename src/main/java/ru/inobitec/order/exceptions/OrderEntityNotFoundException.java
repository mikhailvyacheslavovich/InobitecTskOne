package ru.inobitec.order.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderEntityNotFoundException extends RuntimeException {
}
