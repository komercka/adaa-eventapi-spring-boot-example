package cz.kb.openbanking.adaa.eventapi.example.rest.controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import cz.kb.openbanking.adaa.eventapi.example.core.service.AdaaService;
import cz.kb.openbanking.adaa.eventapi.example.rest.api.v1.generated.EventsApi;
import cz.kb.openbanking.adaa.eventapi.example.rest.model.v1.generated.EventPayloadGen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link EventsApi}.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@Slf4j
@RestController
public class EventsApiController implements EventsApi {

    @Autowired
    private AdaaService adaaService;

    @Override
    public ResponseEntity<Void> receiveEvent(String correlationId, String subscriptionId, Optional<String> acceptLanguage,
                                             @Nullable EventPayloadGen eventPayloadGen)
    {
        Assert.hasText(correlationId, "correlationId must not be empty");
        Assert.hasText(subscriptionId, "subscriptionId must not be empty");

        log.info("subscriptionId: {}, correlationId = {}, acceptLanguage = {}, eventPayloadGen.eventCount = {}",
            subscriptionId, correlationId, acceptLanguage.orElse(null), eventPayloadGen == null ? null : eventPayloadGen.getEventCount());

        // Creates asynchronous call to KB ADAA API
        CompletableFuture.supplyAsync(() -> adaaService.getTransactionsFromAccountApi(subscriptionId));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
