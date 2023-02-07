package code.academy.individuals.InegrationTests;

import code.academy.individuals.model.Agency;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/AgencyTestQueries.sql"})
public class AgencyControllerIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Тест и какво тества " _ " ситуация в която го тестваш
    @Test
    public void testGetAllAgenciesMethod() throws Exception {
        mockMvc.perform(get("/api/v1/agency"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByIDMethod() throws Exception {
        mockMvc.perform(get("/api/v1/agency/{id}", -4))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAgencyByIDMethod_withInvalidID() throws Exception {
        mockMvc.perform(get("/api/v1/agency/{id}", -10))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Agency not found."));
    }

    @Test
    public void testCreate_withValidData_ShouldBeSuccessful() throws Exception {
        Agency agency = new Agency();
        agency.setName("NewAgency");
        mockMvc.perform(post("/api/v1/agency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agency)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreate_TooShortName_ShouldBeSuccessful() throws Exception {
        Agency agency = new Agency();
        agency.setName("T");
        mockMvc.perform(post("/api/v1/agency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agency)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreate_OverLoad_ShouldBeSuccessful() throws Exception {
        Agency agency = new Agency();
        agency.setName("TheAgencyWhichNameIsMuchLongerThanWeValidateInTheClass");
        mockMvc.perform(post("/api/v1/agency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agency)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMethod_withValidData_ShouldBeSuccessFull() throws Exception {
        Agency agency = new Agency();
        agency.setName("TestAgency");
        mockMvc.perform(put("/api/v1/agency/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agency)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMethod_withInvalidID() throws Exception {
        Agency agency = new Agency();
        agency.setName("TestAgency");
        mockMvc.perform(put("/api/v1/agency/{id}", -100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agency)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Agency to update not found"));
    }

    @Test
    public void testUpdateMethod_withShortName() throws Exception {
        Agency agency = new Agency();
        agency.setName("T");
        mockMvc.perform(put("/api/v1/agency/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agency)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMethod_WithValidData() throws Exception {
        mockMvc.perform(delete("/api/v1/agency/{id}", -4))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMethod_InCaseWithUsefulID() throws Exception {
        mockMvc.perform(delete("/api/v1/agency/{id}", -6))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("The ID of the agency is now in use in table individuals"));
    }

    @Test
    public void testDeleteMethod_InCaseWithInvalidlID() throws Exception {
        mockMvc.perform(delete("/api/v1/agency/{id}", -123))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Agency not found"));
    }
}
//12