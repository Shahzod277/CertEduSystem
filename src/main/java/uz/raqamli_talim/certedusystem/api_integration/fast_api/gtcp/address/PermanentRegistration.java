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
public class PermanentRegistration{

    @JsonProperty("Cadastre")
    private String cadastre;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("RegistrationDate")
    private String registrationDate;

    @JsonProperty("Country")
    private Country country;

    @JsonProperty("Region")
    private Region region;

    @JsonProperty("District")
    private District district;
}
