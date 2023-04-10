package com.tahademiryol.rentacar.repository.abstracts;

import com.tahademiryol.rentacar.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
            String cardNumber, String cardHolder, int cardExpirationYear,
            int cardExpirationMonth, String cardCvv);


    // SPeL -> Spring Expression language
    //Custom query
//    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN true ELSE false END" +
//    " FROM Payment p Where p.cardNumber = :#{#paymentRequest.cardNumber} " +
//    " AND p.cardHolder = :#{#paymentRequest.cardHolder} " +
//    " AND p.cardExpirationYear = :#{#paymentRequest.cardExpirationYear} " +
//    " AND p.cardExpirationMonth = :#{#paymentRequest.cardExpirationMonth}" +
//            " AND p.cardCvv = :#{#paymentRequest.cardCvv}")
//    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
//            @Param("paymentRequest") PaymentRequest paymentRequest);
}
