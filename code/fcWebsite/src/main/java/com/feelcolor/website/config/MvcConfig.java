package com.feelcolor.website.config;

import java.util.Locale;

import com.feelcolor.website.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.feelcolor.website.interceptor.RequestInterceptor;
import com.feelcolor.website.interceptor.TokenInterceptor;

/**
 * 继承WebMvcConfigAdapter可以重写SpringMVC的一些配置
 * 
 * @author Administrator
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new RequestInterceptor());
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/test/*");
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/login/*");
    }
}
