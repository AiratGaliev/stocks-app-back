package com.github.airatgaliev.tooltableback.service;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.exception.IdException;
import com.github.airatgaliev.tooltableback.payload.CompanyRequest;
import com.github.airatgaliev.tooltableback.repository.CompanyRepository;
import java.util.NoSuchElementException;
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

  public Company findById(String id) {
    try {
      return companyRepository.findCompanyById(Long.parseLong(id));
    } catch (NoSuchElementException | NullPointerException e) {
      throw new IdException("Company ID: '" + id + "' does not exists");
    }
  }

  public Company create(CompanyRequest companyRequest) {
    Company newCompany = new Company(companyRequest.getName());
    return companyRepository.save(newCompany);
  }

  public Company update(CompanyRequest companyRequest, String id) {
    Company originalCompany = findById(id);
    originalCompany.setName(companyRequest.getName());
    return companyRepository.save(originalCompany);
  }

  public void delete(String id) {
    Company company = findById(id);
    companyRepository.delete(company);
  }
}
