package uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MIP2AddressResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("AnswereId")
    private String answereId;

    @JsonProperty("AnswereMessage")
    private String answereMessage;

    @JsonProperty("AnswereComment")
    private String answereComment;

    @JsonProperty("Data")
    private Mip2AddressData data;
}
