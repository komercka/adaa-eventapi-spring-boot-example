package cz.kb.openbanking.adaa.eventapi.example.rest.controller;

import cz.kb.openbanking.adaa.eventapi.example.rest.api.v1.generated.DiagnosticsApi;
import cz.kb.openbanking.adaa.eventapi.example.rest.model.v1.generated.VersionResponseGen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link DiagnosticsApi}.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@RestController
public class DiagnosticsApiController implements DiagnosticsApi {

    private final VersionResponseGen versionResponseGen;

    private static final String API_VERSION = "1.0";

    /**
     * Constructor. Creates {@link VersionResponseGen} instance for faster response in {@link #getApiVersion}.
     * Version is in {@link #API_VERSION}.
     */
    public DiagnosticsApiController() {
        this.versionResponseGen = new VersionResponseGen();
        this.versionResponseGen.setVersion(API_VERSION);
    }

    @Override
    public ResponseEntity<VersionResponseGen> getApiVersion(String correlationId) {
        Assert.hasText(correlationId, "correlationId must not be empty.");
        return new ResponseEntity<>(versionResponseGen, HttpStatus.OK);
    }
}
