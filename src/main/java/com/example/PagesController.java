package com.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
@RequestMapping("/pages")
public class PagesController {

    @GetMapping("/customObject")
    public String getPagesByCustomObject(PageInfo pageInfo) {
        return String.format("GET to pages route. Filter is: %s", pageInfo.getFilter());
    }

    @GetMapping("/individualName")
    public String getPagesByIndividualName(@RequestParam String filter) {
        return String.format("GET to pages route. Filter is: %s", filter);
    }

    @GetMapping("/hashMap")
    public String getPagesByHashMap(WebRequest webRequest) {
        Map<String, String[]> params = webRequest.getParameterMap();
        return String.format("GET to pages route. Filter is: %s", params.get("filter"));
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
