package com.github.airatgaliev.tooltableback.service;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.payload.StockRequest;
import com.github.airatgaliev.tooltableback.repository.CompanyRepository;
import com.github.airatgaliev.tooltableback.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  private final StockRepository stockRepository;

  private final CompanyRepository companyRepository;

  @Autowired
  public StockService(StockRepository stockRepository,
      CompanyRepository companyRepository) {
    this.stockRepository = stockRepository;
    this.companyRepository = companyRepository;
  }

  public Iterable<Stock> findAll() {
    return stockRepository
        .findAll(Sort.by(Direction.DESC, "date"));
  }

  public Stock create(StockRequest stockRequest) {
    Company company = companyRepository.findCompanyById(stockRequest.getCompanyId());
    Stock stock = new Stock(company, stockRequest.getDate(), stockRequest.getPrice());
    return stockRepository.save(stock);
  }
}
