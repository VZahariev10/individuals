package code.academy.individuals.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

/**
 * An Object that initialize our individuals
 */
public class Individual {
    /**
     * Unique identifier of the Individual;
     */
    private int individualID;
    /**
     * The name of the individual.
     */
    @Size(min = 4,
            max = 50,
            message = "The length of the name should be between {min} and {max} characters")
    private String name;
    /**
     * Type (legal or individual) of the individual
     */
    @Enumerated(EnumType.STRING)
    private IndividualType type;
    /**
     * The unique identification of the agency that must collect the individual`s debt.
     */

    private int agencyID;

    public Individual(int id, String name, IndividualType type, int agencyID) {
        this.individualID = id;
        this.name = name;
        this.type = type;
        this.agencyID = agencyID;
    }

    public Individual() {
    }

    public int getIndividualID() {
        return individualID;
    }

    public void setIndividualID(int individualID) {
        this.individualID = individualID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IndividualType getType() {
        return type;
    }

    public void setType(IndividualType type) {
        this.type = type;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }
}
