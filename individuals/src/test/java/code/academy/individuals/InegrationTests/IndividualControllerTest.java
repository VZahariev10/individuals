package code.academy.individuals.InegrationTests;

import code.academy.individuals.model.Individual;
import code.academy.individuals.model.IndividualType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/IndividualTestQueries.sql"})

public class IndividualControllerTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreate_withValidData_ShouldBeSuccessful() throws Exception {
        Individual individual = new Individual();
        individual.setName("Veselin");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-5);
        mockMvc.perform(post("/api/v1/individuals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreate_withInValidAgencyID_ShouldBeSuccessful() throws Exception {
        Individual individual = new Individual();
        individual.setName("Veselin");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-4);
        mockMvc.perform(post("/api/v1/individuals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Agency not found."));
    }

    @Test
    public void testCreate_withShortName_ShouldBeSuccessful() throws Exception {
        Individual individual = new Individual();
        individual.setName("V");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-5);
        mockMvc.perform(post("/api/v1/individuals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //FROM HERE CREATE
    @Test
    public void testGetAllIndividualsMethod() throws Exception {
        mockMvc.perform(get("/api/v1/individuals"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByIDMethod_InCaseOfValidID() throws Exception {
        mockMvc.perform(get("/api/v1/individuals/{id}", -5))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetIndividualByIDMethod_withInvalidID() throws Exception {
        mockMvc.perform(get("/api/v1/individuals/{id}", -10))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Individual not found."));
    }

    //From Here update
    @Test
    public void testUpdateMethod_WithValidData() throws Exception {
        Individual individual = new Individual();
        individual.setName("Veselin");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-5);
        mockMvc.perform(put("/api/v1/individuals/{id}", -5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name").value("Veselin"))

    }

    @Test
    public void testUpdateMethod_ByInputInvalidID() throws Exception {
        Individual individual = new Individual();
        individual.setName("Veselin");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-5);
        mockMvc.perform(put("/api/v1/individuals/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Individual not found"));
    }

    @Test
    public void testUpdateMethod_WithShortName() throws Exception {
        Individual individual = new Individual();
        individual.setName("V");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-5);
        mockMvc.perform(put("/api/v1/individuals/{id}", -5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMethod_WithLongName() throws Exception {
        Individual individual = new Individual();
        individual.setName("VeselinIsNotLongNameButWeCanMakeItToTestTheMethodAndIHopeIt`sLongEnough");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-5);
        mockMvc.perform(put("/api/v1/individuals/{id}", -5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMethod_ByInputInvalidAgencyID() throws Exception {
        Individual individual = new Individual();
        individual.setName("Veselin");
        individual.setType(IndividualType.individual);
        individual.setAgencyID(-4);
        mockMvc.perform(put("/api/v1/individuals/{id}", -5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Agency not found."));;
    }

    //Here Start With Delete
    @Test
    public void testDeleteMethod_WithValidID_WithoutConnectionToDebtOrReference() throws Exception {
        mockMvc.perform(delete("/api/v1/individuals/{id}", -5))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMethod_WithValidID_WithConnectionToReference() throws Exception {
        mockMvc.perform(delete("/api/v1/individuals/{id}", -7))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This individual can not be deleted, " +
                        "because there are records with his individualID in table 'References' or 'Debt'!"));
    }

    @Test
    public void testDeleteMethod_WithValidID_WithConnectionToDebt() throws Exception {
        mockMvc.perform(delete("/api/v1/individuals/{id}", -8))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This individual can not be deleted, " +
                        "because there are records with his individualID in table 'References' or 'Debt'!"));
    }

    @Test
    public void testDeleteMethod_WithInvalidID() throws Exception {
        mockMvc.perform(delete("/api/v1/individuals/{id}", -9))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Individual not found"));
    }
}
//15