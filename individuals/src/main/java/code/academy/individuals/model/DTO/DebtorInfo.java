package code.academy.individuals.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DebtorInfo {

    private String individualName;
    private String individualType;

    private BigDecimal amount;
    private LocalDate startDate;

    private String referenceType;
    private String externalRef;

    public DebtorInfo(String individualName, String individualType,
                      BigDecimal amount, LocalDate startDate,
                      String referenceType, String externalRef) {
        this.individualName = individualName;
        this.individualType = individualType;
        this.amount = amount;
        this.startDate = startDate;
        this.referenceType = referenceType;
        this.externalRef = externalRef;
    }

    public DebtorInfo() {
    }

    public String getIndividualName() {
        return individualName;
    }

    public void setIndividualName(String individualName) {
        this.individualName = individualName;
    }

    public String getIndividualType() {
        return individualType;
    }

    public void setIndividualType(String individualType) {
        this.individualType = individualType;
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

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(String externalRef) {
        this.externalRef = externalRef;
    }
}
