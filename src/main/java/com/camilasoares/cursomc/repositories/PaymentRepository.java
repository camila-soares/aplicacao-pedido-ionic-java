package com.camilasoares.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
