package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;


public class TransactionCSVReaderTest
{
    @Test
    public void testReadTransactions() throws Exception
    {
        // Створюємо імітацію CSV
        String csvData = "2024-01-01,1000,Salary\n" +
                "2024-01-05,-200,Groceries\n" +
                "2024-01-10,-50,Coffee\n";

        // Записуємо його у тимчасовий файл
        File tempFile = File.createTempFile("test", ".csv");
        try (FileWriter fw = new FileWriter(tempFile))
        {
            fw.write(csvData);
        }

        // Викликаємо наш метод
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.toURI().toURL().toString());

        // Перевіряємо що зчиталося правильно
        Assertions.assertEquals(3, transactions.size(), "Неправильна кількість транзакцій");
        Assertions.assertEquals(1000, transactions.get(0).getAmount(), "Сума першої транзакції неправильна");
        Assertions.assertEquals("Groceries", transactions.get(1).getDescription(), "Опис другої транзакції неправильний");
        Assertions.assertEquals(-50, transactions.get(2).getAmount(), "Сума третьої транзакції неправильна");
    }
}
