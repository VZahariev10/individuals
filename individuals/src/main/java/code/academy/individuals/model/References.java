package code.academy.individuals.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class References {
    private int individualID;
    @NotNull
    @Size(min = 5,
            max = 25,
            message = "The length of the type should be between {min} and {max} characters!")
    private String type;
    @NotNull
    @Size(min = 5,
            max = 50,
            message = "The length of the username should be between {min} and {max} characters!")
    private String externalRef;

    public References(int individualID, String type, String externalRef) {
        this.individualID = individualID;
        this.type = type;
        this.externalRef = externalRef;
    }

    public References() {
    }

    public int getIndividualID() {
        return individualID;
    }

    public void setIndividualID(int individualID) {
        this.individualID = individualID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(String externalRef) {
        this.externalRef = externalRef;
    }
}
