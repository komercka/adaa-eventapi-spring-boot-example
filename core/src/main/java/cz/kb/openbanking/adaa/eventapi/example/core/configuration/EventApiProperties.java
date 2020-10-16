package cz.kb.openbanking.adaa.eventapi.example.core.configuration;

import java.util.Map;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = EventApiProperties.EVENT_API_EXAMPLE_PROPERTY_PREFIX, ignoreUnknownFields = false)
public class EventApiProperties {

    /**
     * Prefix for all properties configuration for ADAA Event API Example application.
     */
    public static final String EVENT_API_EXAMPLE_PROPERTY_PREFIX = "event-api-example";

    /**
     * Example of access token.
     */
    @NotNull
    private String accessToken;

    /**
     * Example of client's account currency.
     */
    @NotNull
    private String currency;

    /**
     * Example of map with subscriptionId (key) and IBAN (value).
     */
    @NotNull
    private Map<String, String> subscriptions;
}
