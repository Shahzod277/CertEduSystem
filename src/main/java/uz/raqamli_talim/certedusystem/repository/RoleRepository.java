package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.raqamli_talim.certedusystem.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.name = ?1 ")
    Optional<Role> findByName(String roleName);

    @Query(value = "select r.name from users_role ur inner join role r on ur.role_id = r.id " +
            " where ur.user_id = ?1 ", nativeQuery = true)
    List<String> findAllNamesByUserId(Integer userId);

}
