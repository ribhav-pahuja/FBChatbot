package com.codingblocks.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SpringBootApplication
public class ChatbotApplication {

	@GetMapping("/Webhook")
	@ResponseBody
	public String verify(@RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String token){
		String t = "76531245648453548792";
		if(t.equals(token)){
			return challenge;
		}
		return "This is not verified";
	}

	@PostMapping("/Webhook")
	@ResponseBody
	public void Hook(@RequestBody Hook hook){
		System.out.println(hook.toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}
}
