package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;


@DisplayName("CSV Reader тести")
public class TransactionCSVReaderTest
{
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException
    {
        // Створюємо імітацію CSV
        String csvData = "2024-01-01,1000,Salary\n" +
                "2024-01-05,-200,Groceries\n" +
                "2024-01-10,-50,Coffee\n";

        // Записуємо його у тимчасовий файл
        tempFile = File.createTempFile("test", ".csv");
        try (FileWriter fw = new FileWriter(tempFile))
        {
            fw.write(csvData);
        }
    }

    @Test
    @DisplayName("Повинен прочитати правильну кількість транзакцій")
    public void testReadTransactions_CountIsCorrect() throws Exception
    {
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.toURI().toURL().toString());

        Assertions.assertEquals(3, transactions.size(), "Повинно бути 3 транзакції");
    }

    @Test
    @DisplayName("Повинен прочитати суму першої транзакції")
    public void testReadTransactions_FirstAmountIsCorrect() throws Exception
    {
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.toURI().toURL().toString());

        Assertions.assertEquals(1000, transactions.get(0).getAmount(), "Перша сума повинна бути 1000");
    }

    @Test
    @DisplayName("Повинен прочитати опис другої транзакції")
    public void testReadTransactions_SecondDescriptionIsCorrect() throws Exception
    {
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.toURI().toURL().toString());

        Assertions.assertEquals("Groceries", transactions.get(1).getDescription(), "Опис другої транзакції повинен бути 'Groceries'");
    }

    @Test
    @DisplayName("Повинен прочитати суму третьої транзакції")
    public void testReadTransactions_ThirdAmountIsCorrect() throws Exception
    {
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.toURI().toURL().toString());

        Assertions.assertEquals(-50, transactions.get(2).getAmount(), "Третя сума повинна бути -50");
    }

    @Test
    @DisplayName("Повинен прочитати дату першої транзакції")
    public void testReadTransactions_FirstDateIsCorrect() throws Exception
    {
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.toURI().toURL().toString());

        Assertions.assertEquals("2024-01-01", transactions.get(0).getDate(), "Перша дата повинна бути '2024-01-01'");
    }

    @Test
    @DisplayName("Повертає порожний список для порожного файлу")
    public void testReadTransactions_EmptyFileReturnsEmptyList() throws Exception
    {
        File emptyFile = File.createTempFile("empty", ".csv");
        try (FileWriter fw = new FileWriter(emptyFile))
        {
            fw.write("");
        }

        List<Transaction> transactions = TransactionCSVReader.readTransactions(emptyFile.toURI().toURL().toString());

        Assertions.assertTrue(transactions.isEmpty(), "Порожний файл повинен повернути порожний список");
    }
}