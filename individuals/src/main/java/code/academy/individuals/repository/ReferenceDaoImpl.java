package code.academy.individuals.repository;

import code.academy.individuals.model.References;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ReferenceDaoImpl implements ReferenceDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public ReferenceDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<References> getAll() {
        String sql = "SELECT *"
                + "       FROM references ";

        return namedParameterJdbcOperations.query(sql, new MapSqlParameterSource(),
                (rs, rowNum) -> {
                    References references = new References();
                    references.setIndividualID(rs.getInt("individualID"));
                    references.setType(rs.getString("type"));
                    references.setExternalRef(rs.getString("externalRef"));


                    return references;
                });
    }

    @Override
    public void create(References references) {
        String sql = "INSERT INTO references (individualID, type, externalRef) "
                + "       VALUES (:individualID, :type, :externalRef)";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("individualID", references.getIndividualID())
                        .addValue("type", references.getType())
                        .addValue("externalRef", references.getExternalRef()));
    }

    @Override
    public Optional<References> getByIndividualID(int id) {
        String sql = "SELECT individualID, type, externalRef  "
                + "       FROM references "
                + "       WHERE individualID = :individualID";
        try {


            return namedParameterJdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource("individualID", id),
                    (rs, rowNum) -> {
                        References references = new References();
                        references.setIndividualID(rs.getInt("individualID"));
                        references.setType(rs.getString("type"));
                        references.setExternalRef(rs.getString("externalRef"));

                        return Optional.of(references);
                    });
        } catch (EmptyResultDataAccessException emptyResult) {
            return Optional.empty();
        }
    }

    @Override
    public void updateByIndividualID(int id, References references) {
        String sql =
                " UPDATE references "
                        + "    SET type = :type, externalRef = :externalRef "
                        + "  WHERE individualID = :individualID ";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("type", references.getType())
                        .addValue("externalRef", references.getExternalRef())
                        .addValue("individualID", id));
    }

    @Override
    public void deleteByIndividualID(int id) {
        String sql = "DELETE "
                + "       FROM references "
                + "       WHERE individualID = :individualID";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("individualID", id));
    }
}

