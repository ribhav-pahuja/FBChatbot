package com.codingblocks.chatbot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Hook {
    public Item[] entry;
    ObjectMapper objectMapper = new ObjectMapper();

    public static class Item {
        public Content[] messaging;
    }

    @Override
    public String toString() {
        try {
            String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
