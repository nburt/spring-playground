package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONRequestController.class)
public class JSONRequestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testString() throws Exception {
        String json = "{\"data\":{\"people\":[{\"id\":1,\"name\":\"John\"}]}}";

        MockHttpServletRequestBuilder request = post("/jr/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Id: 1, Name: John"));
    }

    static class PersonParams {
        final int id;
        final String name;
        PersonParams(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class PeopleParams {
        final PersonParams[] people;
        PeopleParams(PersonParams[] people) {
            this.people = people;
        }
    }

    static class DataObjectParams {
        final PeopleParams data;
        DataObjectParams(PeopleParams data) {
            this.data = data;
        }
    }

    @Test
    public void testDataSerialize() throws Exception {
        PersonParams person = new PersonParams(1, "John");
        PeopleParams people = new PeopleParams(new PersonParams[] {person});
        DataObjectParams data = new DataObjectParams(people);

        Gson gson = new GsonBuilder().create();

        String json = gson.toJson(data);

        MockHttpServletRequestBuilder request = post("/jr/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Id: 1, Name: John"));
    }

    @Test
    public void testFileFixture() throws Exception {
        String json = getJSON("/jsonTestData.json");

        MockHttpServletRequestBuilder request = post("/jr/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Id: 1, Name: John"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

    @Test
    public void testJsonResponse() throws Exception {
        PersonParams person = new PersonParams(1, "John");
        PeopleParams people = new PeopleParams(new PersonParams[] {person});
        DataObjectParams data = new DataObjectParams(people);

        Gson gson = new GsonBuilder().create();

        String json = gson.toJson(data);

        MockHttpServletRequestBuilder request = post("/jr/peopleResponse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")));
    }

}


