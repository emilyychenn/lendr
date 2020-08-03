//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///*
// *  Unit tests for the Contact class.
// */
//
//class ContactTest {
//    Contact newContact;
//    Payment testPayment;
//    Loan testLoan;
//
//    @BeforeEach
//    public void setUp() {
//        newContact = new Contact("Test Name");
//    }
//
//    @Test
//    public void TestContact() {
//        assertEquals("Test Name", newContact.getName());
//        assertEquals(0.0, newContact.getTotalAmountOwed());
//        assertEquals(0, newContact.getLoanList().getSize());
//        assertEquals(0, newContact.getPaymentHistory().getNumPayments());
//    }
//
//    @Test
//    public void testAddLoanToAmountOwed() {
//        assertEquals(20.0, newContact.addLoanToAmountOwed(20.0));
//        assertEquals(40.0, newContact.addLoanToAmountOwed(20.0));
//        assertEquals(40.0, newContact.addLoanToAmountOwed(0.0));
//        assertEquals(-20.0, newContact.addLoanToAmountOwed(-60.0));
//    }
//
//    @Test
//    public void testAddPaymentToTotal() {
//        // positive value for amount someone else pays the user, negative for amount user pays someone else
//        newContact.addLoanToAmountOwed(60.0);
//        assertEquals(20.0, newContact.addPaymentToTotal(40.0));
//        assertEquals(20.0, newContact.addPaymentToTotal(0.0));
//        assertEquals(-20.0, newContact.addPaymentToTotal(40.0));
//        assertEquals(40.0, newContact.addPaymentToTotal(-60.0));
//        assertEquals(1000.0, newContact.addPaymentToTotal(-960.0));
//    }
//
//    @Test
//    public void testAddPaymentToHistory() {
//        testPayment = new Payment(newContact, 50.0);
//        newContact.addPaymentToHistory(testPayment);
//        assertEquals(1, newContact.getPaymentHistory().getNumPayments());
//        testPayment = new Payment(newContact, 100.0);
//        assertEquals(1, newContact.getPaymentHistory().getNumPayments());
//        newContact.addPaymentToHistory(testPayment);
//        assertEquals(2, newContact.getPaymentHistory().getNumPayments());
//    }
//
//    @Test
//    public void testAddLoan() {
//        newContact.addLoan(60.0, "07/25/2020");
//        assertEquals(60.0, newContact.getTotalAmountOwed());
//        newContact.addLoan(80.0, "03/23/2020");
//        assertEquals(140.0, newContact.getTotalAmountOwed());
//        newContact.addLoan(-100.0, "01/15/2020");
//        assertEquals(40.0, newContact.getTotalAmountOwed());
//        newContact.addLoan(0.0, "05/03/2020");
//        assertEquals(40.0, newContact.getTotalAmountOwed());
//        newContact.addLoan(-60.0, "07/22/2020");
//        assertEquals(-20.0, newContact.getTotalAmountOwed());
//    }
//
//    @Test
//    public void testAddLoanToList() {
//        testLoan = new Loan(50.0, "07/22/2020");
//        newContact.addLoanToList(testLoan);
//        assertEquals(1, newContact.getLoanList().getSize());
//        testLoan = new Loan(100.0, "07/23/2020");
//        assertEquals(1, newContact.getLoanList().getSize());
//        newContact.addLoanToList(testLoan);
//        assertEquals(2, newContact.getLoanList().getSize());
//    }
//
//}