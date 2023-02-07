package code.academy.individuals.service;

import code.academy.individuals.model.Agency;
import code.academy.individuals.model.errors.AgencyNotFoundException;
import code.academy.individuals.repository.AgencyDaoImpl;
import code.academy.individuals.repository.IndividualDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    private final AgencyDaoImpl agencyDaoimpl;
    private final IndividualDaoImpl individualDaoImpl;

    public AgencyService(AgencyDaoImpl agencyDaoimpl, IndividualDaoImpl individualDaoImpl) {
        this.agencyDaoimpl = agencyDaoimpl;
        this.individualDaoImpl = individualDaoImpl;
    }

    public List<Agency> getAll() {
        return agencyDaoimpl.getAll();
    }

    public Agency getByID(int id) throws AgencyNotFoundException {
        return agencyDaoimpl.getByID(id)
                .orElseThrow(() -> new AgencyNotFoundException("Agency not found."));
    }

    public void create(Agency agency) {
        agencyDaoimpl.create(agency);
    }

    public void updateByID(int id, Agency agency) throws AgencyNotFoundException {
        if (agencyDaoimpl.getByID(id).isPresent()) {
            agencyDaoimpl.updateByID(id, agency);
        } else {
            throw new AgencyNotFoundException("Agency to update not found");
        }
    }

    public void delete(int id) throws AgencyNotFoundException {
        if (agencyDaoimpl.getByID(id).isPresent()) {
            if (individualDaoImpl.getByAgencyId(id).isPresent()) {
                throw new AgencyNotFoundException("The ID of the agency is now in use in table individuals");
            } else {
                agencyDaoimpl.delete(id);
            }
        } else {
            throw new AgencyNotFoundException("Agency not found");
        }
    }
}
