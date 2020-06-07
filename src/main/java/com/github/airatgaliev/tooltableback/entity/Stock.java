package com.github.airatgaliev.tooltableback.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate date;
  private double price;

  public Stock() {
  }

  public Stock(Company company, LocalDate date, double price) {
    this.company = company;
    this.date = date;
    this.price = price;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double count) {
    this.price = count;
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
