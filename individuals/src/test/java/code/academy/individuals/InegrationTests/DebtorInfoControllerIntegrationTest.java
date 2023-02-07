package code.academy.individuals.InegrationTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/DebtorInfoTestQueries.sql"})
public class DebtorInfoControllerIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetDebtorInfo_WithExistingAgencyName() throws Exception {
        mockMvc.perform(get("/api/v1/debtor-info/{agencyName}", "Agency4"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDebtorInfo_WithInvalidAgencyName() throws Exception {
        mockMvc.perform(get("/api/v1/debtor-info/{agencyName}", "NqmaTakavaAgenciq"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Agency does not exists!"));
    }
}
//2