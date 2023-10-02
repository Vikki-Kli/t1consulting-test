package ru.t1consulting.symbolsoccurrence.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Computer {
    private Computer(){}

    public static Map<Character, Long> countOccurrenceAll(String text){
        return groupingAndComparing(text.chars().mapToObj(s -> (char)s));
    }

    public static Map<Character, Long> countOccurrenceAllIgnoringCase(String text){
        return groupingAndComparing(text.toLowerCase().chars().mapToObj(s -> (char)s));
    }

    public static Map<Character, Long> countOccurrenceLettersOnly(String text){
        return groupingAndComparing(text.chars().mapToObj(s -> String.valueOf((char)s))
                .filter(s -> s.matches("[a-zA-Zа-яА-Я]"))
                .map(s -> s.charAt(0)));
    }

    public static Map<Character, Long> countOccurrenceLettersIgnoringCase(String text){
        return groupingAndComparing(text.toLowerCase().chars().mapToObj(s -> String.valueOf((char)s))
                .filter(s -> s.matches("[a-zA-Zа-яА-Я]"))
                .map(s -> s.charAt(0)));
    }

    private static Map<Character, Long> groupingAndComparing(Stream<Character> stream) {
        return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<Character, Long> comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
