package org.wiciu.model;

import lombok.Getter;

@Getter
public class SquareResponse {
    private final String minSquares;  // String, aby przechowywać wynik w formacie "-"
    private final long overgrownCount;
    private final String message;

    public SquareResponse(String minSquares, long overgrownCount, String message) {
        this.minSquares = minSquares;
        this.overgrownCount = overgrownCount;
        this.message = message;
    }
}

