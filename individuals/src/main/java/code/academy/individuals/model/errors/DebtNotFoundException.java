package code.academy.individuals.model.errors;

public class DebtNotFoundException extends Exception {
    public DebtNotFoundException(String string) {
        super((string));
    }
}
