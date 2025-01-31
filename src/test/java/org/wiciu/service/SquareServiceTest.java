package org.wiciu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wiciu.model.SquareResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SquareServiceTest {

    @InjectMocks
    private SquareService squareService;

    @BeforeEach
    void setUp() {
        squareService = new SquareService();
    }

    @Test
    void preprocess_initializesDataCorrectly() {
        assertThat(squareService).isNotNull();
    }

    @Test
    void calculateSquares_smallNumber_returnsCorrectResponse() {
        // Given
        long n = 10;

        // When
        SquareResponse response = squareService.calculateSquares(n);

        // Then
        assertThat(response.getMinSquares()).isEqualTo("3");
        assertThat(response.getOvergrownCount()).isEqualTo(5);
    }

    @Test
    void calculateSquares_largeNumber_returnsCorrectResponse() {
        // Given
        long n = 1000;

        // When
        SquareResponse response = squareService.calculateSquares(n);

        // Then
        assertThat(response.getMinSquares()).isEqualTo("14");
        assertThat(response.getOvergrownCount()).isEqualTo(234);
    }

    @Test
    void calculateSquares_valueAboveMax_returnsCorrectResponse() {
        // Given
        long n = 10000;

        // When
        SquareResponse response = squareService.calculateSquares(n);

        // Then
        assertThat(response.getMinSquares()).isNotEmpty();
        assertThat(response.getOvergrownCount()).isGreaterThan(0);
    }

    @Test
    void countOvergrownNumbers_normalValue_returnsCorrectCount() {
        // Given
        int n = 30;

        // When
        int result = squareService.countOvergrownNumbers(n);

        // Then
        assertThat(result).isEqualTo(15);
    }

    @Test
    void countOvergrownNumbers_overgrownCount_returnsCorrectCount() {
        // Given
        int n = 200;

        // When
        int result = squareService.countOvergrownNumbers(n);

        // Then
        assertThat(result).isGreaterThan(0);
    }

    @Test
    void calculateLargeNumber_largeNumber_returnsCorrectResponse() {
        // Given
        long n = 5000;

        // When
        SquareResponse response = squareService.calculateLargeNumber(n);

        // Then
        assertThat(response.getMinSquares()).isNotEmpty();
        assertThat(response.getOvergrownCount()).isGreaterThan(0);
    }

    @Test
    void calculateLargeNumber_nearMagicConstant_returnsCorrectResponse() {
        // Given
        long n = 900;

        // When
        SquareResponse response = squareService.calculateLargeNumber(n);

        // Then
        assertThat(response.getMinSquares()).isNotEmpty();
        assertThat(response.getOvergrownCount()).isGreaterThan(0);
    }

    @Test
    void calculateSquares_edgeCase_granularValues() {
        // Given
        long n = 1;

        // When
        SquareResponse response = squareService.calculateSquares(n);

        // Then
        assertThat(response.getMinSquares()).isEqualTo("1");
        assertThat(response.getOvergrownCount()).isEqualTo(0);
    }

    @Test
    void calculateSquares_maxSquareSumValue() {
        // Given
        long n = 630;

        // When
        SquareResponse response = squareService.calculateSquares(n);

        // Then
        assertThat(response.getMinSquares()).isNotEmpty();
        assertThat(response.getOvergrownCount()).isEqualTo(196);
    }
}
