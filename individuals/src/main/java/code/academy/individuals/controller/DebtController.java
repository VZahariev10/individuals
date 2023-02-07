package code.academy.individuals.controller;

import code.academy.individuals.model.Debt;
import code.academy.individuals.model.errors.DebtNotFoundException;
import code.academy.individuals.model.errors.ImpossibleToCreateException;
import code.academy.individuals.model.errors.IndividualNotFoundException;
import code.academy.individuals.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class DebtController {
    private final DebtService debtService;

    @Autowired
    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    /**
     * A Http GET method that provides us with the option of reading
     * all existing debts with their specifications as
     * IndividualID of the debtor;
     * The Amount;
     * The Date for paying the sum.
     */
    @GetMapping("/debt")
    @ResponseStatus(HttpStatus.OK)
    public List<Debt> getAll() {
        return debtService.getAll();
    }

    /**
     * A Http GET method that shows us the debt and the date of a debtor
     * by input his IndividualID.
     */
    @GetMapping("/debt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Debt getById(@PathVariable int id) throws DebtNotFoundException {
        return debtService.getByID(id);
    }

    /**
     * A Http POST method that permits us to create a new debt for Individual
     * who exists in the table "INDIVIDUALS".
     */
    @PostMapping("/debt")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Debt debt) throws IndividualNotFoundException, ImpossibleToCreateException {
        debtService.create(debt);
    }

    /**
     * A Http POST method that allows us to update an existing debt by input the
     * IndividualID of the debtor.
     */
    @PutMapping("/debt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateByID(@PathVariable int id, @Valid @RequestBody Debt debt) throws DebtNotFoundException {
        debtService.updateByID(id, debt);
    }

    /**
     * A Http DELETE method that provides us to delete an existing debt by the
     * IndividualID of the debtor.
     */
    @DeleteMapping("/debt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) throws DebtNotFoundException {
        debtService.delete(id);
    }
}