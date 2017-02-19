package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @GetMapping("/{taskId}/individualName")
    public String getTaskIndividualName(@PathVariable int taskId) {
        return String.format("GET tasks show with id: %d", taskId);
    }

    @GetMapping("/{taskId}/hashMap")
    public String getTaskCustomObject(@PathVariable Map pathVariables) {
        String taskId = (String) pathVariables.get("taskId");
        return String.format("GET tasks show with id: %s", taskId);
    }
    @GetMapping("/{taskId}/customObject")
    public String getTaskHashMap(TaskIds taskIds) {
        return String.format("GET tasks show with id: %d", taskIds.getTaskId());
    }

}
