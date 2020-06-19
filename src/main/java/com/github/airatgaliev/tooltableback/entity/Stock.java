package com.github.airatgaliev.tooltableback.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stock extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "company_id", nullable = false)
  @JsonIgnore
  private Company company;
  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate date;
  private Double price;

  public Stock() {
  }

  public Stock(Company company, LocalDate date, Double price) {
    this.company = company;
    this.date = date;
    this.price = price;
  }

  public String getCompanyName() {
    return company.getName();
  }

  public Long getCompanyId() {
    return company.getId();
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Stock stock = (Stock) o;
    return Double.compare(stock.price, price) == 0 &&
        Objects.equals(company, stock.company) &&
        Objects.equals(date, stock.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), company, date, price);
  }
}
