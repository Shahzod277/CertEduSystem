package uz.raqamli_talim.certedusystem.model.projection;

public interface ORGAdminShortInfoProjection {
    Integer getId();
    String getPinfl();
    String getCreateDate();
    String getFullName();
    String getPhoneNumber();
    Integer getOrganizationyId();
    String getOrganizationName();
    Integer getUniversityId();
    String getuniversityName();
    Boolean getIsActive();
}
