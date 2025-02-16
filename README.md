# Zadanie Kwadraty - Projekt na studia

## Opis Projektu

Projekt rozwiązujący problem rozkładania liczb całkowitych na sumy różnych kwadratów dodatnich liczb całkowitych. Celem jest określenie:

- **Najmniejszej możliwej wartości największego kwadratu w rozkładzie liczby `n`, oznaczonej jako `k(n)`.**
- **Liczby "przerośniętych" liczb mniejszych lub równych `n`.**

Projekt powstał na podstawie zadania z XXII Olimpiady Informatycznej, etap I.

### Przykłady:

- Dla `n = 30`, `k(30) = 4`, liczba "przerośniętych" liczb wynosi 15.
- Dla `n = 8`, `k(8) = infinity`, liczba "przerośniętych" liczb wynosi 5.

### Definicja "przerośniętej liczby":
Liczba `x` jest "przerośnięta", jeśli istnieje liczba `y > x`, dla której `k(y) < k(x)`.

### Przykłady:

- Dla `x = 1`, `k(1) = 1`, nie ma liczb `y > 1`, dla których `k(y) < k(1)`, więc `x = 1` nie jest przerośnięte.
- Dla `x = 9`, `k(9) = 3`, istnieje liczba `y = 16`, dla której `k(16) = 4`, więc `x = 9` jest przerośnięte.

## Technologie

- **Java 21**
- **Spring Boot** jako framework do tworzenia RESTful API
- **Junit5 i Mockito** do stworzenia testów jednostkowych
- **Maven** do automatyzacji budowy
- **Lombok** do tworzenia getterów

## Funkcjonalności

- Obliczenie wartości `k(n)` dla podanej liczby `n`.
- Obliczenie liczby "przerośniętych" liczb mniejszych lub równych `n`.
- Obsługa dużych zakresów wejściowych, dzięki implementacji algorytmów obliczeniowych optymalizujących czas.
- Programowanie dynamiczne do rozwiązywania problemu rozkładów liczb.
- Obsługa wyjątków i walidacja danych wejściowych.

## Uruchomienie

### Wymagania:

- JDK 17 lub nowsza
- Wolny port 8084

### Instalacja:

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/witek171/javaSquaresTask.git
   ```
2. Wejdź do katalogu projektu:
   ```bash
   cd javaSquaresTask
   ```
3. Uruchom aplikację:
   ```bash
   ./mvnw spring-boot:run
   ```
Lub uruchom plik jar
   ```bash
   javaSquaresTask-1.0.0
   ```
### Struktura katalogów
- src/main/java/org/wiciu: Katalog zawierający katalogi z plikami z kodem źródłowym aplikacji.
- src/main/resources: Katalog z plikami konfiguracyjnymi.
- pom.xml: Główny plik konfiguracyjny projektu Maven.
- target/: Katalog, w którym kompilowane są pliki wynikowe.

## Przykład użycia
- API dostępne jest pod adresem /api/squares/calculate. Można obliczyć wartość k(n) i liczbę "przerośniętych" liczb dla liczby n.
- Przykładowe zapytanie:
```bash
GET http://localhost:8084/api/squares/calculate?n=30
```
W odpowiedzi otrzymamy:

{
  "minSquares": "4",
  "overgrownCount": 15
}

## Status projektu
- Projekt jest ukończony i działa zgodnie z wymaganiami.
## Możliwości rozwoju
- Optymalizacja dla dużych danych wejściowych (przekraczających obecny zakres).
- Rozbudowa interfejsu użytkownika.
- Rozszerzenie funkcjonalności o obliczenia równoległe dla dużych zbiorów danych.
## Podziękowania
- Inspiracja: XXII Olimpiada Informatyczna.
## Kontakt
- Witold Pacholik - możesz skontaktować się ze mną na witoldpacholik@wp.pl
