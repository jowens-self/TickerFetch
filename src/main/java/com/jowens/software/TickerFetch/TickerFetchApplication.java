package com.jowens.software.TickerFetch;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TickerFetchApplication {


	public static void main(String[] args) {
		SpringApplication.run(TickerFetchApplication.class, args);



		Config cfg = Config.builder()
				.key("EP9UY0QAK3UB8DLX")
				.timeOut(10)
				.build();

		AlphaVantage.api().init(cfg);
	}
}
