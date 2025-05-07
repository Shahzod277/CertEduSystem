package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uz.raqamli_talim.certedusystem.domain.Certificate;

import java.util.List;

public interface CertificateRepository extends CrudRepository<Certificate, Long> {
    @Query("SELECT c FROM Certificate c " +
            "WHERE c.pinfl =?1")
    List<Certificate> findAllByPinfl(String pinfl);
}
