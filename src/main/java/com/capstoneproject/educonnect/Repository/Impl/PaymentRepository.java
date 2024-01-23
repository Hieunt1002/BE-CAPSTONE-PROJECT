package com.capstoneproject.educonnect.Repository.Impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
