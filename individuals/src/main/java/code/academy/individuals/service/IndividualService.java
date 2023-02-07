package code.academy.individuals.service;

import code.academy.individuals.model.Individual;
import code.academy.individuals.model.errors.AgencyNotFoundException;
import code.academy.individuals.model.errors.ImpossibleToDeleteException;
import code.academy.individuals.model.errors.IndividualNotFoundException;
import code.academy.individuals.repository.DebtDaoImpl;
import code.academy.individuals.repository.IndividualDaoImpl;
import code.academy.individuals.repository.ReferenceDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualService {

    private final IndividualDaoImpl individualDaoImpl;
    private final AgencyService agencyService;
    private final DebtDaoImpl debtDaoImpl;
    private final ReferenceDaoImpl referenceDaoImpl;

    public IndividualService(IndividualDaoImpl individualDaoImpl, DebtDaoImpl debtDaoImpl,
                             ReferenceDaoImpl referenceDaoImpl, AgencyService agencyService) {
        this.individualDaoImpl = individualDaoImpl;
        this.agencyService = agencyService;
        this.debtDaoImpl = debtDaoImpl;
        this.referenceDaoImpl = referenceDaoImpl;

    }

    public List<Individual> getAll() {
        return individualDaoImpl.getAll();
    }

    public Individual getByID(int id) throws IndividualNotFoundException {
        return individualDaoImpl.getById(id).
                orElseThrow(() -> new IndividualNotFoundException("Individual not found."));
    }

    public void create(Individual individual) throws AgencyNotFoundException {
        agencyService.getByID(individual.getAgencyID());
        individualDaoImpl.create(individual);
    }

    public void updateByID(int id, Individual individual) throws IndividualNotFoundException, AgencyNotFoundException {
        if (individualDaoImpl.getById(id).isPresent()) {
            agencyService.getByID(individual.getAgencyID());
            individualDaoImpl.updateByID(id, individual);
        } else {
            throw new IndividualNotFoundException("Individual not found");
        }
    }


    public void delete(int id) throws IndividualNotFoundException, ImpossibleToDeleteException {
        if (individualDaoImpl.getById(id).isPresent()) {
            if (debtDaoImpl.getByID(id).isPresent() || referenceDaoImpl.getByIndividualID(id).isPresent()) {
                throw new ImpossibleToDeleteException("This individual can not be deleted, because there are" +
                        " records with his individualID in table 'References' or 'Debt'!");
            } else {
                individualDaoImpl.delete(id);
            }
        } else {
            throw new IndividualNotFoundException("Individual not found");
        }

    }
}
