package org.wiciu;

import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        // Przechowujemy oryginalne wyniki kValues, aby zachować wyniki liczb przerośniętych
        int[] kValuesOriginal = new int[n + 1];
        Arrays.fill(kValuesOriginal, INF);

        // Obliczanie k(n) dla liczb w zakresie od 1 do n
        for (int i = 1; i <= n; i++) {
            kValuesOriginal[i] = computeKOriginal(i);
        }

        // Obliczanie k(n) i liczb przerośniętych na podstawie nowej funkcji computeK
        for (int i = 1; i <= n; i++) {
            int kValue = computeK(i);
            int overgrownCount = countOvergrownNumbers(i, n, kValuesOriginal); // Używamy oryginalnych kValues

            if (kValue == INF) {
                System.out.println(i + ": " + "- " + overgrownCount);
            } else {
                System.out.println(i + ": " + kValue + " " + overgrownCount);
            }
        }
    }

    // Oryginalna funkcja obliczająca k(n) przed poprawkami
    static int computeKOriginal(int n) {
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

    // Zaktualizowana funkcja obliczająca k(n)
    static int computeK(int n) {
        int maxSquare = (int) Math.sqrt(n);
        List<Integer> squares = new ArrayList<>();

        // Tworzymy listę kwadratów liczb całkowitych mniejszych lub równych sqrt(n)
        for (int i = 1; i <= maxSquare; i++) {
            squares.add(i * i);
        }

        int result = INF;

        // Sprawdzamy wszystkie kombinacje różnych kwadratów (od 2 elementów)
        for (int r = 2; r <= squares.size(); r++) {
            // Generowanie kombinacji r-elementowych
            int[] indices = new int[r];
            for (int i = 0; i < r; i++) {
                indices[i] = i;
            }

            // Iterujemy przez wszystkie kombinacje
            while (indices[0] < squares.size() - r + 1) {
                List<Integer> subset = new ArrayList<>();
                int sum = 0;

                // Tworzymy aktualną kombinację
                for (int i = 0; i < r; i++) {
                    subset.add(squares.get(indices[i]));
                    sum += squares.get(indices[i]);
                }

                // Jeśli suma tej kombinacji równa się n, sprawdzamy maksymalny element
                if (sum == n) {
                    int maxElement = (int) Math.sqrt(Collections.max(subset));
                    result = Math.min(result, maxElement);
                }

                // Generowanie następnej kombinacji
                int i = r - 1;
                while (i >= 0 && indices[i] == squares.size() - r + i) {
                    i--;
                }

                if (i >= 0) {
                    indices[i]++;
                    for (int j = i + 1; j < r; j++) {
                        indices[j] = indices[i] + j - i;
                    }
                } else {
                    break;
                }
            }
        }

        // Jeśli nie znaleziono żadnej kombinacji różnych kwadratów, zwróć INF
        return result == INF ? INF : result;
    }


    // Funkcja zliczająca liczby przerośnięte, korzystając z oryginalnych wyników k(n)
    static int countOvergrownNumbers(int n, int extendedRange, int[] kValuesOriginal) {
        int count = 0;

        // Zliczamy liczby przerośnięte na podstawie oryginalnych wyników k(n)
        for (int x = 1; x <= n; x++) {
            boolean isOvergrown = false;
            for (int y = x + 1; y <= extendedRange; y++) {
                if (kValuesOriginal[y] < kValuesOriginal[x]) { // Używamy oryginalnych kValues
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
