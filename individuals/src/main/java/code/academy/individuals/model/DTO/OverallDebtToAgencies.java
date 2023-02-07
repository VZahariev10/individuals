package code.academy.individuals.model.DTO;

import java.math.BigDecimal;

public class OverallDebtToAgencies {
    private String name;
    private BigDecimal sum;

    public OverallDebtToAgencies(String name, BigDecimal sum) {
        this.name = name;
        this.sum = sum;
    }

    public OverallDebtToAgencies() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
