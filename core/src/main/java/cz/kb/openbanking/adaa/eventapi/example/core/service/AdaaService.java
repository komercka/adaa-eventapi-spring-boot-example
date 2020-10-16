package cz.kb.openbanking.adaa.eventapi.example.core.service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import cz.kb.openbanking.adaa.client.api.model.Account;
import cz.kb.openbanking.adaa.client.api.model.PageSlice;
import cz.kb.openbanking.adaa.client.model.generated.AccountTransaction;
import cz.kb.openbanking.adaa.eventapi.example.core.configuration.EventApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * The type Adaa service.
 *
 * @author <a href="mailto:michal_madle@kb.cz">Michal Madle</a>
 * @since 1.0
 */
@Slf4j
@Component
public class AdaaService {

    @Autowired
    private EventApiProperties eventApiProperties;

    @Autowired
    private AccountApi accountApi;

    /**
     * Calls {@link AccountApi#transactions(Account, String)} until last page is loaded.
     *
     * @param subscriptionId subscription id
     * @return new transactions from KB ADAA API
     */
    public Integer getTransactionsFromAccountApi(String subscriptionId) {
        Assert.hasText(subscriptionId, "subscriptionId must not be empty");

        Account account = getAccount(subscriptionId);
        String accessToken = getAccessToken();

        List<AccountTransaction> result = new ArrayList<>();
        boolean callAccountApi = true;
        while (callAccountApi) {
            PageSlice<AccountTransaction> transactionPageSlice = accountApi.transactions(account, accessToken).size(10).page(0).find();
            result.addAll(transactionPageSlice.getContent());
            callAccountApi = !transactionPageSlice.isLast();
        }

        log.info("Loaded {} transactions: {}", result.size(), result);
        return result.size();
    }

    /**
     * Gets Access token for access KB ADAA API.
     * Returns only mock data, readed from application properties for example purposes only!
     *
     * @return access token
     */
    private String getAccessToken() {
        return eventApiProperties.getAccessToken();
    }

    /**
     * Finds IBAN according to the specified subscriptionId.
     * Returns only mock data, readed from application properties for example purposes only!
     *
     * @param subscriptionId subscription id
     * @return {@link Account}
     */
    private Account getAccount(String subscriptionId) {
        Assert.hasText(subscriptionId, "subscriptionId must not be empty");

        String ibanNumber = eventApiProperties.getSubscriptions().get(subscriptionId);
        return new Account(Iban.valueOf(ibanNumber), Currency.getInstance(eventApiProperties.getCurrency()));
    }
}