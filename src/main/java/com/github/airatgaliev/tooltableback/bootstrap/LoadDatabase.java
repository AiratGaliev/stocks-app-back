package com.github.airatgaliev.tooltableback.bootstrap;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.repository.CompanyRepository;
import com.github.airatgaliev.tooltableback.repository.StockRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Bean
  CommandLineRunner initDatabase() {
    Company company1 = new Company("Газпром");
    companyRepository.save(company1);
    Company company2 = new Company("Автоваз");
    companyRepository.save(company2);
    Company company3 = new Company("Сбербанк");
    companyRepository.save(company3);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return args -> {
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company1, LocalDate.parse("01.01.2019", dateTimeFormatter), 2000.0)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company2, LocalDate.parse("01.01.2019", dateTimeFormatter), 2500.0)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company3, LocalDate.parse("05.01.2019", dateTimeFormatter), 10000.0)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company1, LocalDate.parse("10.01.2019", dateTimeFormatter), 2500.0)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company2, LocalDate.parse("07.10.2019", dateTimeFormatter), 2100.0)));
    };
  }
}
