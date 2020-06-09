package com.github.airatgaliev.tooltableback.controller;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.payload.CompanyRequest;
import com.github.airatgaliev.tooltableback.service.CompanyService;
import com.github.airatgaliev.tooltableback.service.MapValidationErrorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin
public class CompanyController {

  private final CompanyService companyService;
  private final MapValidationErrorService mapValidationErrorService;

  @Autowired
  public CompanyController(CompanyService companyService,
      MapValidationErrorService mapValidationErrorService) {
    this.companyService = companyService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @GetMapping("")
  public Iterable<Company> getAllCompanies() {
    return companyService.findAll();
  }

  @PostMapping("/create")
  public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyRequest companyRequest,
      BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.getValidation(result);
    if (errorMap != null) {
      return errorMap;
    }
    Company company = companyService.create(companyRequest);
    return new ResponseEntity<>(company, HttpStatus.CREATED);
  }
}
