package ru.t1consulting.symbolsoccurrence;

import org.junit.jupiter.api.Test;
import ru.t1consulting.symbolsoccurrence.model.Computer;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    public static String text = "ЙЙЙйййQQqq1 \\/\"";

    @Test
    void countOccurrenceAll() {
        Map<Character,Long> result = Computer.countOccurrenceAll(text);
        Map<Character,Long> map = new HashMap<>();
        map.put('Q',2L);
        map.put('q',2L);
        map.put('Й',3L);
        map.put('й',3L);
        map.put('1',1L);
        map.put(' ',1L);
        map.put('\\',1L);
        map.put('/',1L);
        map.put('\"',1L);

        assertEquals(result, map);

        List<Long> values = result.values().stream().toList();
        List<Long> sortedValues = result.values().stream().sorted(Collections.reverseOrder()).toList();
        assertEquals(sortedValues, values);
    }

    @Test
    void countOccurrenceAllIgnoringCase() {
        Map<Character,Long> result = Computer.countOccurrenceAllIgnoringCase(text);
        Map<Character,Long> map = new HashMap<>();
        map.put('q',4L);
        map.put('й',6L);
        map.put('1',1L);
        map.put(' ',1L);
        map.put('\\',1L);
        map.put('/',1L);
        map.put('\"',1L);

        assertEquals(result, map);

        List<Long> values = result.values().stream().toList();
        List<Long> sortedValues = result.values().stream().sorted(Collections.reverseOrder()).toList();
        assertEquals(sortedValues, values);
    }

    @Test
    void countOccurrenceLettersOnly() {
        Map<Character,Long> result = Computer.countOccurrenceLettersOnly(text);
        Map<Character,Long> map = new HashMap<>();
        map.put('Q',2L);
        map.put('Й',3L);
        map.put('q',2L);
        map.put('й',3L);

        assertEquals(result, map);

        List<Long> values = result.values().stream().toList();
        List<Long> sortedValues = result.values().stream().sorted(Collections.reverseOrder()).toList();
        assertEquals(sortedValues, values);
    }

    @Test
    void countOccurrenceLettersIgnoringCase() {
        Map<Character,Long> result = Computer.countOccurrenceLettersIgnoringCase(text);
        Map<Character,Long> map = new HashMap<>();
        map.put('q',4L);
        map.put('й',6L);

        assertEquals(result, map);

        List<Long> values = result.values().stream().toList();
        List<Long> sortedValues = result.values().stream().sorted(Collections.reverseOrder()).toList();
        assertEquals(sortedValues, values);
    }
}