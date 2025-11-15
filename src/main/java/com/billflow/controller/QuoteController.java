package com.billflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billflow.dto.QuoteDTO;
import com.billflow.service.QuoteService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public List<QuoteDTO> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDTO> getQuoteById(@PathVariable Long id) {
        Optional<QuoteDTO> quote = quoteService.getQuoteById(id);
        return quote.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<QuoteDTO> getQuotesByStatus(@PathVariable String status) {
        return quoteService.getQuotesByStatus(status);
    }

    @GetMapping("/client/{clientId}")
    public List<QuoteDTO> getQuotesByClientId(@PathVariable Long clientId) {
        return quoteService.getQuotesByClientId(clientId);
    }

    @GetMapping("/stats")
    public Map<String, Long> getQuoteStats() {
        return quoteService.getQuoteStats();
    }

    @PostMapping
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO quoteDTO) {
        QuoteDTO created = quoteService.createQuote(quoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteDTO> updateQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO) {
        Optional<QuoteDTO> updated = quoteService.updateQuote(id, quoteDTO);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateQuoteStatus(@PathVariable Long id, @RequestParam String status) {
        boolean success = quoteService.updateQuoteStatus(id, status);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        boolean success = quoteService.deleteQuote(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}