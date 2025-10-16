package org.example;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer
{
    private static final DateTimeFormatter DATE_TIME_FORMATTER_MAIN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions)
    {
        double balance = 0;
        for (Transaction transaction : transactions)
        {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Метод для підрахунку транзакцій за конкретний місяць і рік
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear)
    {
        int count = 0;
        for (Transaction transaction : transactions)
        {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_TIME_FORMATTER_MAIN);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear))
            {
                ++count;
            }
        }
        return count;
    }

    // Метод для пошуку топ-10 витрат
    public static List<Transaction> findTopExpenses(List<Transaction> transactions)
    {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Optional<Transaction> findMaxExpense(List<Transaction> transactions, String monthYear)
    {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DATE_TIME_FORMATTER_MAIN);
                    return date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear);
                })
                .min(Comparator.comparing(Transaction::getAmount)); // мінімальне число = найбільша витрата
    }

    public static Optional<Transaction> findMinExpense(List<Transaction> transactions, String monthYear)
    {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DATE_TIME_FORMATTER_MAIN);
                    return date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear);
                })
                .max(Comparator.comparing(Transaction::getAmount)); // найближче до 0 = найменша витрата
    }

    // Повертає сумарні витрати по категоріях
    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions)
    {
        Map<String, Double> categorySums = new HashMap<>();

        for (Transaction t : transactions)
        {
            if (t.getAmount() < 0)
            {
                String category = t.getDescription();
                double amount = Math.abs(t.getAmount());

                categorySums.put(category, categorySums.getOrDefault(category, 0.0) + amount);
            }
        }
        return categorySums;
    }

    // Повертає сумарні витрати по місяцях
    public static Map<String, Double> calculateExpensesByMonth(List<Transaction> transactions)
    {
        Map<String, Double> monthSums = new HashMap<>();

        for (Transaction t : transactions)
        {
            if (t.getAmount() < 0)
            {
                LocalDate date = LocalDate.parse(t.getDate(), DATE_TIME_FORMATTER_MAIN);
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                double amount = Math.abs(t.getAmount());
                monthSums.put(monthYear, monthSums.getOrDefault(monthYear, 0.0) + amount);
            }
        }
        return monthSums;
    }
}


