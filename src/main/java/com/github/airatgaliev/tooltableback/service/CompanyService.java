package com.github.airatgaliev.tooltableback.service;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  @Autowired
  public CompanyService(
      CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public Iterable<Company> findAll() {
    return companyRepository.findAll(Sort.by(Direction.ASC, "name"));
  }
}
