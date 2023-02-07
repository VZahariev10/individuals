package code.academy.individuals.repository;

import code.academy.individuals.model.References;

import java.util.List;
import java.util.Optional;

public interface ReferenceDAO {

    List<References> getAll();

    void create(References references);

    Optional<References> getByIndividualID(int id);

    void updateByIndividualID(int id, References references);

    void deleteByIndividualID(int id);
}
