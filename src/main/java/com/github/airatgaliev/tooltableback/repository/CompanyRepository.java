package com.github.airatgaliev.tooltableback.repository;

import com.github.airatgaliev.tooltableback.entity.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

  Iterable<Company> findAll(Sort name);

  Company findCompanyById(long parseLong);
}
