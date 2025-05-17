package br.com.budget.record.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RecordDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private Long categoryId;
}

