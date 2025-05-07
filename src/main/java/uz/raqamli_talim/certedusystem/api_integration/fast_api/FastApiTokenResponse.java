package uz.raqamli_talim.certedusystem.api_integration.fast_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FastApiTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("refresh_token_expires")
    private int refreshTokenExpires;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("access_token_expires")
    private int accessTokenExpires;

}
