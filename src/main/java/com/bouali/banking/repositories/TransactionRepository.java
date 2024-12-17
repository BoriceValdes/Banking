package com.bouali.banking.repositories;

//import com.bouali.banking.dto.TransactionSumDetails;
import com.bouali.banking.dto.TransactionSumDetails;
import com.bouali.banking.models.Transaction;
import com.bouali.banking.models.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Ali Bouali
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.creationDate as transactionDate, sum(t.amount) as amount from Transaction t where t.user.id = :userId and t.creationDate "
            + "between :start and :end "
            + "group by t.creationDate")
    Map<LocalDate, BigDecimal> findSumTransactionsByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}
