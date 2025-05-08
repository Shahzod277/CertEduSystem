package uz.raqamli_talim.certedusystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.CertificateData;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.NationalCertificateResponse;

import java.time.LocalDate;
import java.util.Objects;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Certificate extends AbstractEntity {
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

    @ManyToOne
    private CertificateType certificateType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(pinfl, that.pinfl) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(firtName, that.firtName) && Objects.equals(lastName, that.lastName) && Objects.equals(fatherName, that.fatherName) && Objects.equals(level, that.level) && Objects.equals(subject, that.subject) && Objects.equals(subject_id, that.subject_id) && Objects.equals(ball, that.ball) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(certNumber, that.certNumber) && Objects.equals(url, that.url) && Objects.equals(certificateType, that.certificateType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinfl, serialNumber, firtName, lastName, fatherName, level, subject, subject_id, ball, startDate, endDate, certNumber, url, certificateType);
    }
}
