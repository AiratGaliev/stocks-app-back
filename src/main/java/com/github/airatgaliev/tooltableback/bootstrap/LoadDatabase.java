package com.github.airatgaliev.tooltableback.bootstrap;

import com.github.airatgaliev.tooltableback.entity.Company;
import com.github.airatgaliev.tooltableback.entity.Stock;
import com.github.airatgaliev.tooltableback.repository.CompanyRepository;
import com.github.airatgaliev.tooltableback.repository.StockRepository;
import java.util.GregorianCalendar;
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
    return args -> {
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company1, new GregorianCalendar(2019, 1, 1), 2000)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company2, new GregorianCalendar(2019, 1, 1), 2500)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company3, new GregorianCalendar(2019, 1, 5), 10000)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company1, new GregorianCalendar(2019, 1, 10), 2500)));
      LOGGER.info("Preloading " + stockRepository
          .save(new Stock(company2, new GregorianCalendar(2019, 10, 7), 2100)));
    };
  }
}