package uz.raqamli_talim.certedusystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.raqamli_talim.certedusystem.domain.User;
import uz.raqamli_talim.certedusystem.model.projection.ORGAdminShortInfoProjection;
import uz.raqamli_talim.certedusystem.model.projection.UserInfoProjection;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.pinfl = ?1 and u.isActive = true ")
    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findUserByPinflForSignIn(String pinfl);

    @Query("select u from User u where u.pinfl = ?1 ")
    Optional<User> findByPinfl(String pinfl);

    @Query("select u from User u where u.pinfl = ?1 and u.isActive is true ")
    Optional<User> findActiveUserByPinfl(String pinfl);

    @Query("select (count(u) > 0) from User u where u.phoneNumber = ?1")
    Boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = " select u.id, u.first_name firstName, u.last_name lastName, u.father_name fatherName, " +
            "       u.pinfl, u.phone_number phoneNumber, u.is_active isActive, u.gender, u.citizenship, " +
            "       u.serial_and_number serialAndNumber, u.birth_date birthDate, u.photo, " +
            "       u.given_date givenDate, u.university_id universityId, u.current_role_name currentRoleName" +
            "       from users u where u.pinfl = ?1 and u.is_active is true ", nativeQuery = true)
    Optional<UserInfoProjection> findActiveUserInfoByPinfl(String pinfl);

//    @Query(value = " select distinct u.id, u.pinfl, u.full_name fullName, u.phone_number phoneNumber, " +
//            "       u.university_id universityId, un.name_uz universityName, u.is_active isActive " +
//            " from users u " +
//            " inner join users_role ur on u.id = ur.user_id " +
//            " left join Orga un on u.university_id = un.id " +
//            " where ( ?1 is null or u.university_id = ?1) and ( ?2 is null or u.is_active = ?2) and" +
//            "( ?3 is null or ur.role_id =?3 ) and " +
//            " ( ?4 is null or concat(u.pinfl, u.full_name, u.phone_number, un.name_uz) ilike concat('%',?4,'%')) " +
//            " order by u.id ", nativeQuery = true)
//    Page<UserShortInfoProjection> getAllAdmins(Integer universityId, Boolean isActive, Integer roleId,
//                                               String search, Pageable pageable);

    @Query(value = " select distinct u.id,     to_char(u.created_at, 'YYYY-MM-DD') as createDate, " +
            " u.pinfl, u.full_name fullName, u.phone_number phoneNumber, " +
            "        u.organization_id organizationId, o.name_uz organizationName, " +
            "        u.university_id universityId,un.name_uz universityName, " +
            "        u.is_active isActive\n" +
            "  from users u\n" +
            "  inner join users_role ur on u.id = ur.user_id\n" +
            "  left join organization o on u.organization_id = o.id\n" +
            "  left join university un on u.university_id = un.id where ( ?1 is null or u.organization_id = ?1) and ( ?2 is null or u.university_id = ?2)  and ( ?3 is null or u.is_active = ?3) and " +
            " ( ?4 is null or ur.role_id =?4 ) and " +
            "  ( ?5 is null or concat(u.pinfl, u.full_name, u.phone_number, un.name_uz) ilike concat('%',?5,'%')) ", nativeQuery = true)
    Page<ORGAdminShortInfoProjection> getAllAdmins(Integer organizationId, Integer universityId, Boolean isActive, Integer roleId,
                                                   String search, Pageable pageable);


//    @Query("select u from User u where u.id = ?1 and u.university.id = ?2")
//    Optional<User> findByIdAndUniversityId(Integer id, Integer universityId);

    @Query("select u from User u where u.pinfl = ?1 and u.isActive = true ")
    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findAdminInfoByPinfl(String pinfl);
}
