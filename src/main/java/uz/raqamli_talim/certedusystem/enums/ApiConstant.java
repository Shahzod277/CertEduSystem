package uz.raqamli_talim.certedusystem.enums;

import lombok.Getter;

@Getter
public enum ApiConstant {

    //fast-api
    FAST_API_USERNAME("certificate_e-edu"),
    FAST_API_PASSWORD("XZXSx85$kM!FR8EW7f#Z"),

    //ONE ID API
    ONE_ID_LOGIN("khm_qabul"),
    ONE_ID_PASSWORD("pmQgROZaRRaNHqdY"),

    //HEMIS INTEGRATION API
    HEMIS_INTEGRATION_API_URL("https://172.18.8.11:8888/api/user/auth"),
    HEMIS_INTEGRATION_API_USERNAME("31407996240030"),
    HEMIS_INTEGRATION_API_PASSWORD("31407996240030"),

    //billing.edu.uz - state
    BILLING_EDU_UZ_LOGIN("centralbank"),
    BILLING_EDU_UZ_PASSWORD("YmFua2N0cmw6I0RUZSQ1XipfXz0qdGlvcl8rNGZra2Vtd28+NDM1ay80a3A1"),

    //billing.e-edu.uz - state
    BILLING_E_EDU_UZ_LOGIN("register_office"),
    BILLING_E_EDU_UZ_PASSWORD("lLQ3h@e9VX$E5YF#o7lZ"),

    //pro-emis.edu.uz
    PROF_EMIS_EDU_UZ_LOGIN("get_contract_data"),
    PROF_EMIS_EDU_UZ_PASSWORD("Uvx20(:\\K<!lYu0R4|te"),

    //HEMIS ISHCHILAR API
    HEMIS_TOKEN_URL("https://ministry.hemis.uz/app/rest/v2/oauth/token");

    private final String value;

    ApiConstant(String value) {
        this.value = value;
    }
}
