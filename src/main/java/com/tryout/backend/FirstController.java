package com.tryout.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class FirstController {

	@RequestMapping("/")
    public String home() {
        return "index.html";
    }
}


