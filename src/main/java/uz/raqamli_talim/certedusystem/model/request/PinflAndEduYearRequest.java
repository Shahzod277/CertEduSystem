package uz.raqamli_talim.certedusystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PinflAndEduYearRequest {
    private String pinfl;
    private String eduYearCode;
}
