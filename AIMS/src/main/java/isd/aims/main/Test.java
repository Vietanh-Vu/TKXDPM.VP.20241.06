package isd.aims.main;

import isd.aims.main.entity.db.dao.payment_transaction.PaymentTransactionDAO;

public class Test {
    public static void main(String[] args) {
        // Create an instance of PaymentTransactionDAO
        PaymentTransactionDAO paymentTransactionDAO = new PaymentTransactionDAO();

        // Test case 1: Retrieve transaction number by order ID
        String orderId = "7171a4fb-681e-4cde-8f11-d01bb6df39dd";  // Example order ID
        try {
            String transactionNumber = paymentTransactionDAO.getTranSactionNumberByOrderId(orderId);
            System.out.println("Transaction Number for Order ID " + orderId + ": " + transactionNumber);
        } catch (RuntimeException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        // Test case 2: Handle invalid or non-existing order ID
        String invalidOrderId = "99999";  // Example invalid order ID
        try {
            String transactionNumber = paymentTransactionDAO.getTranSactionNumberByOrderId(invalidOrderId);
            System.out.println("Transaction Number for Order ID " + invalidOrderId + ": " + transactionNumber);
        } catch (RuntimeException e) {
            System.out.println("Error occurred for Order ID " + invalidOrderId + ": " + e.getMessage());
        }
    }
}
