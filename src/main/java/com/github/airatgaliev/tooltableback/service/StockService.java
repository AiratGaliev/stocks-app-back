package com.github.airatgaliev.tooltableback.service;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.exception.IdException;
import com.github.airatgaliev.tooltableback.payload.StockRequest;
import com.github.airatgaliev.tooltableback.repository.CompanyRepository;
import com.github.airatgaliev.tooltableback.repository.StockRepository;
import java.util.NoSuchElementException;
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

  public Stock findById(String id) {
    try {
      return stockRepository.findStockById(Long.parseLong(id));
    } catch (NoSuchElementException | NullPointerException e) {
      throw new IdException("Stock ID: '" + id + "' does not exists");
    }
  }

  public Stock create(StockRequest stockRequest) {
    Company company = companyRepository.findCompanyById(stockRequest.getCompanyId());
    Stock stock = new Stock(company, stockRequest.getDate(), stockRequest.getPrice());
    return stockRepository.save(stock);
  }

  public Stock update(StockRequest stockRequest, String id) {
    Stock originalStock = findById(id);
    originalStock.setPrice(stockRequest.getPrice());
    return stockRepository.save(originalStock);
  }

  public void delete(String id) {
    Stock post = findById(id);
    stockRepository.delete(post);
  }
}
