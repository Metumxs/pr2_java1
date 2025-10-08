package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;


class TransactionAnalyzerTest
{
    @Test
    public void testCalculateTotalBalance()
    {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Виклик методу
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевіряємо
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth()
    {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Викликаємо методи
        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        // Перевіряємо
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses() {
        // Створення тестових даних
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2024-01-01", -500, "Rent"),
                new Transaction("2024-01-02", -100, "Food"),
                new Transaction("2024-01-03", -50, "Coffee"),
                new Transaction("2024-01-04", -1000, "Laptop"),
                new Transaction("2024-01-05", 2000, "Salary")
        );

        // Викликаємо метод
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевіряємо
        Assertions.assertEquals(4, topExpenses.size(),
                "Неправильна кількість витрат (має бути 4, бо 4 від’ємні транзакції)");
        Assertions.assertEquals(-1000, topExpenses.get(0).getAmount(),
                "Найбільша витрата (Laptop) має бути першою");
        Assertions.assertEquals("Laptop", topExpenses.get(0).getDescription(),
                "Перша транзакція повинна бути 'Laptop'");
        Assertions.assertEquals(-500, topExpenses.get(1).getAmount(),
                "Друга найбільша витрата повинна бути -500 (Rent)");
    }
}