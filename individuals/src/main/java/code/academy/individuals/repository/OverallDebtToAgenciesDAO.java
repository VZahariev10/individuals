package code.academy.individuals.repository;

import code.academy.individuals.model.DTO.OverallDebtToAgencies;

import java.util.List;

public interface OverallDebtToAgenciesDAO {
    List<OverallDebtToAgencies> getOverAllDebtSum();
}
