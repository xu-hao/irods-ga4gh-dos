package org.irods.jargon.ga4gh.dos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

@Configuration
public class SwaggerDocumentationConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Data Object Service")
				.description("https://github.com/ga4gh/data-object-service-schemas").license("Apache 2.0")
				.licenseUrl("https://raw.githubusercontent.com/ga4gh/data-object-service-schemas/master/LICENSE")
				.termsOfServiceUrl("").version("0.5.0").contact(new Contact("", "", "davidcs@ucsc.edu")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.irods.jargon.ga4gh.dos.api")).build()
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class).apiInfo(apiInfo());
	}

}
