package uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NationalCertificateResponse {
    private int status;
    private int count;
    private String message;
    private List<CertificateData> data;
}
