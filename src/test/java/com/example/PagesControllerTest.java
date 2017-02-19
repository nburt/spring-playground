package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(PagesController.class)
public class PagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetPagesByCustomObjectEndpoint() throws Exception {
        this.mvc.perform(get("/pages/customObject?filter=new").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("GET to pages route. Filter is: new"));

    }

    @Test
    public void testGetPagesByIndividualNameEndpoint() throws Exception {
        this.mvc.perform(get("/pages/individualName?filter=new").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("GET to pages route. Filter is: new"));

    }

    @Test
    public void testGetPagesByHashMapEndpoint() throws Exception {
        this.mvc.perform(get("/pages/hashMap?filter=new").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("GET to pages route. Filter is: new"));

    }

    @Test
    public void testPagesPatchEndpoint() throws Exception {
        this.mvc.perform(patch("/pages").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("PATCH to pages route"));
    }

    @Test
    public void testPagesDeleteEndpoint() throws Exception {
        this.mvc.perform(delete("/pages").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("DELETE to pages route"));
    }

}
