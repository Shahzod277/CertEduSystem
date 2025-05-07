package uz.raqamli_talim.certedusystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raqamli_talim.certedusystem.domain.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {
    private Integer id;
    private String phoneNumber;
    private String pinfl;
    private String serialAndNumber;
    private String fullName;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String birthDate;
    private String currentRole;
    private Boolean isActive;
    private Integer universityId;
    private String university;
    private String givenDate;
    private String createDate;

    private Integer organizationId;
    private String organization;
    private String photoUrl;
    private List<Integer> roleIds;
    private String role;
    private List<RoleResponse> roles = new ArrayList<>();

    public AdminResponse(Integer id, String pinfl, String phoneNumber, String fullName, Boolean isActive, String role, String university, String organization,String createDate) {
        this.id = id;
        this.pinfl = pinfl;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.isActive = isActive;
        this.role = role;
        this.university = university;
        this.organization = organization;
        this.createDate = createDate;
    }


    public AdminResponse(User user) {
        this.id = user.getId();
        this.phoneNumber = user.getPhoneNumber();
        this.pinfl = user.getPinfl();
        this.serialAndNumber = user.getSerialAndNumber();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.fatherName = user.getFatherName();
        this.fullName = user.getFullName();
        this.birthDate = user.getBirthDate();
        this.currentRole = user.getCurrentRoleName();
        this.givenDate = user.getGivenDate();
        this.isActive = user.getIsActive();
//        if (user.getUniversity() != null) {
//            this.universityId = user.getUniversity().getId();
//            this.university = user.getUniversity().getNameUz();
//        }
//        if (user.getOrganization() != null) {
//            this.organizationId = user.getOrganization().getId();
//            this.organization = user.getOrganization().getNameUz();
//        }
        this.photoUrl = user.getPhoto();
        this.roles = user.getRoles().stream().map(RoleResponse::new).toList();
    }
}
