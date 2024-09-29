package com.example.converter;
import java.util.Scanner;

public class CurrencyConverterCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Digite o valor: ");
                double amount = scanner.nextDouble();

                System.out.println("Escolha a moeda de origem (ex: BRL, USD, EUR, GBP, etc.): ");
                String baseCurrency = scanner.next().toUpperCase();

                System.out.println("Escolha a moeda de destino (ex: BRL, USD, EUR, GBP, etc.): ");
                String targetCurrency = scanner.next().toUpperCase();

                Rates rates = CurrencyConverter.getExchangeRates(baseCurrency);

                if (rates.rates.containsKey(targetCurrency)) {
                    double convertedAmount;

                    if (baseCurrency.equals("BRL")) {
                        convertedAmount = amount / rates.rates.get(targetCurrency);
                    } else {
                        convertedAmount = amount * rates.rates.get(targetCurrency);
                    }

                    System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
                } else {
                    System.out.println("Moeda de destino não suportada.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Deseja realizar outra conversão? (s/n)");
            String response = scanner.next().toLowerCase();
            if (!response.equals("s")) {
                break;
            }
        }
        scanner.close();
        System.out.println("Programa encerrado.");
    }
}
