package com.tmdaq.jsontool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Cache {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper XML_MAPPER = new XmlMapper();
    private static XmlHandler xmlHandler = null;
    private static JsonHandler jsonHandler = null;

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static XmlMapper getXmlMapper() {
        return XML_MAPPER;
    }

    public static XmlHandler getXmlHandler() {
        if (xmlHandler == null) {
            xmlHandler = new XmlHandler();
        }
        return xmlHandler;
    }

    public static JsonHandler getJsonHandler() {
        if (jsonHandler == null) {
            jsonHandler = new JsonHandler();
        }
        return jsonHandler;
    }

    private Cache() {
        throw new IllegalStateException("Utility class");
    }
}
