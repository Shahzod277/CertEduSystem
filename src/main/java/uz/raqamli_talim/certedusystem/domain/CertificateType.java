package uz.raqamli_talim.certedusystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CertificateType extends AbstractEntity {
private String name;
}
