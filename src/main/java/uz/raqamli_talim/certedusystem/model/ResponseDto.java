package uz.raqamli_talim.certedusystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDto {

    private int statusCode;
    private String message;
    private LocalDateTime timeStamp;
    private Object object;

    public ResponseDto(int statusCode, String message, LocalDateTime timeStamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
