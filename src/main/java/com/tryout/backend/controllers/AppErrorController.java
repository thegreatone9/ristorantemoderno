package com.tryout.backend.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class AppErrorController implements ErrorController {
	
	@RequestMapping("/static/error")
    public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        
	        if(statusCode == HttpStatus.FORBIDDEN.value()) {
	        	System.out.println("403 ERRRRROORRRRRRRR!!!!!");
	        	return "static/error/error-403.html";
	        }
	        
	        else if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	System.out.println("404 ERRRRROORRRRRRRR!!!!!");
	            return "static/error/error-404.html";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	System.out.println("500 ERRRRROORRRRRRRR!!!!!");
	            return "static/error/error-500.html";
	        }
	    }
	    return "static/error/error";

    }
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
