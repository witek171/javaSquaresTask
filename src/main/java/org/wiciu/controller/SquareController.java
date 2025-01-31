package org.wiciu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wiciu.model.SquareResponse;
import org.wiciu.service.SquareService;

@RestController
@RequestMapping("/api/squares")
class SquareController {
    private final SquareService squareService;

    public SquareController(SquareService squareService) {
        this.squareService = squareService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<SquareResponse> calculate(@RequestParam long n) {
        try {
            if (n < 1) {
                throw new IllegalArgumentException("Value of n must be greater than or equal to 1");
            }

            return ResponseEntity.ok(squareService.calculateSquares(n));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SquareResponse("Error: " + e.getMessage()));
        }
    }
}
