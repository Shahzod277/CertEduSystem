package uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.docrest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDataResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("data")
    private PersonData data;

    @JsonProperty("message")
    private String message;

}