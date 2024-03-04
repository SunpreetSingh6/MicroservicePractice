package com.UserService.PayLoad;

import lombok.*;
import org.springframework.http.HttpStatus;

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String msg;
    private HttpStatus status;
    private boolean success;

}
