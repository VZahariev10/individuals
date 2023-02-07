package code.academy.individuals.repository;

import code.academy.individuals.model.DTO.DebtorInfo;

import java.util.List;

public interface DebtorInfoDAO {
    List<DebtorInfo> getDebtorInfo(String agencyName);

}
