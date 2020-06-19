package com.github.airatgaliev.tooltableback.payload;

import javax.validation.constraints.Size;

public class CompanyRequest {

  @Size(min = 2, max = 20, message = "Name must be between 2 and 50 characters")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
