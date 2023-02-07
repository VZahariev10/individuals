package code.academy.individuals.service;

import code.academy.individuals.model.References;
import code.academy.individuals.model.errors.ImpossibleToCreateException;
import code.academy.individuals.model.errors.IndividualNotFoundException;
import code.academy.individuals.model.errors.ReferenceNotFoundException;
import code.academy.individuals.repository.ReferenceDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReferencesService {
    private final ReferenceDaoImpl referenceDaoImpl;
    private final IndividualService individualService;

    public ReferencesService(ReferenceDaoImpl referenceDaoImpl, IndividualService individualService) {
        this.referenceDaoImpl = referenceDaoImpl;
        this.individualService = individualService;
    }

    public List<References> getAll() {
        return referenceDaoImpl.getAll();
    }

    public References getByIndividualID(int id) throws ReferenceNotFoundException {
        return referenceDaoImpl.getByIndividualID(id)
                .orElseThrow(() -> new ReferenceNotFoundException("Reference not found."));
    }

    public void create(References references) throws IndividualNotFoundException, ImpossibleToCreateException {
        if (referenceDaoImpl.getByIndividualID(references.getIndividualID()).isPresent()) {
            throw new ImpossibleToCreateException("Individual has reference");
        } else {
            individualService.getByID(references.getIndividualID());
            referenceDaoImpl.create(references);
        }
    }


    public void updateByIndividualID(int id, References references) throws ReferenceNotFoundException {
        if (referenceDaoImpl.getByIndividualID(id).isPresent()) {
            referenceDaoImpl.updateByIndividualID(id, references);
        } else {
            throw new ReferenceNotFoundException("Reference not found.");
        }
    }

    public void deleteByIndividualID(int id) throws ReferenceNotFoundException {
        if (referenceDaoImpl.getByIndividualID(id).isPresent()) {
            referenceDaoImpl.deleteByIndividualID(id);
        } else {
            throw new ReferenceNotFoundException("Reference not found.");
        }

    }
}
