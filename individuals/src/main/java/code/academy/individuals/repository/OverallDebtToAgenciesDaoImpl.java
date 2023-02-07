package code.academy.individuals.repository;

import code.academy.individuals.model.DTO.OverallDebtToAgencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OverallDebtToAgenciesDaoImpl implements OverallDebtToAgenciesDAO {
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public OverallDebtToAgenciesDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<OverallDebtToAgencies> getOverAllDebtSum() {
        String sql = "SELECT a.name, SUM(d.amount) as SumDebt " +
                "FROM agency a " +
                "INNER JOIN individuals i on a.agencyID = i.agencyID " +
                "LEFT JOIN debt d on i.individualID = d.individualID  " +
                "WHERE d.startDate > sysdate " +
                "GROUP by a.name ";

        return namedParameterJdbcOperations.query(sql, new MapSqlParameterSource(),
                (rs, rowNum) -> {
                    OverallDebtToAgencies overallDebtToAgencies = new OverallDebtToAgencies();
                    overallDebtToAgencies.setName(rs.getString("name"));
                    overallDebtToAgencies.setSum(rs.getBigDecimal("sumDebt"));

                    return overallDebtToAgencies;
                });
    }
}
