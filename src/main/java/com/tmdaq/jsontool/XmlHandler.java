package com.tmdaq.jsontool;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.io.StringWriter;

import static com.tmdaq.jsontool.Cache.getObjectMapper;
import static com.tmdaq.jsontool.Cache.getXmlMapper;

public class XmlHandler implements TypeHandler {
    @Override
    public String convert(String s) {
        StringWriter w = new StringWriter();
        try (JsonParser jp = getXmlMapper().getFactory().createParser(s); JsonGenerator jg = getObjectMapper().getFactory().createGenerator(w)) {
            while (jp.nextToken() != null) {
                jg.copyCurrentEvent(jp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return w.toString();
    }
}
