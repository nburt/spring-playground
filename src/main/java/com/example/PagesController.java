package com.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
public class PagesController {

    @GetMapping(value = {"", "/"})
    public String getIndex() {
        return "GET to pages route";
    }

    @PatchMapping(value = {"", "/"})
    public String patchPage() {
        return "PATCH to pages route";
    }

    @DeleteMapping(value={"", "/"})
    public String deletePage() {
        return "DELETE to pages route";
    }

}
