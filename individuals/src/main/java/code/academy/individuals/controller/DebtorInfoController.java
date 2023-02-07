package code.academy.individuals.controller;

import code.academy.individuals.model.DTO.DebtorInfo;
import code.academy.individuals.model.errors.AgencyNotFoundException;
import code.academy.individuals.service.DebtorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class DebtorInfoController {
    private final DebtorInfoService debtorInfoService;

    @Autowired
    public DebtorInfoController(DebtorInfoService debtorInfoService) {
        this.debtorInfoService = debtorInfoService;
    }

    /**
     * A HTTP GET METHOD that gives us information:
     * Debtor`s name, his type (individual or legal),
     * the amount and the date for paying
     * the reference and the external reference;
     * for all individuals registered in existing agency by input the agency`s name.
     *
     * @throws AgencyNotFoundException if we input an incorrect agency`s name.
     */
    @GetMapping("debtor-info/{agencyName}")
    @ResponseStatus(HttpStatus.OK)
    public List<DebtorInfo> getDebtorInfo(@PathVariable String agencyName) throws AgencyNotFoundException {
        return debtorInfoService.getDebtorInfo(agencyName);
    }
}
