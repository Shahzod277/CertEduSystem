package uz.raqamli_talim.certedusystem.model.projection;

public interface UserShortInfoProjection {
    Integer getId();
    String getPinfl();
    String getFullName();
    String getPhoneNumber();
    Integer getUniversityId();
    String getUniversityName();
    Boolean getIsActive();
}
