package org.wiciu;

import java.util.Scanner;

public class Main {

    // Stałe
    private static final int MAX_SQUARE_SUM = 630;
//    630 = 1 + 4 + 9 + 16 + 25 + 36 + 49 + 64 + 81 + 100 + 121 + 144
    private static final int MAX_SQUARES = 30;
    private static final int MAGIC_CONSTANT = 130;

    // Tablice pomocnicze
    private static boolean[][] canBeFormed = new boolean[MAX_SQUARE_SUM + 1][MAX_SQUARES];
    private static int[] minimumSquares = new int[MAX_SQUARE_SUM + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Preprocessing - przygotowanie tablic
        preprocess();

        // Odczytujemy wartość n
        long n = sc.nextLong();

        // Jeśli n jest mniejsze lub równe MAX_SQUARE_SUM, obliczamy wynik dla tej liczby
        if (n <= MAX_SQUARE_SUM) {
            printResultForSmallNumber(n);
        } else {
            // Dla n > MAX_SQUARE_SUM obliczamy wynik, korzystając z optymalizacji
            printResultForLargeNumber(n);
        }
    }

    // Funkcja wstępnego przetwarzania
    private static void preprocess() {
        // Wypełniamy tablicę minimumSquares wartością 100 (nie znaleziono rozkładu)
        for (int i = 0; i < minimumSquares.length; ++i) {
            minimumSquares[i] = 100;
        }

        // Ustawiamy dla każdej liczby możliwość rozkładu na sumy kwadratów
        for (int i = 0; i < MAX_SQUARES; ++i) {
            canBeFormed[0][i] = true;
        }

        // Sprawdzamy wszystkie liczby od 1 do MAX_SQUARE_SUM
        for (int i = 1; i <= MAX_SQUARE_SUM; ++i) {
            for (int j = 1; j < MAX_SQUARES && j * j <= i; ++j) {
                canBeFormed[i][j] = (canBeFormed[i][j - 1] || canBeFormed[i - j * j][j - 1]);
                if (minimumSquares[i] == 100 && canBeFormed[i][j]) {
                    minimumSquares[i] = j;
                }
            }
            // Uaktualniamy dostępność rozkładów z poprzednich kroków
            for (int j = 1; j < MAX_SQUARES; ++j) {
                canBeFormed[i][j] |= canBeFormed[i][j - 1];
            }
        }
    }

    // Funkcja obliczająca liczbę "przerosnietych" liczb
    private static int countOvergrownNumbers(int x) {
        int overgrownCount = 0;
        int minSquares = 99;

        // Dla każdej liczby do MAX_SQUARE_SUM, porównujemy liczbę kwadratów
        for (int i = MAX_SQUARE_SUM; i > 0; --i) {
            if (minimumSquares[i] < minSquares) {
                minSquares = minimumSquares[i];
            }
            if (i <= x && minimumSquares[i] > minSquares) {
                overgrownCount++;
            }
        }
        return overgrownCount;
    }

    // Funkcja do wypisywania wyników dla n <= MAX_SQUARE_SUM
    private static void printResultForSmallNumber(long n) {
        if (minimumSquares[(int) n] == 100) {
            System.out.print("- ");
        } else {
            System.out.print(minimumSquares[(int) n] + " ");
        }
        System.out.println(countOvergrownNumbers((int) n));
    }

    // Funkcja do wypisywania wyników dla n > MAX_SQUARE_SUM
    private static void printResultForLargeNumber(long n) {
        long sumOfSquares = 0;
        int index = 0;

        // Określamy, gdzie suma kwadratów przekroczyła n
        while (sumOfSquares < n) {
            index++;
            sumOfSquares += (long) index * index;
        }

        int K = (sumOfSquares - n > MAGIC_CONSTANT)
                ? index
                : (minimumSquares[MAX_SQUARE_SUM - (int) (sumOfSquares - n)] > 12 ? index + 1 : index);

        System.out.print(K + " ");

        long result = 0;
        result += countOvergrownNumbers(MAX_SQUARE_SUM);
        result += (long) (countOvergrownNumbers(MAX_SQUARE_SUM) - countOvergrownNumbers(MAX_SQUARE_SUM - MAGIC_CONSTANT))
                * (index - 12 - 1);

        if (sumOfSquares - n <= MAGIC_CONSTANT) {
            result += countOvergrownNumbers(MAX_SQUARE_SUM - (int) (sumOfSquares - n))
                    - countOvergrownNumbers(MAX_SQUARE_SUM - MAGIC_CONSTANT);
        }

        System.out.println(result);
    }
}
