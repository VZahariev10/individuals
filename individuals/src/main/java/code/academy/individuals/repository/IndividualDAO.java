package code.academy.individuals.repository;

import code.academy.individuals.model.Individual;

import java.util.List;
import java.util.Optional;

public interface IndividualDAO {

    List<Individual> getAll();

    Optional<Individual> getById(int id);

    Optional<Individual> getByAgencyId(int id);

    void create(Individual individual);

    void updateByID(int id, Individual individual);

    void delete(int id);


}
