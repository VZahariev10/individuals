package code.academy.individuals.service;

import code.academy.individuals.model.DTO.OverallDebtToAgencies;
import code.academy.individuals.repository.OverallDebtToAgenciesDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallDebtToAgenciesService {
    private final OverallDebtToAgenciesDaoImpl overallDebtToAgenciesDaoImpl;

    public OverallDebtToAgenciesService(OverallDebtToAgenciesDaoImpl overallDebtToAgenciesDaoImpl) {
        this.overallDebtToAgenciesDaoImpl = overallDebtToAgenciesDaoImpl;
    }

    public List<OverallDebtToAgencies> getOverAllDebtSum() {
        return overallDebtToAgenciesDaoImpl.getOverAllDebtSum();
    }
}
