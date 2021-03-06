package com.codingblocks.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@SpringBootApplication
public class ChatbotApplication {

    @GetMapping("/Webhook")
    @ResponseBody
    public String verify(@RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String token) {
        String t = "76531245648453548792";
        if (t.equals(token)) {
            return challenge;
        }
        return "This is not verified";
    }

    @PostMapping("/Webhook")
    @ResponseBody
    public void Hook(@RequestBody Hook hook) {
        for (Hook.Item item : hook.entry) {
            for (Content i : item.messaging) {
                Response response = new Response();
                response.recipient = i.sender;
                response.message = i.message;
                if (response.message.text == null) {
                    response.message.text = "Thanks for sending an attachment";
                } else {
                    if (response.message.text.toLowerCase().contains("quote")) {
                        Jokes jokes = new Jokes();
                        int random = (int) (Math.random() * jokes.jokes.size());
                        response.message.text = (new Jokes()).jokes.get(random);
                    } else if(response.message.text.toLowerCase().equals("hi")||response.message.text.toLowerCase().equals("hello")){
                        response.message.text = "Heya ! How you doing? \nPlease ask me for a quote";

                    }else {
                        response.message.text = "Please ask me for a quote";
                    }
                }
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://graph.facebook.com/v2.6/me/messages/";
                String access_token = "EAAJZClfP0EdIBAHgmdNM59uYfJYcUZCjqhyR7iD3XW5K7xse8nvSUIqlOI9Ncwr8DDnVRIwuCL87jR7m0d1z7gOEtsyqzjZBxD4n5xIGPrLjnok9YeI35bQtjwxlHLjd0ywRG2duvdwFHyUqzV9ZClD8gI5Q6qIDcpI6dfqiuSIQYkZC9lX2g";
                UriComponentsBuilder Builder = UriComponentsBuilder.fromUriString(url)
                        .queryParam("access_token", access_token);
                UriComponents components = Builder.build();
                restTemplate.postForObject(components.toString(), response, String.class);
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatbotApplication.class, args);
    }
}