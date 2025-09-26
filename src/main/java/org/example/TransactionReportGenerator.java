package org.example;

import java.util.List;


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
}

