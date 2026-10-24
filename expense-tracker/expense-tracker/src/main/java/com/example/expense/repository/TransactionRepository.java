package com.example.expense.repository;

import com.example.expense.model.Transaction;
import com.example.expense.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByOrderByDateDescIdDesc();

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = :type")
    double sumByType(@Param("type") TransactionType type);

    @Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t " +
           "WHERE t.type = :type GROUP BY t.category.name ORDER BY SUM(t.amount) DESC")
    List<Object[]> totalsByCategory(@Param("type") TransactionType type);

    @Query("SELECT MONTH(t.date), SUM(t.amount) FROM Transaction t " +
           "WHERE t.type = :type AND YEAR(t.date) = :year GROUP BY MONTH(t.date) ORDER BY MONTH(t.date)")
    List<Object[]> totalsByMonth(@Param("type") TransactionType type, @Param("year") int year);
}
