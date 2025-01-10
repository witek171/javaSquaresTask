package org.wiciu;

import java.util.*;

public class Main {

    // Stałe
    private static final int s12 = 1 + 4 + 9 + 16 + 25 + 36 + 49 + 64 + 81 + 100 + 121 + 144;
    private static final int dpkw = 30;
    private static final int magicConst = 130;

    // Tablice pomocnicze
    private static boolean[][] dasie = new boolean[s12 + 1][dpkw];
    private static int[] k = new int[s12 + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Preprocessing
        preprocessing();

        // Odczytujemy wartość n
        long n = sc.nextLong();

        if (n <= s12) {
            // Jeśli n <= s12
            if (k[(int) n] == 100) {
                System.out.print("- ");
            } else {
                System.out.print(k[(int) n] + " ");
            }
            System.out.println(ileprzerosdox((int) n));
        } else {
            // Jeśli n > s12
            long suma = 0;
            int indeks = 0;
            while (suma < n) {
                indeks++;
                suma += (long) indeks * indeks;
            }
            int K = (suma - n > magicConst) ? indeks : (k[s12 - (int) (suma - n)] > 12 ? indeks + 1 : indeks);
            System.out.print(K + " ");
            long wynik = 0;
            wynik += ileprzerosdox(s12);
            wynik += (ileprzerosdox(s12) - ileprzerosdox(s12 - magicConst)) * (indeks - 12 - 1);
            if (suma - n <= magicConst) {
                wynik += ileprzerosdox(s12 - (int) (suma - n)) - ileprzerosdox(s12 - magicConst);
            }
            System.out.println(wynik);
        }
    }

    // Funkcja preprocesująca dane
    private static void preprocessing() {
        Arrays.fill(k, 100);

        for (int i = 0; i < dpkw; ++i)
            dasie[0][i] = true;

        for (int i = 1; i <= s12; ++i) {
            for (int j = 1; j < dpkw && j * j <= i; ++j) {
                dasie[i][j] = (dasie[i][j - 1] || dasie[i - j * j][j - 1]);
                if (k[i] == 100 && dasie[i][j])
                    k[i] = j;
            }
            for (int j = 1; j < dpkw; ++j)
                dasie[i][j] |= dasie[i][j - 1];
        }
    }

    // Funkcja licząca liczbę "przerosnietych" liczb
    private static int ileprzerosdox(int x) {
        int zl = 0, min = 99;
        for (int i = s12; i > 0; --i) {
            if (k[i] < min)
                min = k[i];
            if (i <= x && k[i] > min)
                ++zl;
        }
        return zl;
    }
}
