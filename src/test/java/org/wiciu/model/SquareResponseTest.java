package org.wiciu.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareResponseTest {

    @Test
    void constructor_withMessage_correctlySetsMessage() {
        // Given
        String message = "Test Message";

        // When
        SquareResponse squareResponse = new SquareResponse(message);

        // Then
        assertThat(squareResponse.getMessage()).isEqualTo(message);
    }

    @Test
    void constructor_withMinSquaresAndOvergrownCount_correctlySetsValues() {
        // Given
        String minSquares = "5";
        long overgrownCount = 3;

        // When
        SquareResponse squareResponse = new SquareResponse(minSquares, overgrownCount);

        // Then
        assertThat(squareResponse.getMinSquares()).isEqualTo(minSquares);
        assertThat(squareResponse.getOvergrownCount()).isEqualTo(overgrownCount);
    }
}
