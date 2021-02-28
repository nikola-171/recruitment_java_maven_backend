package com.example.recruitment.api;

import com.example.recruitment.model.Candidate;
import com.example.recruitment.service.CandidateService;
import com.example.recruitment.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
public class HomeControllerIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private SkillService skillService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void get_all_candidates() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/candidates");
        mockMvc.perform(request).andExpect(status().isOk());

    }

    @Test
    void add_candidate() throws Exception {
        Candidate candidate = new Candidate();
        candidate.setFirst_name("pera");
        candidate.setLast_name("perić");
        candidate.setEmail("pera.peric@gmail.com");
        candidate.setPhone("06589658");
        candidate.setYear_of_birth(1998);
        candidate.setMonth_of_birth(4);
        candidate.setDay_of_birth(3);

        String o = asJsonString(candidate);
        RequestBuilder request = MockMvcRequestBuilders.post("/candidate/addCandidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(o);
        mockMvc.perform(request).andExpect(status().isOk());

        /* error expected */
        candidate.setLast_name("");
        o = asJsonString(candidate);

         request = MockMvcRequestBuilders.post("/candidate/addCandidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(o);
        mockMvc.perform(request).andExpect(status().isBadRequest());

        /* error expected */
        candidate.setFirst_name("");
        o = asJsonString(candidate);

        request = MockMvcRequestBuilders.post("/candidate/addCandidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(o);
        mockMvc.perform(request).andExpect(status().isBadRequest());

        /* error expected */
        candidate.setPhone("065856abg");
        o = asJsonString(candidate);

        request = MockMvcRequestBuilders.post("/candidate/addCandidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(o);
        mockMvc.perform(request).andExpect(status().isBadRequest());

        /* error expected */
        candidate.setYear_of_birth(null);
        o = asJsonString(candidate);

        request = MockMvcRequestBuilders.post("/candidate/addCandidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(o);
        mockMvc.perform(request).andExpect(status().isBadRequest());

    }

    @Test
    void delete_candidate() throws Exception {
        /* not found */
        RequestBuilder request = MockMvcRequestBuilders.get("/candidates/5/delete");
        mockMvc.perform(request).andExpect(status().isNotFound());

    }

    @Test
    void search_candidate() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/candidates/search/?first_name=pera");
        mockMvc.perform(request).andExpect(status().isOk());

    }

    @Test
    void update_candidate() throws Exception {

        Candidate candidate = new Candidate();
        candidate.setFirst_name("pera");
        candidate.setLast_name("perić");
        candidate.setEmail("pera.peric@gmail.com");
        candidate.setPhone("06589658");
        candidate.setYear_of_birth(1998);
        candidate.setMonth_of_birth(4);
        candidate.setDay_of_birth(3);

        String o = asJsonString(candidate);
        RequestBuilder request = MockMvcRequestBuilders.put("/candidate/2/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(o);
        mockMvc.perform(request).andExpect(status().isOk());

    }
}
