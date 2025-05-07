package uz.raqamli_talim.certedusystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String startDate;
    private LocalDate endDate;
    private String certNumber;
    private String url;
}
