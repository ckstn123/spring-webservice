package com.chansu.webservice;

import com.chansu.webservice.domain.security.helpers.SpringSecurityHelper;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnClass(HandlebarsViewResolver.class)
public class WebMvcConfig implements WebMvcConfigurer {

    @Configuration
    @ConditionalOnClass(SpringSecurityHelper.class)
    static class SpringSecurityHelperAutoConfiguration {
        @Autowired
        private HandlebarsViewResolver handlebarsViewResolver;

        @Autowired
        private SpringSecurityHelper springSecurityHelper;

        @PostConstruct
        public void registerHelper() {
            handlebarsViewResolver.registerHelper(SpringSecurityHelper.NAME, springSecurityHelper);
        }
    }
}
