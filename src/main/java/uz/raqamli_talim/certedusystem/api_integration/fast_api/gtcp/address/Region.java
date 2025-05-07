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
public class Region{

    @JsonProperty("IdValue")
    private String idValue;

    @JsonProperty("Value")
    private String value;

    @JsonProperty("Id")
    private int id;
}
