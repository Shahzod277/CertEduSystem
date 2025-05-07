package uz.raqamli_talim.certedusystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raqamli_talim.certedusystem.domain.Role;
import uz.raqamli_talim.certedusystem.domain.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String fullName;
    private String pinfl;
    private String photo;
    private String gender;
    private String givenDate;
    private String currentRole;
    private String citizenship;
    private String nationality;
    private String phoneNumber;
    private String serialAndNumber;
    private String birthDate;
    private Integer universityId;
    private String universityName;
    private String region;
    private String district;
    private String address;
    private List<String> roles = new ArrayList<>();

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.fatherName = user.getFatherName();
        this.fullName = user.getFullName();
        this.givenDate = user.getGivenDate();
        this.currentRole = user.getCurrentRoleName();
        this.phoneNumber = user.getPhoneNumber();
        this.photo = user.getPhoto();
        this.birthDate = user.getBirthDate();
        this.pinfl = user.getPinfl();
        this.serialAndNumber = user.getSerialAndNumber();
//        if (user.getUniversity() != null) {
//            this.universityId = user.getUniversity().getId();
//            this.universityName = user.getUniversity().getNameUz();
//        }
        this.roles = user.getRoles().stream().map(Role::getName).toList();
    }
}
