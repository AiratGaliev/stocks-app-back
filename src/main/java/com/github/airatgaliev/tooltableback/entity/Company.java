package com.github.airatgaliev.tooltableback.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import net.minidev.json.annotate.JsonIgnore;

@Entity
public class Company extends AbstractEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", orphanRemoval = true)
  @JsonIgnore
  private final List<Stock> stocks = new ArrayList<>();

  public Company() {
  }

  public Company(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    Company company = (Company) o;
    return Objects.equals(name, company.name) &&
        Objects.equals(stocks, company.stocks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, stocks);
  }
}
