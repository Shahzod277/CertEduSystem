package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.repository.CrudRepository;
import uz.raqamli_talim.certedusystem.domain.CertificateType;

public interface CertificateTypeRepository extends CrudRepository<CertificateType, Long> {
}
