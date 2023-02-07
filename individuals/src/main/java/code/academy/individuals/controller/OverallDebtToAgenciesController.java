package code.academy.individuals.controller;

import code.academy.individuals.model.DTO.OverallDebtToAgencies;
import code.academy.individuals.service.OverallDebtToAgenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class OverallDebtToAgenciesController {
    private final OverallDebtToAgenciesService overallDebtToAgenciesService;

    @Autowired
    public OverallDebtToAgenciesController(OverallDebtToAgenciesService overallDebtToAgenciesService) {
        this.overallDebtToAgenciesService = overallDebtToAgenciesService;
    }

    /**
     * A Http GET method that returns us the sum of all debts of every single agency.
     */
    @GetMapping("debt-to-agencies")
    @ResponseStatus(HttpStatus.OK)
    public List<OverallDebtToAgencies> getDebtorInfo() {
        return overallDebtToAgenciesService.getOverAllDebtSum();
    }
}
