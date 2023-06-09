package com.jowens.software.TickerFetch;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private static Quote singleQuote;
    @GetMapping("/")
    public String index() {
        return "Select ticker with URL until FE is linked.";
    }

    @GetMapping("/quote/{ticker}")
    public Quote SingleQuote(@PathVariable String ticker) throws InterruptedException {
        Quote q;
        AlphaVantage.api().timeSeries().quote()
                .forSymbol(ticker)
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(e -> handleFailure(e))
                .fetch();

        //wait for handleSuccess to finish
        Thread.sleep(1000);

        q = singleQuote;
        return q;
    }

    public static synchronized void handleSuccess(QuoteResponse response) {
        Quote q = new Quote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        System.out.println(q);

        singleQuote = q;

    }

    public static void handleFailure(AlphaVantageException error) {
        System.out.println("Error message is: " + error.getMessage());
    }
}
