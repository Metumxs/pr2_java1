package org.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class TransactionReportGenerator
{
    public static void printBalanceReport(double totalBalance)
    {
        System.out.println("\nЗагальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count)
    {
        System.out.println("\nКількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses)
    {
        System.out.println("\n10 найбільших витрат:");
        for (Transaction expense : topExpenses)
        {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printMaxExpenseReport(String monthYear, Optional<Transaction> maxExpense)
    {
        System.out.println("\n=== Найбільша витрата за " + monthYear + " ===");
        if (maxExpense.isPresent())
        {
            Transaction t = maxExpense.get();
            System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getAmount());
        }
        else
        {
            System.out.println("Немає витрат у цей період.");
        }
    }

    public static void printMinExpenseReport(String monthYear, Optional<Transaction> minExpense)
    {
        System.out.println("\n=== Найменша витрата за " + monthYear + " ===");
        if (minExpense.isPresent())
        {
            Transaction t = minExpense.get();
            System.out.println(t.getDate() + " | " + t.getDescription() + " | " + t.getAmount());
        }
        else
        {
            System.out.println("Немає витрат у цей період.");
        }
    }

    // Вивід витрат по категоріях
    public static void printExpensesByCategory(Map<String, Double> expensesByCategory)
    {
        System.out.println("\n=== Витрати по категоріях ===");

        for (String category : expensesByCategory.keySet())
        {
            double amount = expensesByCategory.get(category);
            int starsCount = (int) (amount / 1000);
            System.out.println(category + " : " + amount + " грн " + "*".repeat(starsCount));
        }
    }

    // Вивід витрат по місяцях
    public static void printExpensesByMonth(Map<String, Double> expensesByMonth)
    {
        System.out.println("\n=== Витрати по місяцях ===");

        for (String month : expensesByMonth.keySet())
        {
            double amount = expensesByMonth.get(month);
            int starsCount = (int) (amount / 1000);
            System.out.println(month + " : " + amount + " грн " + "*".repeat(starsCount));
        }
    }
}
