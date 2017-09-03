package com.codingblocks.chatbot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Hook {
    public Item[] entry;
    public ObjectMapper objectMapper = new ObjectMapper();
    public static class Item{
        Content[] messaging ;
    }

    @Override
    public String toString() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            super.toString();
        }
        return super.toString();
    }
}
