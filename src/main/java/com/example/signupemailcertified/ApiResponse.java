package com.example.signupemailcertified;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private Integer status;
    private String message;
    private T data;

}
