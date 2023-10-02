package ru.t1consulting.symbolsoccurrence.controller;

import org.springframework.web.bind.annotation.*;
import ru.t1consulting.symbolsoccurrence.service.MainService;

import java.util.Map;

@RestController
public class MainController {

    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @PostMapping("/occurrence")
    public Map<Character, Long> countOccurrence(@RequestBody String text,
                                                @RequestParam(value = "lettersonly", defaultValue = "false") boolean lettersOnly,
                                                @RequestParam(value = "ignoringcase", defaultValue = "false") boolean ignoringCase) {
        return service.countOccurrence(text, lettersOnly, ignoringCase);
    }
}
