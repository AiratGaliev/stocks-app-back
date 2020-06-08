package com.github.airatgaliev.tooltableback.service;

import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  private final StockRepository stockRepository;

  @Autowired
  public StockService(
      StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  public Iterable<Stock> findAll() {
    return stockRepository
        .findAll(Sort.by(Direction.DESC, "date"));
  }
}
