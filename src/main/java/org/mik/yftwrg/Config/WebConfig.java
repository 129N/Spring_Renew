package org.mik.yftwrg.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SuppressWarnings("unused")
public class WebConfig implements WebMvcConfigurer {
//    @Override
    public void addCorsMapping(CorsRegistry registry){

        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}
