package com.k3rcaft.mvcinitialize.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Slf4j
public class HttpResponseNew<T> {

    private static class Meta {
        @JsonIgnore
        private final SimpleDateFormat format;
        public String serverDateTime;
        public int statusCode;
        public String message;

        Meta(int statusCode, String message) {
            format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            serverDateTime = format.format(new Date());
            this.statusCode = statusCode;
            this.message = message;
        }
    }

    private Meta meta;
    private T data;
    private RestResponsePage<?> pagination;
    //prevent init this object
    private HttpResponseNew() {}

    public static <T> HttpResponseNew<List<T>> successful(String message, RestResponsePage<T> pagination) {
        HttpResponseNew<List<T>> response = new HttpResponseNew<>();
        response.setMeta(new Meta(HttpStatus.OK.value(), message));
        response.setData(pagination.getContent());
        response.setPagination(pagination);
        return response;
    }

    public static <T> HttpResponseNew<T> successful(String message, T data) {
        HttpResponseNew<T> response = new HttpResponseNew<>();
        response.setMeta(new Meta(HttpStatus.OK.value(), message));
        response.setData(data);
        return response;
    }

    public static <T> HttpResponseNew<T> successful(String message) {
        HttpResponseNew<T> response = new HttpResponseNew<>();
        response.setMeta(new Meta(HttpStatus.OK.value(), message));
        return response;
    }

    public static <T> HttpResponseNew<T> failure(String message, int statusCode) {
        HttpResponseNew<T> response = new HttpResponseNew<>();
        response.setMeta(new Meta(statusCode, message));
        return response;
    }

    public static <T> HttpResponseNew<T> failure(String message) {
        HttpResponseNew<T> response = new HttpResponseNew<>();
        response.setMeta(new Meta(HttpStatus.BAD_REQUEST.value(), message));
        return response;
    }
}
