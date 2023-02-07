package code.academy.individuals.model.errors;

public class ImpossibleToDeleteException extends Exception {
    public ImpossibleToDeleteException(String string) {
        super((string));
    }
}
