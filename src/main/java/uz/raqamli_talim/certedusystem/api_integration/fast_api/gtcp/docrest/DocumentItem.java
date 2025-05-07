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
public class DocumentItem {

    @JsonProperty("docgiveplace")
    private String docgiveplace;

    @JsonProperty("datebegin")
    private String datebegin;

    @JsonProperty("document")
    private String document;

    @JsonProperty("dateend")
    private String dateend;

    @JsonProperty("type")
    private String type;

    @JsonProperty("docgiveplaceid")
    private int docgiveplaceid;

    @JsonProperty("status")
    private int status;

}