package org.wiciu;

import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        for (int i = 1; i < n; i++) {
            int kValue = computeK(i);
            int overgrownCount = countOvergrownNumbers(i, i + 100); // Rozszerzony zakres

            if (kValue == INF) {
                System.out.println(i + ": " + "- " + overgrownCount);
            } else {
                System.out.println(i + ": " + kValue + " " + overgrownCount);
            }
        }

    }

    // Funkcja obliczająca k(n)
    static int computeK(int n) {
        int maxSquare = (int) Math.sqrt(n);
        List<Integer> squares = new ArrayList<>();

        for (int i = 1; i <= maxSquare; i++) {
            squares.add(i * i);
        }

        int result = INF;
        int numSquares = squares.size();

        for (int mask = 1; mask < (1 << numSquares); mask++) {
            int sum = 0, maxElement = 0;

            for (int i = 0; i < numSquares; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += squares.get(i);
                    maxElement = Math.max(maxElement, (int) Math.sqrt(squares.get(i)));
                }
            }

            if (sum == n) {
                result = Math.min(result, maxElement);
            }
        }

        return result == INF ? INF : result;
    }

    // Funkcja zliczająca liczby przerośnięte
    static int countOvergrownNumbers(int n, int extendedRange) {
        int[] kValues = new int[extendedRange + 1];
        Arrays.fill(kValues, INF);

        // Oblicz k(x) dla x <= extendedRange
        for (int i = 1; i <= extendedRange; i++) {
            kValues[i] = computeK(i);
        }

        int count = 0;
        for (int x = 1; x <= n; x++) {
            boolean isOvergrown = false;
            for (int y = x + 1; y <= extendedRange; y++) {
                if (kValues[y] < kValues[x]) {
                    isOvergrown = true;
                    break;
                }
            }
            if (isOvergrown) {
                count++;
            }
        }

        return count;
    }
}
