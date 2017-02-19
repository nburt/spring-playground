package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(TasksController.class)
public class TasksControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testTasksShowIndividualNameEndpoint() throws Exception {
        int taskId = 4;
        this.mvc.perform(get(String.format("/tasks/%d/individualName", taskId)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("GET tasks show with id: %d", taskId)));
    }

    @Test
    public void testTasksShowHashMapEndpoint() throws Exception {
        int taskId = 4;
        this.mvc.perform(get(String.format("/tasks/%d/hashMap", taskId)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("GET tasks show with id: %d", taskId)));
    }

    @Test
    public void testTasksShowCustomObjectEndpoint() throws Exception {
        int taskId = 4;
        this.mvc.perform(get(String.format("/tasks/%d/customObject", taskId)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("GET tasks show with id: %d", taskId)));
    }

}
