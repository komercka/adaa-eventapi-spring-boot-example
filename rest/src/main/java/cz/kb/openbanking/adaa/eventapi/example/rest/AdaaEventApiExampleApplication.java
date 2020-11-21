package cz.kb.openbanking.adaa.eventapi.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application's entry point.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "cz.kb.openbanking.adaa.eventapi.example")
public class AdaaEventApiExampleApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AdaaEventApiExampleApplication.class);
    }

    /**
     * Entry-point of the application.
     *
     * @param args application arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AdaaEventApiExampleApplication.class, args);
    }
}
