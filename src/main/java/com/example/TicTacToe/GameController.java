package com.example.TicTacToe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/game")
@CrossOrigin // Add this if you encounter CORS issues
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        // Assuming gameService.getStatus() always returns a valid response.
        return ResponseEntity.ok(gameService.getStatus());
    }

    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam @Min(0) @Max(2) int row, @RequestParam @Min(0) @Max(2) int col) {
        try {
            String response = gameService.makeMove(row, col);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/reset")
    public void resetGame() {
        gameService.reset();
        //return ResponseEntity.ok("Game reset!");
    }
}
