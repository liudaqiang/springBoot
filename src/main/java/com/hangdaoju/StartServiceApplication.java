package com.hangdaoju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class StartServiceApplication extends SpringBootServletInitializer{

	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(StartServiceApplication.class);
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(StartServiceApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
		    @Override
		    public void customize(ConfigurableEmbeddedServletContainer container) {
		      container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
		      container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
		    }
		  };
//	   return (container -> {
//	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.jsp");
//	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.jsp");
//	        container.addErrorPages(error404Page,error500Page);
//	   });
	}
}
