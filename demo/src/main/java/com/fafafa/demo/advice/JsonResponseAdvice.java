package com.fafafa.demo.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.Locale;
import com.fafafa.demo.util.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class JsonResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object arg0, MethodParameter arg1, MediaType arg2, Class arg3,
            ServerHttpRequest arg4, ServerHttpResponse arg5) {

        if(arg0 instanceof JsonResponse) return arg0;

        return JsonResponse.error(toLocale("A0001", "默认值"));
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    private String toLocale(String code, String defaultMessage) {
        String lang = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);


        // Locale locale = StringUtils.isBlank(lang) ? Locale.CHINESE : Locale.forLanguageTag(lang);
        return Locale.CHINESE.getCountry();
    }
}
