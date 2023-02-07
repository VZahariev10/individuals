package code.academy.individuals.repository;

import code.academy.individuals.model.DTO.DebtorInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DebtorInfoDaoImpl implements DebtorInfoDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public DebtorInfoDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<DebtorInfo> getDebtorInfo(String agencyName) {
        String sql = "select i.name, i.type, d.amount, d.startdate, r.type as reftype, r.externalref " +
                "from agency a " +
                "inner join individuals i on a.agencyid = i.agencyid " +
                "left join debt d on i.individualid = d.individualid " +
                "left join references r on i.individualid = r.individualid " +
                "where a.name = :agencyName ";

        return namedParameterJdbcOperations.query(sql,
                new MapSqlParameterSource("agencyName", agencyName),
                (rs, rowNum) -> {
                    DebtorInfo debtorInfo = new DebtorInfo();
                    debtorInfo.setIndividualName(rs.getString("name"));
                    debtorInfo.setIndividualType(rs.getString("type"));
                    debtorInfo.setAmount(rs.getBigDecimal("amount"));
                    debtorInfo.setStartDate((rs.getDate("startDate").toLocalDate()));
                    debtorInfo.setReferenceType(rs.getString("reftype"));
                    debtorInfo.setExternalRef(rs.getString("externalRef"));

                    return debtorInfo;
                });
    }
}
