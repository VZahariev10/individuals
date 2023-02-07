package code.academy.individuals.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * An Object that characterize our debts
 */
public class Debt {
    /**
     * Unique identifier of the Debtor;
     */
    private int individualID;

    /**
     * The sum of the debtor.
     */
    @Min(10)
    private BigDecimal amount;

    /**
     * The date for paying the debt.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    public Debt(int individualID, BigDecimal amount, LocalDate startDate) {
        this.individualID = individualID;
        this.amount = amount;
        this.startDate = startDate;
    }

    public Debt() {
    }

    public int getIndividualID() {
        return individualID;
    }

    public void setIndividualID(int individualID) {
        this.individualID = individualID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
