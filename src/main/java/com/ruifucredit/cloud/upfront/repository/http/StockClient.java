package com.ruifucredit.cloud.upfront.repository.http;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ruifucredit.cloud.inventory.support.dto.Stock;
import com.ruifucredit.cloud.kit.dto.Outcoming;

import lombok.SneakyThrows;

@Repository
public class StockClient {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate rest;

	@HystrixCommand(fallbackMethod="queryStocksFallback")
	@SneakyThrows
	public Outcoming<List<Stock>> queryStocks(String url) {

		RequestEntity<Void> request = RequestEntity.get(new URI(url)).accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8).build();

		ResponseEntity<Outcoming<List<Stock>>> responseFromStock = rest.exchange(request,
				new ParameterizedTypeReference<Outcoming<List<Stock>>>() {
				});
		
		logger.info("StockClient.queryStocks.url: {}, result: {}", url, responseFromStock);
		
		if (responseFromStock.getStatusCode() == HttpStatus.OK) {
			return responseFromStock.getBody();
		} else {
			throw new RuntimeException(responseFromStock.toString());
		}

	}
	
	public Outcoming<List<Stock>> queryStocksFallback(String url) {

		logger.info("StockClient.queryStocksFallback.url: {}, result: {}", url, null);
		
		return new Outcoming<>(Collections.emptyList());
		
	}
	
}
