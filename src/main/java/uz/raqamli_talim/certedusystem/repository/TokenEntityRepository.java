package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.raqamli_talim.certedusystem.domain.TokenEntity;

import java.util.Optional;


public interface TokenEntityRepository extends JpaRepository<TokenEntity, Integer> {
    @Query("select t from TokenEntity t where t.orgName = :name ")
    Optional<TokenEntity> findByOrgName(@Param("name") String name);
}
