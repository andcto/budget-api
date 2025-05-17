package br.com.budget.record.repository;

import br.com.budget.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUserId(Long userId);
    List<Record> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}

