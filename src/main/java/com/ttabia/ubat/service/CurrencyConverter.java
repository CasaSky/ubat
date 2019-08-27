package com.ttabia.ubat.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttabia.ubat.dto.Conversion;
import com.ttabia.ubat.dto.ConversionResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:access.properties")
public class CurrencyConverter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CurrencyConverter.class);
    
    private final static String COIN_MARKET_CAP_URI = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";

    @Value("${coinmarketcap-api-key}")
    private String coinMarketCapApiKey;

    public ConversionResponse toFiat(CryptoCurrency currency, BigDecimal amount) throws Exception {

        //TODO check for alternative to coin market cap
        /*String targetCurrenciesNames = FiatCurrency.currencies().stream()
                .map(FiatCurrency::getCode)
                .collect(Collectors.joining(","));*/
        String targetCurrenciesNames = FiatCurrency.EUR.getCode();

        try {
            Map<String, BigDecimal> prices = downloadPrices(currency.getId(), amount, targetCurrenciesNames);
            List<Conversion> conversions = prices.entrySet().stream()
                    .map(e -> new Conversion(e.getKey(), FiatCurrency.valueOf(e.getKey()).getSymbol(), e.getValue()))
                    .collect(Collectors.toList());
            return new ConversionResponse(currency.getName(), amount, conversions);
        } catch (URISyntaxException | IOException e) {
            String message = "Could not get prices";
            LOGGER.error(message, e);
            throw new Exception(message);
        }
    }

    private Map<String, BigDecimal> downloadPrices(String sCurrencyId, BigDecimal amount, String tCurrencies)
            throws URISyntaxException, IOException {

        Map<String, BigDecimal> pricesByCurrency = new HashMap<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("id", sCurrencyId));
        parameters.add(new BasicNameValuePair("amount", String.valueOf(amount)));
        parameters.add(new BasicNameValuePair("convert", tCurrencies));

        URIBuilder query = new URIBuilder(COIN_MARKET_CAP_URI);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        if (StringUtils.isBlank(coinMarketCapApiKey)) {
            String message = "Missing CoinMarketCap api key";
            LOGGER.error(message);
            throw new RuntimeException(message);
        }
        request.addHeader("X-CMC_PRO_API_KEY", coinMarketCapApiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseContent);
            JsonNode dataNode = jsonNode.get("data");
            JsonNode quoteNode = dataNode.get("quote");
            String[] tCs = tCurrencies.split(",");
            for (String tC : tCs) {
                JsonNode priceNode = quoteNode.get(tC).get("price");
                pricesByCurrency.putIfAbsent(tC, BigDecimal.valueOf(priceNode.asDouble()));
            }
            return pricesByCurrency;
        } finally {
            response.close();
        }
    }
}
