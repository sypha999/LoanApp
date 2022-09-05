package com.loans.repositories;

import com.loans.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loans,Long> {
}
