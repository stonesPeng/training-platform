package me.stone.training.platform.spring4all.advice.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.spring4all.advice.annotation.EnableDecryptRequestBody;
import me.stone.training.platform.spring4all.advice.property.AesEncryptAdviceProperty;
import me.stone.training.platform.spring4all.advice.utils.AesUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 15:40
 */
@RestControllerAdvice
@Slf4j
public class RequestBodyDecryptAdvice implements RequestBodyAdvice {

    private final AesEncryptAdviceProperty aesEncryptAdviceProperty;

    private final ObjectMapper objectMapper;

    public RequestBodyDecryptAdvice(AesEncryptAdviceProperty aesEncryptAdviceProperty, ObjectMapper objectMapper) {
        this.aesEncryptAdviceProperty = aesEncryptAdviceProperty;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return Optional.ofNullable(methodParameter.getMethod())
            .map(x -> x.getAnnotation(EnableDecryptRequestBody.class) != null)
            .orElse(false);
    }

    @SneakyThrows
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter,
                                           Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return handleBeforeBodyRead(httpInputMessage, methodParameter, type, aClass);
    }

    @SneakyThrows
    private HttpInputMessage handleBeforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter,
                                                  Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        final Map<String, Object> stringObjectMap = objectMapper.readValue(httpInputMessage.getBody(), Map.class);
        final String data = (String) stringObjectMap.get(aesEncryptAdviceProperty.getEncryptJsonKey());
        if (data == null) {
            throw new RuntimeException("密文格式有误");
        }
        final String decrypt = AesUtils.decrypt(data, aesEncryptAdviceProperty.getAesSecret());
        log.info("decrypt message is {}", decrypt);
        stringObjectMap.replace(aesEncryptAdviceProperty.getEncryptJsonKey(), objectMapper.readValue(decrypt, Map.class));
        return new CustomHttpInputMessage(new ByteArrayInputStream(objectMapper.writeValueAsString(stringObjectMap).getBytes()), httpInputMessage.getHeaders());
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter,
                                Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter,
                                  Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @AllArgsConstructor
    static class CustomHttpInputMessage implements HttpInputMessage {
        @Getter
        private final InputStream body;
        @Getter
        private final HttpHeaders headers;
    }
}
