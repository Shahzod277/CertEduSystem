package uz.raqamli_talim.certedusystem.model.projection;

import java.math.BigDecimal;

public interface InvestByRegionProfAndHigher {
    String getRegionName();
    BigDecimal getProf();
    BigDecimal getHigher();
}
