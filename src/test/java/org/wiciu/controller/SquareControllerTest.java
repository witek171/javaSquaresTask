package org.wiciu.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wiciu.model.SquareResponse;
import org.wiciu.service.SquareService;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SquareControllerTest {

    @InjectMocks
    private SquareController squareController;

    @Mock
    private SquareService squareService;
    private SquareResponse validResponse;

    @BeforeEach
    void setUp() {
        validResponse = new SquareResponse("2", 0);
    }

    @Test
    void calculate_allParamsOk_correctResponse() {
        // Given
        when(squareService.calculateSquares(10)).thenReturn(validResponse);

        // When
        ResponseEntity<SquareResponse> responseEntity = squareController.calculate(10);

        // Then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(responseEntity
                .getBody())
                .getMinSquares())
                .isEqualTo("2");
        assertThat(responseEntity.getBody()
                .getOvergrownCount())
                .isEqualTo(0);
    }

    @Test
    void calculate_invalidNValue_errorResponse() {
        // When
        ResponseEntity<SquareResponse> responseEntity = squareController.calculate(-1);

        // Then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity
                .getBody())
                .getMessage())
                .isEqualTo("Error: Value of n must be greater than or equal to 1");
    }
}
