package com.github.airatgaliev.tooltableback.controller;

import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.payload.StockRequest;
import com.github.airatgaliev.tooltableback.service.MapValidationErrorService;
import com.github.airatgaliev.tooltableback.service.StockService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin
public class StockController {

  private final StockService stockService;
  private final MapValidationErrorService mapValidationErrorService;

  @Autowired
  public StockController(StockService stockService,
      MapValidationErrorService mapValidationErrorService) {
    this.stockService = stockService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @GetMapping("")
  public Iterable<Stock> getAllStocks() {
    return stockService.findAll();
  }

  @PostMapping("/create")
  public ResponseEntity<?> createCompany(@Valid @RequestBody StockRequest stockRequest,
      BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.getValidation(result);
    if (errorMap != null) {
      return errorMap;
    }
    Stock stock = stockService.create(stockRequest);
    return new ResponseEntity<>(stock, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody StockRequest stockRequest,
      BindingResult result,
      @PathVariable String id) {
    ResponseEntity<?> errorMap = mapValidationErrorService.getValidation(result);
    if (errorMap != null) {
      return errorMap;
    }
    Stock stock = stockService.update(stockRequest, id);
    return new ResponseEntity<>(stock, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    stockService.delete(id);
    return new ResponseEntity<>("Stock with ID: " + id + " was deleted", HttpStatus.OK);
  }
}
