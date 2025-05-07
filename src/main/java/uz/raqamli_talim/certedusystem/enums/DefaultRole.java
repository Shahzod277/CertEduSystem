package uz.raqamli_talim.certedusystem.enums;

import lombok.Getter;

@Getter
public enum DefaultRole {

    ROLE_SUPER_ADMIN("ROLE_SUPERADMIN"),
    ROLE_OTM_ADMIN("ROLE_OTMADMIN"),
    ROLE_OPERATOR("ROLE_OPERATOR");
    private final String message;

    DefaultRole(String message) {
        this.message = message;
    }

}
