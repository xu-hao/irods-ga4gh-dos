package org.irods.jargon.ga4gh.dos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

@Configuration
public class SwaggerDocumentationConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Data Repository Service")
				.description("https://github.com/ga4gh/data-repository-service-schemas").license("Apache 2.0")
				.licenseUrl("https://raw.githubusercontent.com/ga4gh/data-repository-service-schemas/master/LICENSE")
				.termsOfServiceUrl("").version("0.6.0").contact(new Contact("", "", "ga4gh-cloud@ga4gh.org")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.irods.jargon.ga4gh.dos.api")).build()
				.directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class).apiInfo(apiInfo());
	}

}
