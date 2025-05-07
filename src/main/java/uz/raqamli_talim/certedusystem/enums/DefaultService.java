package uz.raqamli_talim.certedusystem.enums;

import lombok.Getter;

@Getter
public enum DefaultService {

    TEMPLATE(4);
    private final Integer message;

    DefaultService(Integer message) {
        this.message = message;
    }

}
