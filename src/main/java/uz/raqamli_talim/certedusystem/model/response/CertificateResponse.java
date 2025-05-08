package uz.raqamli_talim.certedusystem.model.response;

import lombok.Getter;
import lombok.Setter;
import uz.raqamli_talim.certedusystem.domain.Certificate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CertificateResponse {
    private String pinfl;
    private String serialNumber;
    private String firtName;
    private String lastName;
    private String fatherName;
    private String level;
    private String subject;
    private Integer subject_id;
    private String ball;
    private LocalDate startDate;
    private LocalDate endDate;
    private String certNumber;
    private String url;

    public CertificateResponse(Certificate certificate) {
        this.pinfl = certificate.getPinfl();
        this.serialNumber = certificate.getSerialNumber();
        this.firtName = certificate.getFirtName();
        this.lastName = certificate.getLastName();
        this.fatherName = certificate.getFatherName();
        this.level = certificate.getLevel();
        this.subject = certificate.getSubject();
        this.subject_id = certificate.getSubject_id();
        this.ball = certificate.getBall();
        this.startDate = certificate.getStartDate();
        this.endDate= certificate.getEndDate();
        this.certNumber = certificate.getCertNumber();
        this.url = certificate.getUrl();

    }
}
