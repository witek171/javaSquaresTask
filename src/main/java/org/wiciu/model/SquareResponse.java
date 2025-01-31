package org.wiciu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Pomija pola o wartości null
public class SquareResponse {
    private String minSquares;  // String, aby przechowywać wynik w formacie "-"
    private Long overgrownCount; // Long aby uniknac domyslnej wartosci '0'
    private String message;

    public SquareResponse(String message) {
        this.message = message;
    }

    public SquareResponse(String minSquares, long overgrownCount) {
        this.minSquares = minSquares;
        this.overgrownCount = overgrownCount;
    }
}

