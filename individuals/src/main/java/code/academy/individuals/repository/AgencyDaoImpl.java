package code.academy.individuals.repository;

import code.academy.individuals.model.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AgencyDaoImpl implements AgencyDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public AgencyDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Agency> getAll() {
        String sql = "SELECT agencyID, name "
                + "       FROM Agency ";

        return namedParameterJdbcOperations.query(sql, new MapSqlParameterSource(),
                (rs, rowNum) -> {
                    Agency agency = new Agency();
                    agency.setAgencyID(rs.getInt("agencyID"));
                    agency.setName(rs.getString("name"));

                    return agency;
                });
    }

    @Override
    public void create(Agency agency) {
        String sql = "INSERT INTO agency (name) "
                + "       VALUES (:name)";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("name", agency.getName()));
    }

    @Override
    public Optional<Agency> getByID(int id) {
        String sql = "SELECT agencyID, name "
                + "       FROM agency "
                + "       WHERE agencyID = :agencyID";
        try {
            return namedParameterJdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource("agencyID", id),
                    (rs, rowNum) -> {
                        Agency agency = new Agency();
                        agency.setName(rs.getString("name"));
                        agency.setAgencyID(rs.getInt("agencyID"));

                        return Optional.of(agency);
                    });
        } catch (EmptyResultDataAccessException emptyResult) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Agency> getByName(String name) {
        String sql = "SELECT agencyID, name "
                + "       FROM agency "
                + "       WHERE name = :name";
        try {
            return namedParameterJdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource("name", name),
                    (rs, rowNum) -> {
                        Agency agency = new Agency();
                        agency.setName(rs.getString("name"));
                        agency.setAgencyID(rs.getInt("agencyID"));

                        return Optional.of(agency);
                    });
        } catch (EmptyResultDataAccessException emptyResult) {
            return Optional.empty();
        }
    }

    @Override
    public void updateByID(int id, Agency agency) {
        String sql = " UPDATE agency "
                + "SET name = :name "
                + "WHERE agencyID = :agencyID ";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("name", agency.getName())
                        .addValue("agencyID", id));

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE "
                + "       FROM agency "
                + "       WHERE agencyID = :agencyID";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("agencyID", id));
    }
}
