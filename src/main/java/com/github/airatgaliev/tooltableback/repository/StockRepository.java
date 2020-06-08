package com.github.airatgaliev.tooltableback.repository;

import com.github.airatgaliev.tooltableback.entity.Stock;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {

  Iterable<Stock> findAll(Sort sort);
}
