package com.tryout.backend;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//public class WebConfiguration implements WebMvcConfigurer {
//	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/{spring:\\w+}")
//	          .setViewName("forward:/");
//	    registry.addViewController("/**/{spring:\\w+}")
//	          .setViewName("forward:/");
//	    registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}")
//	          .setViewName("forward:/");
//	}
//}

@Component
public class RedirectToIndexFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        
        System.out.println(req);
        System.out.println(requestURI);

        if (requestURI.startsWith("/api")) {
        	System.out.println("got em at api");
            chain.doFilter(request, response);
            return;
        }

        if (requestURI.startsWith("/built/")) {
        	System.out.println("got em at built");
            chain.doFilter(request, response);
            return;
        }
        
        if (requestURI.startsWith("/assets/")) {
        	System.out.println("got em at assets");
            chain.doFilter(request, response);
            return;
        }

        // all requests not api or static will be forwarded to index page. 
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

}


