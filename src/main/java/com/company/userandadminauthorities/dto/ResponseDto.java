package com.company.userandadminauthorities.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto <T> {

    private boolean success;
    private String message;
    private int code;
    /*
    * -1  => not found
    * -2  => database error
    * -3  => validation error
    * 0   =>  default holat;
    * */

    private T data;
    private List<ErrorDto> errors;
}
