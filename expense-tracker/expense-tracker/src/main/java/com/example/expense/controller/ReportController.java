package com.example.expense.controller;

import com.example.expense.model.TransactionType;
import com.example.expense.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final TransactionRepository repo;

    public ReportController(TransactionRepository repo) {
        this.repo = repo;
    }

    // Total income, total expense and balance
    @GetMapping("/summary")
    public Map<String, Double> summary() {
        double income = repo.sumByType(TransactionType.INCOME);
        double expense = repo.sumByType(TransactionType.EXPENSE);
        Map<String, Double> m = new LinkedHashMap<>();
        m.put("totalIncome", income);
        m.put("totalExpense", expense);
        m.put("balance", income - expense);
        return m;
    }

    // Expenses grouped by category
    @GetMapping("/by-category")
    public List<Map<String, Object>> byCategory() {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object[] row : repo.totalsByCategory(TransactionType.EXPENSE)) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("category", row[0]);
            m.put("total", row[1]);
            out.add(m);
        }
        return out;
    }

    // Expenses grouped by month for a year (defaults to current year)
    @GetMapping("/monthly")
    public List<Map<String, Object>> monthly(@RequestParam(required = false) Integer year) {
        int y = (year == null) ? LocalDate.now().getYear() : year;
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object[] row : repo.totalsByMonth(TransactionType.EXPENSE, y)) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("month", row[0]);
            m.put("total", row[1]);
            out.add(m);
        }
        return out;
    }
}
