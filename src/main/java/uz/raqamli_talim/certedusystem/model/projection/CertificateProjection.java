package uz.raqamli_talim.certedusystem.model.projection;

import java.time.LocalDate;

public interface CertificateProjection {
    String getPinfl();
    String getId();
    String getSerialNumber();
    String getFirtName();
    String getLastName();
    String getFatherName();
    String getLevel();
    String getSubject();
    Integer getSubjectId();
    String getBall();
    LocalDate getStartDate();
    LocalDate getEndDate();
    String getCertNumber();
    String getUrl();
}
