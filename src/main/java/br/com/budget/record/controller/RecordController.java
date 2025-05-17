package br.com.budget.record.controller;

import br.com.budget.auth.service.AuthenticatedUserService;
import br.com.budget.record.dto.RecordDTO;
import br.com.budget.record.model.Record;
import br.com.budget.record.service.RecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
@AllArgsConstructor
public class RecordController {

    private final RecordService recordService;

    private final AuthenticatedUserService authenticatedUserService;

    @PostMapping
    public ResponseEntity<Record> create(@RequestBody @Valid RecordDTO dto) {
        Record created = recordService.createRecord(dto, authenticatedUserService.getLoggedUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Record>> getAll() {
        List<Record> records = recordService.getAllByUser(authenticatedUserService.getLoggedUserId());
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getById(@PathVariable Long id) {
        return recordService.getById(id, authenticatedUserService.getLoggedUserId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Record> update(@PathVariable Long id, @RequestBody @Valid RecordDTO dto) {
        Record updated = recordService.updateRecord(id, dto, authenticatedUserService.getLoggedUserId());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recordService.deleteRecord(id, authenticatedUserService.getLoggedUserId());
        return ResponseEntity.noContent().build();
    }
}

