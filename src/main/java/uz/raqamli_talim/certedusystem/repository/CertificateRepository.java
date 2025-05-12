package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.raqamli_talim.certedusystem.domain.Certificate;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    @Query("SELECT c FROM Certificate c " +
            "WHERE c.pinfl =?1")
    List<Certificate> findAllByPinfl(String pinfl);

    @Query("""
    SELECT c FROM Certificate c
    WHERE (:search IS NULL OR
           LOWER(CONCAT(c.pinfl, c.firtName, c.lastName, c.fatherName, c.serialNumber, c.certNumber))
           LIKE LOWER(CONCAT('%', :search, '%')))
""")
    Page<Certificate> getCertificates(@Param("search") String search, Pageable pageable);




}
