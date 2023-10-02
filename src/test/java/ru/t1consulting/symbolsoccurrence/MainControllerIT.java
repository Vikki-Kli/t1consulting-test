package ru.t1consulting.symbolsoccurrence;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerIT {

    @Autowired
    private MockMvc mockMvc;
    private final String text = "QQqqWw12@ _/ЯЯяяГг";
    private final String regexAll = "\\{(\".\":\\d+,)+\".\":\\d+}";
    private final String regexLettersOnly = "\\{(\"[a-zA-Zа-яА-Я]\":\\d+,)+\".\":\\d+}";
    private final String regexIgnoringCase = "\\{(\"[^A-ZА-Я]\":\\d+,)+\".\":\\d+}";
    private final String regexLettersOnlyIgnoringCase = "\\{(\"[a-zа-я]\":\\d+,)+\".\":\\d+}";

    @Test
    public void countOccurrence_regular() throws Exception {
        String result = mockMvc.perform(post("/occurrence")
                        .content(text)
                        .contentType(MediaType.TEXT_PLAIN)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(jsonPath("$.*", isA(JSONArray.class)))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertTrue(result.matches(regexAll));

        result = mockMvc.perform(post("/occurrence?lettersonly={bool}", true)
                        .content(text)
                        .contentType(MediaType.TEXT_PLAIN)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.*", notNullValue()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertTrue(result.matches(regexLettersOnly));

        result = mockMvc.perform(post("/occurrence?ignoringcase={bool}", true)
                        .content(text)
                        .contentType(MediaType.TEXT_PLAIN)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.*", notNullValue()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertTrue(result.matches(regexIgnoringCase));

        result = mockMvc.perform(post("/occurrence?ignoringcase={bool}&lettersonly={bool}", true, true)
                        .content(text)
                        .contentType(MediaType.TEXT_PLAIN)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.*", notNullValue()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertTrue(result.matches(regexLettersOnlyIgnoringCase));

    }

    @Test
    public void countOccurrence_missingBody() throws Exception {
        mockMvc.perform(post("/occurrence"))
                .andDo(print())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof HttpMessageNotReadableException))
                .andExpect(status().isBadRequest());
    }
}
