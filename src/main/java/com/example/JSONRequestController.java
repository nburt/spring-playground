package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jr")
public class JSONRequestController {

    @PostMapping("/people")
    public String createPeople(@RequestBody DataObject data) {
        return String.format("Id: %d, Name: %s",  data.getData().getPeople()[0].getId(), data.getData().getPeople()[0].getName());
    }

    @PostMapping("peopleResponse")
    public Person[] createPeopleWithResponse(@RequestBody DataObject data) {
        return data.getData().getPeople();
    }

}
