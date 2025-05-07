package uz.raqamli_talim.certedusystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentRequest {
    private Integer id;
    private Integer sectorId;
    private Integer subSectorId;
    private Integer countryId;
    private Integer internationalFundId;
    private Integer profEduId;
    private BigDecimal sum;
    private BigDecimal plannedSum;
    private String url;
    private String description;
    private String currencyCode;
    private String contractNumber;
    private String contractDate;
}
