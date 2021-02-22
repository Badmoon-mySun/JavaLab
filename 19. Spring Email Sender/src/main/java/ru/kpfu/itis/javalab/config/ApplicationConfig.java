package ru.kpfu.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@EnableWebMvc
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ru.kpfu.itis.javalab")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("spring.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
        hikariConfig.setDriverClassName(environment.getProperty("spring.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths("classpath:/templates/", "classpath:/mails/");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable",
                environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        properties.put("mail.smtp.allow8bitmime",
                environment.getProperty("spring.mail.properties.mail.smtp.allow8bitmime"));
        properties.put("mail.smtp.ssl.trust", environment.getProperty("spring.mail.properties.mail.smtp.ssl.trust"));
        properties.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));

        return mailSender;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }
}
