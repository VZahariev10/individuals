package code.academy.individuals.controller;

import code.academy.individuals.model.References;
import code.academy.individuals.model.errors.ImpossibleToCreateException;
import code.academy.individuals.model.errors.IndividualNotFoundException;
import code.academy.individuals.model.errors.ReferenceNotFoundException;
import code.academy.individuals.service.ReferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class ReferencesController {
    private final ReferencesService referencesService;

    @Autowired
    public ReferencesController(ReferencesService referencesService) {
        this.referencesService = referencesService;
    }

    /**
     * A Http GET method that provides us with the option of reading
     * all existing references and external references of all individuals.
     */
    @GetMapping("/references")
    @ResponseStatus(HttpStatus.OK)
    public List<References> getAll() {
        return referencesService.getAll();
    }

    /**
     * A Http GET method that provides us with the option of reading
     * references and external references of an individual by input his ID.
     */
    @GetMapping("/references/{id}")
    @ResponseStatus(HttpStatus.OK)
    public References getByIndividualID(@PathVariable int id) throws ReferenceNotFoundException {
        return referencesService.getByIndividualID(id);
    }

    /**
     * A Http POST method that provides us to create new reference for an individual from
     * table INDIVIDUALS.
     */
    @PostMapping("/references")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody References references) throws IndividualNotFoundException, ImpossibleToCreateException {
        referencesService.create(references);
    }

    /**
     * A Http POST method that allows us to update an existing reference by input the
     * IndividualID of the debtor.
     */
    @PutMapping("/references/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateByID(@PathVariable int id, @Valid @RequestBody References references)
            throws ReferenceNotFoundException {
        referencesService.updateByIndividualID(id, references);
    }

    /**
     * A Http DELETE method that provides us to delete an existing reference by the
     * IndividualID of the debtor.
     */
    @DeleteMapping("/references/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByIndividualID(@PathVariable int id) throws ReferenceNotFoundException {
        referencesService.deleteByIndividualID(id);
    }
}
