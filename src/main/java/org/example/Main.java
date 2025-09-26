package org.example;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args)
    {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        for (Transaction transaction : transactions)
        {
            System.out.println(transaction);
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Map<String, Double> expensesByCategory = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printExpensesByCategory(expensesByCategory);

// По місяцях
        Map<String, Double> expensesByMonth = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        TransactionReportGenerator.printExpensesByMonth(expensesByMonth);
    }
}
