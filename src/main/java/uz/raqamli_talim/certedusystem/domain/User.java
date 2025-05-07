package uz.raqamli_talim.certedusystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raqamli_talim.invest_edu.domain.classificatory.Organization;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_phone_number", columnList = "phone_number", unique = true),
        @Index(name = "idx_users_pinfl", columnList = "pinfl", unique = true),
        @Index(name = "idx_users_current_role_id", columnList = "currentRoleId"),
        @Index(name = "idx_users_university_id", columnList = "university_id")
})
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String fatherName;
    private String fullName;
    private String pinfl;
    private String givenDate;
    private String photo;
    private String birthDate;
    @Column(length = 15)
    private String serialAndNumber;
    @Column(length = 14)
    private String phoneNumber;
    private String password;
    private Integer currentRoleId;
    private String currentRoleName;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER")),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    foreignKey = @ForeignKey(name = "FK_USER_ROLE_ROLE")))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    private String position;
    private Boolean isExpert;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(fatherName, user.fatherName) && Objects.equals(fullName, user.fullName) && Objects.equals(pinfl, user.pinfl) && Objects.equals(givenDate, user.givenDate) && Objects.equals(photo, user.photo) && Objects.equals(birthDate, user.birthDate) && Objects.equals(serialAndNumber, user.serialAndNumber) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(password, user.password) && Objects.equals(currentRoleId, user.currentRoleId) && Objects.equals(currentRoleName, user.currentRoleName) && Objects.equals(roles, user.roles) && Objects.equals(organization, user.organization) && Objects.equals(position, user.position) && Objects.equals(isExpert, user.isExpert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, fatherName, fullName, pinfl, givenDate, photo, birthDate, serialAndNumber, phoneNumber, password, currentRoleId, currentRoleName, roles, organization, position, isExpert);
    }
}
