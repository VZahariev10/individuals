package code.academy.individuals.repository;

import code.academy.individuals.model.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyDAO {

    List<Agency> getAll();

    void create(Agency agency);

    Optional<Agency> getByID(int id);

    Optional<Agency> getByName(String name);

    void updateByID(int id, Agency agency);

    void delete(int id);
}
