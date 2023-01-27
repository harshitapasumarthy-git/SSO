package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;


@Controller
	public class ResourceController {
	    private static final String jwtTokenCookieName = "JWT-TOKEN";

	    @GetMapping("/")
	    public String home() {
	        return "redirect:/protected-resource";
	    }

	    @GetMapping("/protected-resource")
	    public String protectedResource() {
	        return "protected-resource";
	    }

	    @GetMapping("/logout")
	    public String logout(HttpServletResponse httpServletResponse) {
	        CookieClass.clear(httpServletResponse, jwtTokenCookieName);
	        return "redirect:/";
	    }
	}

