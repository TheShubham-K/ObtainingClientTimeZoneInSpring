package com.shubham.ObtainingClientTimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SpringBootApplication
public class ObtainingClientTimeZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObtainingClientTimeZoneApplication.class,
				args);
	}


	@Bean
	LocaleContextResolver localeResolver () {
		SessionLocaleResolver l = new SessionLocaleResolver();
		return new
				AcceptHeaderLocaleTzCompositeResolver(l);
	}

	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				TzRedirectInterceptor interceptor = new TzRedirectInterceptor();
				InterceptorRegistration i = registry.addInterceptor(interceptor);
				i.excludePathPatterns("/tzHandler", "/tzValueHandler");
			}
		};
	}
}
