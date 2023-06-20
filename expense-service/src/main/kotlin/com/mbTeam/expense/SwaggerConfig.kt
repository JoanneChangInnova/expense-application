package com.mbTeam.expense

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


@Configuration
class SwaggerConfig {
    @Bean
    fun setConfig(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
    }

    fun apiInfo(): ApiInfo {
        return ApiInfo(
            "Expense Service",
            "Api Documentation",
            "1.0",
            "urn:tos",
            ApiInfo.DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            ArrayList()
        );
    }
}