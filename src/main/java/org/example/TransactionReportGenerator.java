package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public static void printTopTransactionsExpensesReport(List<Transaction> topTransactionsExpenses)
    {
        System.out.println("\n10 найбільших витрат:");
        for (Transaction transactionExpense : topTransactionsExpenses)
        {
            System.out.println(transactionExpense.getDescription() + ": " + transactionExpense.getAmount());
        }
    }

    // Вивід витрат по категоріях
    public static void printExpensesByCategory(Map<String, Double> expensesByCategory)
    {
        System.out.println("\n=== Витрати по категоріях ===");

        for (String category : expensesByCategory.keySet())
        {
            double amount = expensesByCategory.get(category);
            int stars = (int) (amount / 1000);
            System.out.println(category + " : " + amount + " грн " + "*".repeat(stars));
        }
    }

    // Вивід витрат по місяцях
    public static void printExpensesByMonth(Map<String, Double> expensesByMonth)
    {
        System.out.println("\n=== Витрати по місяцях ===");

        for (String month : expensesByMonth.keySet())
        {
            double amount = expensesByMonth.get(month);
            int stars = (int) (amount / 1000);
            System.out.println(month + " : " + amount + " грн " + "*".repeat(stars));
        }
    }

}

