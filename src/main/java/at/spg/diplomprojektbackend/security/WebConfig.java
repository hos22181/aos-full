package at.spg.diplomprojektbackend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all endpoints and methods for frontend on localhost:5173
        registry.addMapping("/**")
                .allowedOrigins("https://aos-spg-full.vercel.app")  // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // Allow all HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow cookies and credentials if needed
    }
}