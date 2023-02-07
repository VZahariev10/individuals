package code.academy.individuals.InegrationTests;

import code.academy.individuals.model.References;
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
@Sql(scripts = {"/ReferencesTestQueries.sql"})

public class ReferencesControllerTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * CREATE TESTS
     *
     * @throws Exception
     */

    @Test
    public void testCreate_withValidData_ShouldBeSuccessful() throws Exception {
        References references = new References();
        references.setIndividualID(-95);
        references.setType("TestAgency");
        references.setExternalRef("TestAgency");
        mockMvc.perform(post("/api/v1/references")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void testCreate_withUsedId_ShouldBeSuccessful() throws Exception {
        References references = new References();
        references.setIndividualID(-4);
        references.setType("TestAgency");
        references.setExternalRef("TestAgency");
        mockMvc.perform(post("/api/v1/references")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Individual has reference"));
    }

    @Test
    public void testCreate_withShortExternalRef_ShouldBeSuccessful() throws Exception {
        References references = new References();
        references.setIndividualID(-98);
        references.setType("TestAgency");
        references.setExternalRef("T");
        mockMvc.perform(post("/api/v1/references")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreate_withShortType_ShouldBeSuccessful() throws Exception {
        References references = new References();
        references.setIndividualID(-8);
        references.setType("a");
        references.setExternalRef("TestAgency");
        mockMvc.perform(post("/api/v1/references")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Get Tests
     *
     * @throws Exception
     */
    @Test
    public void testGetAllReferencesMethod() throws Exception {
        mockMvc.perform(get("/api/v1/references"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReferenceByIndividualIDMethod() throws Exception {
        mockMvc.perform(get("/api/v1/references/{id}", -4))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReferenceByIndividualIDMethod_withInvalidID() throws Exception {
        mockMvc.perform(get("/api/v1/references/{id}", -9))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Reference not found."));
    }

    @Test
    public void testUpdateMethod_WithValidData() throws Exception {
        References references = new References();
        references.setType("TestAgency");
        references.setExternalRef("TestAgency");
        mockMvc.perform(put("/api/v1/references/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //here is a problem to handle exception with the length of the type and external Ref
    @Test
    public void testUpdateMethod_WithLongType() throws Exception {
        References references = new References();
        references.setType("ShuShuMuShuShuShuMuShuShuShuMuShuShuShuMuShuShuShuMuShu");
        references.setExternalRef("TestAgency");
        mockMvc.perform(put("/api/v1/references/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMethod_withShortExternalRef() throws Exception {
        References references = new References();
        references.setType("TestAgency");
        references.setExternalRef("a");
        mockMvc.perform(put("/api/v1/references/{id}", -4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(references)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //to here
    @Test
    public void testDeleteMethod_WithValidID() throws Exception {
        mockMvc.perform(delete("/api/v1/references/{id}", -4))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMethod_InCaseWithInvalidID() throws Exception {
        mockMvc.perform(delete("/api/v1/references/{id}", -65))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Reference not found."));
    }
}
//11