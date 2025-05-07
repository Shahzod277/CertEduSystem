package uz.raqamli_talim.certedusystem.model.projection;


import java.math.BigDecimal;

public interface InvestmentYearlyInfoProjection {
    Integer getId();
    String getDescription();
    String getUrl();
    BigDecimal getTotal();
    int getYear();
    Integer getMonthId();
    String getMonth();
    Integer getStatusId();
    String getStatus();
    Integer getOrganizationId();
    String getOrganization();

  }
