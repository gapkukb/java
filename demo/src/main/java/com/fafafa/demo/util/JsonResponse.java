package com.fafafa.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
public class JsonResponse {
    private Integer code;
    private String message;
    private Object data;

    public static JsonResponse error(Integer code, String message) {
        return new JsonResponse(code, message, null);
    }

    public static JsonResponse error(String message) {
        return new JsonResponse(-1, message, null);
    }

    public static JsonResponse ok(Object data) {
        return new JsonResponse(0, null, data);
    }
}
