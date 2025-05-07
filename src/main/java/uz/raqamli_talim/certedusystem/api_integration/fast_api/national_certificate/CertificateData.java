package uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateData {
    private long imie;
    private String psser;
    private int psnum;
    private String lname;
    private String fname;
    private String mname;
    private String level;
    private String subject;
    private Integer subject_id;
    private String ball;
    private String start_date;
    private String end_date;
    private String ser_num;
    private String hash;
}
