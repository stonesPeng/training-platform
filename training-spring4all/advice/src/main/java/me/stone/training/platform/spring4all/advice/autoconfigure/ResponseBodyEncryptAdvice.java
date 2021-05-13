package me.stone.training.platform.spring4all.advice.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.spring4all.advice.annotation.EnableEncryptResponseBody;
import me.stone.training.platform.spring4all.advice.property.AesEncryptAdviceProperty;
import me.stone.training.platform.spring4all.advice.utils.AesUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 15:40
 */
@RestControllerAdvice
@Slf4j
public class ResponseBodyEncryptAdvice implements ResponseBodyAdvice<Object> {
    private final ObjectMapper mapper;

    private final AesEncryptAdviceProperty aesEncryptAdviceProperty;

    public ResponseBodyEncryptAdvice(ObjectMapper mapper, AesEncryptAdviceProperty aesEncryptAdviceProperty) {
        this.mapper = mapper;
        this.aesEncryptAdviceProperty = aesEncryptAdviceProperty;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return Optional.ofNullable(methodParameter.getMethod())
            .map(x -> x.getAnnotation(EnableEncryptResponseBody.class) != null)
            .orElse(false);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        String outputDataStr;
        if (o instanceof String) {
            outputDataStr = AesUtils.encrypt(String.valueOf(o), aesEncryptAdviceProperty.getAesSecret());
        } else {
            outputDataStr = AesUtils.encrypt(mapper.writeValueAsString(o), aesEncryptAdviceProperty.getAesSecret());
        }
        log.info("The data string after encrypt is {}", outputDataStr);
        return outputDataStr;
    }
}
