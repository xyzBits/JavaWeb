package com.dongfang.javaweb.servlet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpRequest {
    private HttpServletRequest httpRequest;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        httpRequest = mock(HttpServletRequest.class);
        System.out.println("httpRequest.getMethod() = " + httpRequest.getMethod());

        Enumeration headers = new StringTokenizer("header1 header2", " ");
        when(httpRequest.getHeaderNames()).thenReturn(headers);

        Enumeration header1Values = new StringTokenizer("value1_1 value1_2", " ");
        when(httpRequest.getHeaders("header1")).thenReturn(header1Values);

        Enumeration header2Values = new StringTokenizer("value2_1 value2_2", " ");
        when(httpRequest.getHeaders("header2")).thenReturn(header2Values);
    }

    @Test
    public void map() {
        Map<String, List<String>> headersMap = Collections
                .list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(httpRequest.getHeaders(h))
                ));

        System.out.println("headersMap = " + headersMap);
        assertThat(headersMap.get("header1")).containsOnly("value1_1", "value1_2");
        assertThat(headersMap.get("header2")).containsOnly("value2_1", "value2_2");
    }

    @Test
    public void httpHeaders() {
        HttpHeaders httpHeaders = Collections
                .list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(httpRequest.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));

        assertThat(httpHeaders.get("header1")).containsOnly("value1_1", "value1_2");
        assertThat(httpHeaders.get("header2")).containsOnly("value2_1", "value2_2");
    }
}
