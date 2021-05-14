package me.stone.training.platform.spring4all.advice.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.spring4all.advice.property.AesEncryptAdviceProperty;
import me.stone.training.platform.spring4all.advice.utils.AesUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdviceTestController.class)
@Slf4j
class AdviceTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AesEncryptAdviceProperty property;


    @SneakyThrows
    @Test
    void postTestBean() {
        final AdviceTestController.TestBean bean = AdviceTestController.TestBean.builder().count(1).name("name").build();
        final String content = objectMapper.writeValueAsString(bean);
        final Map<Object, Object> map = Stream.of(new Object[][]{
            {property.getEncryptJsonKey(), AesUtils.encrypt(content, property.getAesSecret())}
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        final String data = objectMapper.writeValueAsString(map);
        final String contentAsString = mockMvc.perform(
            post("/advice/bean").contentType(MediaType.APPLICATION_JSON).content(data)
        ).andDo(print())
            .andExpect(status().isOk())
            .andDo(mvcResult -> {
                final String result = mvcResult.getResponse().getContentAsString();
                log.info(AesUtils.decrypt(result, property.getAesSecret()));
            })
            .andReturn()
            .getResponse()
            .getContentAsString();
        log.info(contentAsString);
    }
}