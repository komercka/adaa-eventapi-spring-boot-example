package cz.kb.openbanking.adaa.eventapi.example.core.configuration;

import cz.kb.openbanking.adaa.eventapi.example.core.PackageNameHolder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the Core module.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@Configuration
@ComponentScan(basePackageClasses = PackageNameHolder.class)
@EnableConfigurationProperties(EventApiProperties.class)
public class AdaaEventApiExampleCoreConfiguration {
}
