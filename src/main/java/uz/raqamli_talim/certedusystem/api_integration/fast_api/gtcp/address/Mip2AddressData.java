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
public class Mip2AddressData {

    @JsonProperty("TemproaryRegistrations")
    private Object temproaryRegistrations;

    @JsonProperty("PermanentRegistration")
    private PermanentRegistration permanentRegistration;
}
