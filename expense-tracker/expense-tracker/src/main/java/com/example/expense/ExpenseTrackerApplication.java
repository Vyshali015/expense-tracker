package com.example.expense;

import com.example.expense.model.Category;
import com.example.expense.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }

    // Seed default categories on first run
    @Bean
    CommandLineRunner seed(CategoryRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                for (String n : new String[]{"Food", "Transport", "Rent", "Shopping", "Bills", "Salary", "Other"}) {
                    repo.save(new Category(n));
                }
            }
        };
    }
}
