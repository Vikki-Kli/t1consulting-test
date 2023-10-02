package ru.t1consulting.symbolsoccurrence.service;

import org.springframework.stereotype.Service;
import ru.t1consulting.symbolsoccurrence.model.Computer;

import java.util.Map;

@Service
public class MainService {

    public Map<Character, Long> countOccurrence(String text, boolean lettersOnly, boolean ignoringCase) {
        if (lettersOnly && ignoringCase) return Computer.countOccurrenceLettersIgnoringCase(text);
        else if (lettersOnly && !ignoringCase) return Computer.countOccurrenceLettersOnly(text);
        else if (!lettersOnly && ignoringCase) return Computer.countOccurrenceAllIgnoringCase(text);
        else return Computer.countOccurrenceAll(text);
    }
}
