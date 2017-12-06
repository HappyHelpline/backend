package com.source.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
@Configuration
@ComponentScan("com.source")
@EnableWebMvc
@EnableTransactionManagement
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }


    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver getCommonsMultipartResolver() {
//    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//    	multipartResolver.setMaxUploadSize(20971520); // 20MB
//    	multipartResolver.setMaxInMemorySize(1048576);	// 1MB
//    	return multipartResolver;
//    }
    
    
//    @Bean
//    public ThreadPoolTaskExecutor taskExecutor() 
//    {
//     ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
//     pool.setCorePoolSize(100);
//     pool.setMaxPoolSize(1000);
//     pool.setWaitForTasksToCompleteOnShutdown(true);
//     return pool;
//    }
    
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) 
//    {
//       registry.addMapping("/**")
//      .allowedOrigins("http://**,https://**");
//    }
    
   /* @Bean(name = "dataSource")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("cm.mysql.jdbc.driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/gsp");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
    
    */
    
    
}
