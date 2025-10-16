package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class TransactionAnalyzerTest
{
    @Test
    @DisplayName("Повертає 10 найбільших витрат, якщо їх більше 10")
    public void testFindTopExpenses_WhenMoreThan10ExpensesExist()
    {
        // Створення тестових даних (12 витрат і 1 дохід)
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2024-01-01", -100, "Їжа"),
                new Transaction("2024-01-02", -200, "Таксі"),
                new Transaction("2024-01-03", -300, "Комунальні"),
                new Transaction("2024-01-04", -400, "Одяг"),
                new Transaction("2024-01-05", -500, "Подарунок"),
                new Transaction("2024-01-06", -600, "Квитки"),
                new Transaction("2024-01-07", -700, "Ремонт"),
                new Transaction("2024-01-08", -800, "Техніка"),
                new Transaction("2024-01-09", -900, "Медицина"),
                new Transaction("2024-01-10", -1000, "Оренда"),
                new Transaction("2024-01-11", -1100, "Подорож"),
                new Transaction("2024-01-12", -1200, "Курси"),
                new Transaction("2024-01-13", 5000, "Зарплата") // Ця транзакція має бути проігнорована
        );

        // Очікуваний результат: 10 транзакцій від -1200 до -300
        List<Transaction> topTenExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевіряємо, що повернуто рівно 10 транзакцій
        Assertions.assertEquals(10, topTenExpenses.size(),
                "Повинно бути повернуто рівно 10 транзакцій.");

        // Перевіряємо, що перша витрата є найбільшою
        Assertions.assertEquals(-1200, topTenExpenses.get(0).getAmount(),
                "Першою у списку має бути найбільша витрата (-1200).");

        // Перевіряємо, що остання витрата є 10-ю за розміром
        Assertions.assertEquals(-300, topTenExpenses.get(9).getAmount(),
                "Останньою у списку має бути 10-та за розміром витрата (-300).");
    }

    @Test
    @DisplayName("Повертає всі витрати, якщо їх менше 10")
    public void testFindTopExpenses_WhenLessThan10ExpensesExist()
    {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2024-01-01", -500, "Оренда"),
                new Transaction("2024-01-02", -100, "Їжа"),
                new Transaction("2024-01-03", -50, "Кава"),
                new Transaction("2024-01-04", -1000, "Ноутбук"),
                new Transaction("2024-01-05", 2000, "Зарплата")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевіряємо, що повернуто всі 4 витрати
        Assertions.assertEquals(4, topExpenses.size(),
                "Якщо витрат менше 10, мають бути повернуті всі.");

        // Перевіряємо правильність сортування
        Assertions.assertEquals(-1000, topExpenses.get(0).getAmount(), "Найбільша витрата 'Ноутбук' має бути першою.");
        Assertions.assertEquals(-50, topExpenses.get(3).getAmount(), "Найменша витрата 'Кава' має бути останньою.");
    }

    @Test
    @DisplayName("Повертає порожній список, якщо витрат немає")
    public void testFindTopExpenses_WhenNoExpensesExist()
    {
        // Створення даних без витрат
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2024-01-01", 5000, "Зарплата"),
                new Transaction("2024-01-02", 1000, "Бонус")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевіряємо, що список порожній
        Assertions.assertTrue(topExpenses.isEmpty(),
                "Список має бути порожнім, якщо немає жодної витрати.");
    }

    @Test
    @DisplayName("Повертає порожній список для порожнього вхідного списку")
    public void testFindTopExpenses_WhenTransactionListIsEmpty()
    {
        // Виклик методу з порожнім списком
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(Collections.emptyList());

        // Перевіряємо, що список порожній
        Assertions.assertTrue(topExpenses.isEmpty(),
                "Результат для порожнього списку транзакцій має бути порожнім.");
    }
}