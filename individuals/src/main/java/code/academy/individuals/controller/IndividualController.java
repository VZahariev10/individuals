package code.academy.individuals.controller;

import code.academy.individuals.model.Individual;
import code.academy.individuals.model.errors.AgencyNotFoundException;
import code.academy.individuals.model.errors.ImpossibleToDeleteException;
import code.academy.individuals.model.errors.IndividualNotFoundException;
import code.academy.individuals.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class IndividualController {

    private final IndividualService individualService;

    @Autowired
    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    /**
     * A Http GET method that provides us with the option of reading
     * all existing individuals with their individualID, name,
     * type (individual or legal) and the agencyID of the agency, that
     * must collect their debt.
     */
    @GetMapping("/individuals")
    @ResponseStatus(HttpStatus.OK)
    public List<Individual> getAll() {
        return individualService.getAll();
    }

    /**
     * A Http GET method that shows us the information of an individual:
     * individualID, name,
     * type (individual or legal) and the agencyID of the agency, that
     * must collect his debt,
     * searched by the individualID of the individual.
     */
    @GetMapping("/individuals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Individual getById(@PathVariable int id) throws IndividualNotFoundException {
        return individualService.getByID(id);
    }

    /**
     * A Http POST method that provides us to create a new individual.
     */
    @PostMapping("/individuals")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Individual individual) throws AgencyNotFoundException {
        individualService.create(individual);
    }

    /**
     * A Http PUT method that provides us to update
     * the name, the type (individual or legal) and the agency,
     * that must collect the of existing individual.
     */
    @PutMapping("/individuals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateByID(@PathVariable int id, @Valid @RequestBody Individual individual)
            throws IndividualNotFoundException, AgencyNotFoundException {

        individualService.updateByID(id, individual);
    }

    /**
     * A Http DELETE method that provides us to remove an existing individual.
     */
    @DeleteMapping("/individuals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) throws IndividualNotFoundException, ImpossibleToDeleteException {
        individualService.delete(id);
    }
}
