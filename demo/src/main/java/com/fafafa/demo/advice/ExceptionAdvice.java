package com.fafafa.demo.advice;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fafafa.demo.util.JsonResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import java.util.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ExceptionAdvice {
    @Resource
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler({ Throwable.class })
    public Object handleException(Throwable e, HttpServletResponse response) {
        
        /** ErrorResponse 会携带状态码 */
        if (e instanceof ErrorResponse) {
            System.out.println("---------------ErrorResponse--------------");
            var code = ((ErrorResponse) e).getStatusCode().value();
            response.setStatus(code);
            return JsonResponse.error(code, e.getMessage());
        }

        System.out.println("---------------Exception--------------");
        return JsonResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    private String toLocale(String code, String defaultMessage) {
        String lang = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        Locale locale = lang != null && lang.isBlank() ? Locale.getDefault() : Locale.forLanguageTag(lang);

        System.out.println(locale.getLanguage());

        try {
            return messageSource.getMessage(code, new Object[] {}, locale);
        } catch (Exception e) {
            log.warn("本地化消息转换异常：{},{}", code);
            return defaultMessage == null ? "" : defaultMessage;
        }
    }
}
