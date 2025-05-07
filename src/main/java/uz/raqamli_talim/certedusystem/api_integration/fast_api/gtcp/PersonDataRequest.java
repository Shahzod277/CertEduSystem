package uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDataRequest {

    @JsonProperty("pinfl")
    private String pinfl;

    @JsonProperty("document")
    private String document;

    @JsonProperty("birth_date")
    private String birthDate;

    public PersonDataRequest(String pinfl) {
        this.pinfl = pinfl;
    }

    public PersonDataRequest(PinflAndSerialNumber dto) {
        this.pinfl = dto.getPinfl();
        this.document = dto.getSerialAndNumber();
    }
}
