package com.billflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billflow.dto.QuoteDTO;
import com.billflow.mapper.QuoteMapper;
import com.billflow.model.Quote;
import com.billflow.repository.QuoteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public List<QuoteDTO> getAllQuotes() {
        return quoteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<QuoteDTO> getQuoteById(Long id) {
        return quoteRepository.findById(id).map(this::toDTO);
    }

    public List<QuoteDTO> getQuotesByStatus(String status) {
        return quoteRepository.findByStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<QuoteDTO> getQuotesByClientId(Long clientId) {
        return quoteRepository.findByClientId(clientId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Map<String, Long> getQuoteStats() {
        List<Quote> quotes = quoteRepository.findAll();
        Map<String, Long> stats = new HashMap<>();
        for (Quote quote : quotes) {
            stats.put(quote.getStatus(), stats.getOrDefault(quote.getStatus(), 0L) + 1);
        }
        return stats;
    }

    public QuoteDTO createQuote(QuoteDTO quoteDTO) {
    	Quote quote=QuoteMapper.toEntity(quoteDTO);
        quote = quoteRepository.save(quote);
        return toDTO(quote);
    }

    public Optional<QuoteDTO> updateQuote(Long id, QuoteDTO quoteDTO) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote.setClientId(quoteDTO.getClientId());
            quote.setStatus(quoteDTO.getStatus());
            quote.setDescription(quoteDTO.getDescription());
            quote.setTotalAmount(quoteDTO.getTotalAmount());
            quote.setUpdatedAt(java.time.LocalDateTime.now());
            quote = quoteRepository.save(quote);
            return Optional.of(toDTO(quote));
        }
        return Optional.empty();
    }

    public boolean updateQuoteStatus(Long id, String status) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote.setStatus(status);
            quote.setUpdatedAt(java.time.LocalDateTime.now());
            quoteRepository.save(quote);
            return true;
        }
        return false;
    }

    public boolean deleteQuote(Long id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private QuoteDTO toDTO(Quote quote) {
    	return QuoteMapper.toDTO(quote);
    }
}