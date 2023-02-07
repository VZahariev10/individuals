package code.academy.individuals.service;

import code.academy.individuals.model.DTO.DebtorInfo;
import code.academy.individuals.model.errors.AgencyNotFoundException;
import code.academy.individuals.repository.AgencyDaoImpl;
import code.academy.individuals.repository.DebtorInfoDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtorInfoService {
    private final DebtorInfoDaoImpl debtorInfoDaoImpl;
    private final AgencyDaoImpl agencyDaoImpl;

    public DebtorInfoService(DebtorInfoDaoImpl debtorInfoDaoImpl, AgencyDaoImpl agencyDaoImpl) {
        this.debtorInfoDaoImpl = debtorInfoDaoImpl;
        this.agencyDaoImpl = agencyDaoImpl;
    }

    public List<DebtorInfo> getDebtorInfo(String agencyName) throws AgencyNotFoundException {
        if (agencyDaoImpl.getByName(agencyName).isPresent()) {
            if (debtorInfoDaoImpl.getDebtorInfo(agencyName).isEmpty()) {
                throw new AgencyNotFoundException("No data available!");
            } else {
                return debtorInfoDaoImpl.getDebtorInfo(agencyName);
            }
        } else {
            throw new AgencyNotFoundException("Agency does not exists!");
        }

    }
}
