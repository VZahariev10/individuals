package code.academy.individuals.repository;

import code.academy.individuals.model.Individual;
import code.academy.individuals.model.IndividualType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IndividualDaoImpl implements IndividualDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public IndividualDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Individual> getAll() {
        String sql = "SELECT individualID, name, type, agencyID  "
                + "       FROM individuals ";

        return namedParameterJdbcOperations.query(sql, new MapSqlParameterSource(),
                (rs, rowNum) -> {
                    Individual individual = new Individual();
                    individual.setIndividualID(rs.getInt("individualID"));
                    individual.setName(rs.getString("name"));
                    individual.setType(IndividualType.valueOf(rs.getString("type")));
                    individual.setAgencyID(rs.getInt("agencyID"));

                    return individual;
                });
    }

    @Override
    public Optional<Individual> getById(int id) {
        String sql = "SELECT individualID, name, type, agencyID"
                + "       FROM individuals "
                + "       WHERE individualID = :individualID";
        try {
            return namedParameterJdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource("individualID", id),
                    (rs, rowNum) -> {
                        Individual individual = new Individual();
                        individual.setIndividualID(rs.getInt("individualID"));
                        individual.setName(rs.getString("name"));
                        individual.setType(IndividualType.valueOf(rs.getString("type")));
                        individual.setAgencyID(rs.getInt("agencyID"));

                        return Optional.of(individual);
                    });
        } catch (EmptyResultDataAccessException emptyResult) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Individual> getByAgencyId(int id) {
        String sql = "SELECT individualID, name, type, agencyID"
                + "       FROM individuals "
                + "       WHERE agencyID = :agencyID";
        try {
            return namedParameterJdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource("agencyID", id),
                    (rs, rowNum) -> {
                        Individual individual = new Individual();
                        individual.setIndividualID(rs.getInt("individualID"));
                        individual.setName(rs.getString("name"));
                        individual.setType(IndividualType.valueOf(rs.getString("type")));
                        individual.setAgencyID(rs.getInt("agencyID"));

                        return Optional.of(individual);
                    });
        } catch (EmptyResultDataAccessException emptyResult) {
            return Optional.empty();
        }
    }

    @Override
    public void create(Individual individual) {
        String sql = "INSERT INTO individuals (name, type, agencyID) "
                + "       VALUES (:name, :type, :agencyID)";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("name", individual.getName())
                        .addValue("type", individual.getType().toString())
                        .addValue("agencyID", individual.getAgencyID()));

    }

    @Override
    public void updateByID(int id, Individual individual) {
        String sql = " UPDATE individuals "
                + "SET name = :name,type = :type, agencyID = :agencyID "
                + "WHERE individualID = :individualID ";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("name", individual.getName())
                        .addValue("type", individual.getType().toString())
                        .addValue("agencyID", individual.getAgencyID())
                        .addValue("individualID", id));
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE "
                + "       FROM individuals "
                + "       WHERE individualID = :individualID";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("individualID", id));
    }
}
