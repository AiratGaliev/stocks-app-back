package com.github.airatgaliev.tooltableback.controller;

import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin
public class StockController {

  private final StockService stockService;

  @Autowired
  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @GetMapping("")
  public Iterable<Stock> getAllStocks() {
    return stockService.findAll();
  }
}
