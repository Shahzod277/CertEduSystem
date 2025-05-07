package uz.raqamli_talim.certedusystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    private String pinfl;
    private String phoneNumber;
    private String birthDate;
    private String givenDate;
    private String serialAndNumber;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String photo;
    private Boolean isActive;
    private Integer organizationId;
    private List<Integer> roleIds;
}
