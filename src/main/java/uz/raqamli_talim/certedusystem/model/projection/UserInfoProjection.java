package uz.raqamli_talim.certedusystem.model.projection;

public interface UserInfoProjection {
    Integer getId();
    String getPinfl();
    String getFirstName();
    String getLastName();
    String getFatherName();
    String getFullName();
    String getPhoneNumber();
    Boolean getIsActive();
    String getGender();
    String getCitizenship();
    String getNationality();
    String getSerialAndNumber();
    String getBirthDate();
    String getPhoto();
    String getGivenDate();
    String getCurrentRoleName();
    Integer getUniversityId();
    String getUniversityCode();
    String getContractWebType();
}
