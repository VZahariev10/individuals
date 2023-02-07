package code.academy.individuals.repository;

import code.academy.individuals.model.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DebtDaoImpl implements DebtDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public DebtDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Debt> getAll() {
        String sql = "SELECT individualID, amount, startDate  "
                + "       FROM debt ";

        return namedParameterJdbcOperations.query(sql, new MapSqlParameterSource(),
                (rs, rowNum) -> {
                    Debt debt = new Debt();
                    debt.setIndividualID(rs.getInt("individualID"));
                    debt.setAmount(rs.getBigDecimal("amount"));
                    debt.setStartDate((rs.getDate("startDate")).toLocalDate());

                    return debt;
                });
    }

    @Override
    public void create(Debt debt) {
        String sql = "INSERT INTO debt (individualID, amount, startDate) "
                + "       VALUES (:individualID, :amount, :startDate)";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("individualID", debt.getIndividualID())
                        .addValue("amount", debt.getAmount())
                        .addValue("startDate", debt.getStartDate()));
    }

    @Override
    public Optional<Debt> getByID(int id) {
        String sql = "SELECT individualID, amount, startDate"
                + "       FROM debt "
                + "       WHERE individualID = :individualID";
        try {
            return namedParameterJdbcOperations.queryForObject(sql,
                    new MapSqlParameterSource("individualID", id),
                    (rs, rowNum) -> {
                        Debt debt = new Debt();
                        debt.setIndividualID(rs.getInt("individualID"));
                        debt.setAmount(rs.getBigDecimal("amount"));
                        debt.setStartDate((rs.getDate("startDate").toLocalDate()));

                        return Optional.of(debt);
                    });
        } catch (EmptyResultDataAccessException emptyResult) {
            return Optional.empty();
        }
    }

    @Override
    public void updateByID(int id, Debt debt) {
        String sql = " UPDATE debt "
                + "SET amount = :amount, startDate = :startDate "
                + "WHERE individualID = :individualID ";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("amount", debt.getAmount())
                        .addValue("startDate", debt.getStartDate())
                        .addValue("individualID", id));
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE "
                + "       FROM debt "
                + "       WHERE individualID = :individualID";

        namedParameterJdbcOperations.update(sql,
                new MapSqlParameterSource("individualID", id));
    }
}