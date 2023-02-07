package code.academy.individuals.controller;

import code.academy.individuals.model.Agency;
import code.academy.individuals.model.errors.AgencyNotFoundException;
import code.academy.individuals.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class AgencyController {
    private final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    /**
     * A Http GET method that provides us with the option of reading
     * all existing agencies with their agencyID and names.
     */
    @GetMapping("agency")
    @ResponseStatus(HttpStatus.OK)
    public List<Agency> getAll() {
        return agencyService.getAll();
    }

    /**
     * A Http GET method that shows us a single name and ID of an agency
     * searched by it`s ID.
     */
    @GetMapping("agency/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agency getById(@PathVariable int id) throws AgencyNotFoundException {
        return agencyService.getByID(id);
    }

    /**
     * A Http POST method that provides us to create a new agency.
     */
    @PostMapping("agency")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Agency agency) {
        agencyService.create(agency);
    }

    /**
     * A Http PUT method that provides us to update the name of existing agency.
     */
    @PutMapping("/agency/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateByID(@PathVariable int id, @Valid @RequestBody Agency agency) throws AgencyNotFoundException {
        agencyService.updateByID(id, agency);
    }

    /**
     * A Http DELETE method that provides us to delete an existing agency.
     */

    @DeleteMapping("agency/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) throws AgencyNotFoundException {
        agencyService.delete(id);
    }
}
