package code.academy.individuals.service;

import code.academy.individuals.model.Debt;
import code.academy.individuals.model.References;
import code.academy.individuals.model.errors.DebtNotFoundException;
import code.academy.individuals.model.errors.ImpossibleToCreateException;
import code.academy.individuals.model.errors.IndividualNotFoundException;
import code.academy.individuals.repository.DebtDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtService {
    private final DebtDaoImpl debtDaoImpl;
    private final IndividualService individualService;

    public DebtService(DebtDaoImpl debtDaoImpl, IndividualService individualService) {
        this.debtDaoImpl = debtDaoImpl;
        this.individualService = individualService;
    }

    public List<Debt> getAll() {
        return debtDaoImpl.getAll();
    }

    public Debt getByID(int id) throws DebtNotFoundException {
        return debtDaoImpl.getByID(id)
                .orElseThrow(() -> new DebtNotFoundException("Debt does not exists"));
    }

    public void create(Debt debt) throws IndividualNotFoundException, ImpossibleToCreateException {
        if(debtDaoImpl.getByID(debt.getIndividualID()).isPresent()) {
            throw new ImpossibleToCreateException("The Individual with this ID is registered with a Debt");
        }else{
            individualService.getByID(debt.getIndividualID());
            debtDaoImpl.create(debt);
        }


    }

    public void updateByID(int id, Debt debt) throws DebtNotFoundException {
        if (debtDaoImpl.getByID(id).isPresent()) {
            debtDaoImpl.updateByID(id, debt);
        } else {
            throw new DebtNotFoundException("Debt does not exists!");
        }
    }

    public void delete(int id) throws DebtNotFoundException {
        if (debtDaoImpl.getByID(id).isPresent()) {
            debtDaoImpl.delete(id);
        } else {
            throw new DebtNotFoundException("Debt does not exists!");
        }

    }
}