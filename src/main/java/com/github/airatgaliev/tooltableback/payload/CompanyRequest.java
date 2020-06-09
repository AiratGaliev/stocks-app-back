package com.github.airatgaliev.tooltableback.payload;

import javax.validation.constraints.NotBlank;

public class CompanyRequest {

  @NotBlank(message = "Company name cannot be blank")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
