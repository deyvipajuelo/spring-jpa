package com.springjpa.springjpa.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MessageResponse {

    private String message;
    private Object body;

}
