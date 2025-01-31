package org.wiciu.service;

import org.springframework.stereotype.Service;
import org.wiciu.model.SquareResponse;
import java.util.Arrays;

@Service
public class SquareService {
    private static final int MAX_SQUARE_SUM = 630; // 1 + 4 + 9 + ... + 144
    private static final int MAX_SQUARES = 30;
    private static final int MAGIC_CONSTANT = 130;
    private static final boolean[][] canBeFormed = new boolean[MAX_SQUARE_SUM + 1][MAX_SQUARES];
    private static final int[] minimumSquares = new int[MAX_SQUARE_SUM + 1];

    public SquareService() {
        preprocess();
    }

    private void preprocess() {
        Arrays.fill(minimumSquares, 100);

        for (int i = 0; i < MAX_SQUARES; ++i) {
            canBeFormed[0][i] = true;
        }

        for (int i = 1; i <= MAX_SQUARE_SUM; ++i) {
            for (int j = 1; j < MAX_SQUARES && j * j <= i; ++j) {
                canBeFormed[i][j] = (canBeFormed[i][j - 1] || canBeFormed[i - j * j][j - 1]);
                if (minimumSquares[i] == 100 && canBeFormed[i][j]) {
                    minimumSquares[i] = j;
                }
            }

            for (int j = 1; j < MAX_SQUARES; ++j) {
                canBeFormed[i][j] |= canBeFormed[i][j - 1];
            }
        }
    }

    public SquareResponse calculateSquares(long n) {
        if (n <= MAX_SQUARE_SUM) {
            int minSquareCount = minimumSquares[(int) n];

            if (minSquareCount == 100) { // 100 - default value of array minimumSquares
                int overgrownCount = countOvergrownNumbers((int) n);
                return new SquareResponse("-", overgrownCount);
            }

            return new SquareResponse(String.valueOf(minSquareCount), countOvergrownNumbers((int) n));
        } else {
            return calculateLargeNumber(n);
        }
    }

    int countOvergrownNumbers(int x) {
        int overgrownCount = 0;
        int minSquares = 99;

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

    SquareResponse calculateLargeNumber(long n) {
        long sumOfSquares = 0;
        int index = 0;

        while (sumOfSquares < n) {
            index++;
            sumOfSquares += (long) index * index;
        }

        int K;
        if (sumOfSquares - n > MAGIC_CONSTANT) {
            K = index;
        } else {
            if (minimumSquares[MAX_SQUARE_SUM - (int) (sumOfSquares - n)] > 12) {
                K = index + 1;
            } else {
                K = index;
            }
        }

        long result = 0;
        result += countOvergrownNumbers(MAX_SQUARE_SUM);
        result += (long) (countOvergrownNumbers(MAX_SQUARE_SUM)
                - countOvergrownNumbers(MAX_SQUARE_SUM - MAGIC_CONSTANT))
                * (index - 12 - 1);

        if (sumOfSquares - n <= MAGIC_CONSTANT) {
            result += countOvergrownNumbers(MAX_SQUARE_SUM - (int) (sumOfSquares - n))
                    - countOvergrownNumbers(MAX_SQUARE_SUM - MAGIC_CONSTANT);
        }

        return new SquareResponse(String.valueOf(K), result);
    }
}
