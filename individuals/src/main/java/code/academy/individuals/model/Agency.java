package code.academy.individuals.model;

import javax.validation.constraints.Size;

/**
 * An Object that represents our agencies.
 */
public class Agency {
    /**
     * The unique identification number of the agency
     */
    private int agencyID;
    /**
     * The name of the agency.
     */
    @Size(min = 2,
            max = 20,
            message = "Agency's name should be between {min} and {max} characters")
    private String name;

    public Agency(int agencyID, String name) {
        this.agencyID = agencyID;
        this.name = name;
    }

    public Agency() {
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
