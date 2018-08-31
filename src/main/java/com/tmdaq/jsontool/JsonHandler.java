package com.tmdaq.jsontool;

import lombok.SneakyThrows;

import java.io.StringWriter;
import java.util.Map;

import static com.tmdaq.jsontool.Cache.getObjectMapper;
import static com.tmdaq.jsontool.Cache.getXmlMapper;

public class JsonHandler implements TypeHandler {
    @Override
    @SneakyThrows
    public String convert(String s) {
        Map map = getObjectMapper().readValue(s, Map.class);
        StringWriter stringWriter = new StringWriter();
        getXmlMapper().writeValue(stringWriter, map);
        return stringWriter.toString();
    }
}
