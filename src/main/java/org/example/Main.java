package org.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Main {
    public static void main(String[] args)
    {
        String monthYearTest = "01-2024";

        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        for (Transaction transaction : transactions)
        {
            System.out.println(transaction);
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYearTest);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYearTest, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        Map<String, Double> expensesByCategory = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printExpensesByCategory(expensesByCategory);
        Map<String, Double> expensesByMonth = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        TransactionReportGenerator.printExpensesByMonth(expensesByMonth);

        Optional<Transaction> maxExpense = TransactionAnalyzer.findMaxExpense(transactions, monthYearTest);
        TransactionReportGenerator.printMaxExpenseReport(monthYearTest, maxExpense);
        Optional<Transaction> minExpense = TransactionAnalyzer.findMinExpense(transactions, monthYearTest);
        TransactionReportGenerator.printMinExpenseReport(monthYearTest, minExpense);
    }
}
