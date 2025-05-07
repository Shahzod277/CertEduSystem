package uz.raqamli_talim.certedusystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raqamli_talim.certedusystem.domain.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Integer id;
    private String name;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }


}
