# Zadanie: Kwadraty

## Opis Projektu
Projekt zajmuje się rozwiązaniem problemu związanego z rozkładaniem liczb całkowitych na sumy różnych kwadratów dodatnich liczb całkowitych. Kluczowym celem jest określenie:
- Najmniejszej możliwej wartości największego kwadratu w rozkładzie liczby \(n\) \(k(n)\).
- Liczby "przerośniętych" liczb mniejszych lub równych \(n\).

Przykład:
- Dla \(n = 30\), \(k(30) = 4\), liczba "przerośniętych" liczb wynosi \(15\).
- Dla \(n = 8\), \(k(8) = infinity\), liczba "przerośniętych" liczb wynosi \(5\).
- Liczba przerośniętych liczb dla n = 30 Musimy teraz znaleźć liczbę przerośniętych liczb, czyli takich liczb x ≤ 30, dla których istnieje liczba y > x, taka że k(y) < k(x).
  Prześledźmy kilka przykładów:
  Dla x = 1: k(1) = 1. Istnieją liczby y > 1, dla których k(y) < k(1) – nie ma takich liczb, więc x = 1 nie jest przerośnięte.
  Dla x = 9: k(9) = 3. Istnieją liczby y > 9, np. y = 16, dla których k(16) = 4 < k(9). Zatem x = 9 jest przerośnięte.
  Dla x = 25: k(25) = 5. Istnieją liczby y > 25, np. y = 30, dla których k(30) = 4 < k(25). Zatem x = 25 jest przerośnięte.
  Powtarzając ten proces dla wszystkich liczb od 1 do 30, otrzymujemy 15 przerośniętych liczb.

Projekt powstał na podstawie zadania z XXII Olimpiady Informatycznej, etap I.

## Technologie
Projekt został zaimplementowany w języku Python. Wykorzystano:
- Programowanie dynamiczne do rozwiązywania problemu rozkładów liczb.
- Struktury danych do efektywnego zarządzania pamięcią (tablice dwuwymiarowe).

## Funkcjonalności
- Obliczenie wartości \(k(n)\) dla podanej liczby \(n\).
- Obliczenie liczby "przerośniętych" liczb mniejszych lub równych \(n\).
- Obsługa dużych zakresów wejściowych (do \(10^18\)).
- Obsługa plików wejściowych i wyjściowych.

## Uruchomienie
### Wymagania
- Python 3.12.8 lub nowszy

### Instalacja
1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/witek171/javaSquaresTask.git
   ```
2. Wejdź do katalogu projektu:
   ```bash
   cd javaSquaresTask
   ```
3. Utwórz i aktywuj wirtualne środowisko Python:
   ```bash
   python3 -m venv .venv
   source .venv/bin/activate  # Na systemach Linux/MacOS
   .venv\\Scripts\\activate   # Na systemach Windows
   ```
3. Uruchom skrypt:
   ```bash
   python3 main.py
   ```

### Struktura katalogów
- **inputs/**: Katalog z plikami wejściowymi.
- **outputs/**: Katalog, w którym zapisywane są wyniki.
- **main.py**: Główny plik projektu.

## Przykład użycia
Dla pliku wejściowego z zawartością:
```
30
```
Wynik zapisany w pliku wyjściowym to:
```
4 15
```

## Status projektu
Projekt jest ukończony.

## Możliwości rozwoju
- Optymalizacja dla dużych danych wejściowych (przekraczających obecny zakres \(10^18\)).
- Rozbudowa interfejsu użytkownika.
- Zastosowanie algorytmów równoległych do obliczeń na dużych danych.

## Podziękowania
- Inspiracja: XXII Olimpiada Informatyczna.

## Kontakt
Witold Pacholik - możesz się ze mną skontaktować na witoldpacholik@wp.pl
