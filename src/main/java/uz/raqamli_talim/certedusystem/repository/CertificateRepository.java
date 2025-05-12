package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.raqamli_talim.certedusystem.domain.Certificate;
import uz.raqamli_talim.certedusystem.model.projection.CertificateProjection;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    @Query("SELECT c FROM Certificate c " +
            "WHERE c.pinfl =?1")
    List<Certificate> findAllByPinfl(String pinfl);
    @Query(value = "SELECT c.pinfl as pinfl, c.serial_number as serialNumber, c.firt_name as firtName, c.last_name as lastName, c.father_name as fatherName, c.level as level, c.subject as subject, c.subject_id as subjectId, c.ball as ball, c.start_date as startDate, c.end_date as endDate, c.cert_number as certNumber, c.url as url FROM certificate c WHERE (:search IS NULL OR LOWER(CONCAT(c.pinfl, c.firt_name, c.last_name, c.father_name, c.serial_number, c.cert_number)) LIKE LOWER(CONCAT('%', :search, '%')))", nativeQuery = true)

    Page<CertificateProjection> getCertificates(@Param("search") String search, Pageable pageable);


}
