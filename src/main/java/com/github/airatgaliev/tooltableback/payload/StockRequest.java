package com.github.airatgaliev.tooltableback.payload;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public class StockRequest {

  @NotNull(message = "Please select date")
  private LocalDate date;
  @NotNull(message = "Please select stock company name")
  private Long companyId;
  @NotNull(message = "Stock price is required")
  private Double price;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}