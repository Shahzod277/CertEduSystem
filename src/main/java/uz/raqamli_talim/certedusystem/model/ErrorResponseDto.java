package uz.raqamli_talim.certedusystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ErrorResponseDto {

    private int errorCode;
    private String errorMessage;
    private String apiPath;
    private LocalDateTime errorTime;
}
