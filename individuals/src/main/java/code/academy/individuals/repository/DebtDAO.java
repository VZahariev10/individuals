package code.academy.individuals.repository;

import code.academy.individuals.model.Debt;

import java.util.List;
import java.util.Optional;

public interface DebtDAO {
    List<Debt> getAll();

    void create(Debt debt);

    Optional<Debt> getByID(int id);

    void updateByID(int id, Debt debt);

    void delete(int id);
}