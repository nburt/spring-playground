package com.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class FormDataController {

    @PostMapping(value = "/customObject", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createCommentCustomObject(Comment comment) {
        return String.format("%s said %s", comment.getAuthor(), comment.getContent());
    }

    @PostMapping("/strings")
    public String createCommentString(@RequestBody String rawBody) {
        return rawBody;
    }

    @PostMapping(value = "/hashMap", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createCommentHashMap(@RequestParam Map<String, String> body) {
        return String.format("%s said %s", body.get("author"), body.get("content"));
    }

}
