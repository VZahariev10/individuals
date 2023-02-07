package code.academy.individuals.InegrationTests;


import code.academy.individuals.model.Debt;
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

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/DebtTestQueries.sql"})

public class DebtControllerIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Here start create tests
    @Test
    public void testCreate_withValidData_ShouldBeSuccessful() throws Exception {
        Debt debt = new Debt();
        debt.setIndividualID(-12);
        debt.setAmount(BigDecimal.valueOf(2342.43));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(post("/api/v1/debt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    //newOne
    @Test
    public void testCreate_withUsedId_ShouldBeSuccessful() throws Exception {
        Debt debt = new Debt();
        debt.setIndividualID(-4);
        debt.setAmount(BigDecimal.valueOf(2342.43));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(post("/api/v1/debt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The Individual with this ID is registered with a Debt"));
    }

    @Test
    public void testCreate_withInvalidIndividualID_ShouldBeSuccessful() throws Exception {
        Debt debt = new Debt();
        debt.setIndividualID(-5);
        debt.setAmount(BigDecimal.valueOf(2342.43));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(post("/api/v1/debt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Individual not found."));
    }

    @Test
    public void testCreate_withTooSmallAmount_ShouldBeSuccessful() throws Exception {
        Debt debt = new Debt();
        debt.setIndividualID(-4);
        debt.setAmount(BigDecimal.valueOf(8));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(post("/api/v1/debt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


//FROM HERE WE START GET TESTS

    @Test
    public void testGetAllDebtMethod() throws Exception {
        mockMvc.perform(get("/api/v1/debt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByIDMethod() throws Exception {
        mockMvc.perform(get("/api/v1/debt/{id}", -4))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDebtByIDMethod_withInvalidID() throws Exception {
        mockMvc.perform(get("/api/v1/debt/{id}", -5))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Debt does not exists"));
    }

    //Here start with Update Tests

    @Test
    public void testUpdateMethod_WithValidData() throws Exception {
        Debt debt = new Debt();
        debt.setAmount(BigDecimal.valueOf(2342.43));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(put("/api/v1/debt/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMethod_InvalidIndividualID() throws Exception {
        Debt debt = new Debt();
        debt.setAmount(BigDecimal.valueOf(2342.43));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(put("/api/v1/debt/{id}", -5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Debt does not exists!"));
    }

    @Test
    public void testUpdateMethod_TooSmallAmount() throws Exception {
        Debt debt = new Debt();
        debt.setAmount(BigDecimal.valueOf(1));
        debt.setStartDate(LocalDate.parse("2022-11-16"));
        mockMvc.perform(put("/api/v1/debt/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debt)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
//here we start DELETE test

    @Test
    public void testDeleteMethod_WithValidID() throws Exception {
        mockMvc.perform(delete("/api/v1/debt/{id}", -4))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMethod_withInvalidID() throws Exception {
        mockMvc.perform(delete("/api/v1/debt/{id}", -6))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Debt does not exists!"));
    }
}
//11