package com.household.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HouseholdApplication {
  public static void main(String[] args) { SpringApplication.run(HouseholdApplication.class, args); }
}
