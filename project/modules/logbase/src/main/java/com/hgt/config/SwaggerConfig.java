package com.hgt.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //初始化作者信息
    Contact contact = new Contact("yaoooo", "https://github.com/HGTLite/logboard/issues", "837276086@qq.com");

    @Bean
    public Docket restfulApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(initApiInfo())
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/") // base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hgt"))
                .paths(doFilteringRules())
                .build();
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     *
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/logb.*")
        );

//        return or(
//                regex("/logs/apps.*")
//        );

    }

    private ApiInfo initApiInfo() {

        ApiInfo apiInfo = new ApiInfo("LogBoard REST APIs",//大标题
                initContextInfo(),//简单的描述
                "0.0.1",//版本
                "无服务条款",
                contact,//作者
                "The Apache License, Version 2.0",//链接显示文字
                "http://www.apache.org/licenses/LICENSE-2.0.html"//网站链接
        );
        return apiInfo;
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("<p>LogBoard后台提供的服务接口主要分为3类：  </p>")
                .append("<ul>" + "<li>关于应用信息的接口，以\"/apps\"为标志；" + "</li>")
                .append("<li>关于日志信息的接口，以\"/loginfo\"为标志；" + "</li>")
                .append("<li>关于异常预警信息的接口，以\"/exps\"为标志。" + "</li></ul>")
                .append("<p>接口的其他信息可以参考本项目的wiki：https://hgtlite.github.io/logboard/" + "</p>");

        return sb.toString();
    }

}