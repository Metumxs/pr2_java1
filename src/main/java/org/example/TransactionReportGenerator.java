package org.example;

import java.util.List;


public class TransactionReportGenerator
{
    public void printBalanceReport(double totalBalance)
    {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count)
    {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topTransactionsExpenses)
    {
        System.out.println("10 найбільших витрат:");
        for (Transaction transactionExpense : topTransactionsExpenses)
        {
            System.out.println(transactionExpense.getDescription() + ": " + transactionExpense.getAmount());
        }
    }
}

