package org.vargattila;

import lombok.Data;

@Data
public class UserContext {
    public static final String CORRELATION_ID = "correlation_id";
    String correlationId;
}
