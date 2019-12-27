package br.com.futeba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.futeba.controller"))
          .paths(PathSelectors.any())
          .build()
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
//          .securitySchemes(Arrays.asList(securityScheme()))
//          .securityContexts(Arrays.asList(securityContext()))
          .apiInfo(apiInfo());
    }
    
    private List<ResponseMessage> responseMessageForGET() {
    	ArrayList<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
    	
    	responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("500 message")
                .responseModel(new ModelRef("Error"))
                .build());
    	
    	responseMessages.add(new ResponseMessageBuilder()
                .code(403)
                .message("Forbidden!")
                .build());
        return responseMessages;
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Futeba")
                .description("This app is for bookmarking games")
                .version("1.0.0")
                .contact(new Contact("Diego Miranda", "https://futeba.com.br", "diegoetata@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .termsOfServiceUrl("http://swagger.io/terms/")
                .build();
    }
}