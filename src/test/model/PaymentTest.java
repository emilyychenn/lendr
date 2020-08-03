//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///*
// *  Unit tests for the Payment class. Note: will add more tests later on when Payment class is used more extensively
// *  (e.g. to show full payment history with dates etc.)
// */
//
//public class PaymentTest {
//    Payment testPayment;
//    Contact testContact;
//
//    @BeforeEach
//    public void setUp() {
//        testContact = new Contact("Felix");
//        testPayment = new Payment(testContact, 100.0);
//    }
//
//    @Test
//    public void testPayment() {
//        assertEquals(LocalDate.now(), testPayment.getDate());
//        assertEquals(testContact, testPayment.getContact());
//        assertEquals(100.0, testPayment.getAmount());
//    }
//
//}
