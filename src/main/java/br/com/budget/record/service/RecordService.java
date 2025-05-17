package br.com.budget.record.service;

import br.com.budget.category.model.Category;
import br.com.budget.category.repository.CategoryRepository;
import br.com.budget.record.dto.RecordDTO;
import br.com.budget.record.model.Record;
import br.com.budget.record.repository.RecordRepository;
import br.com.budget.user.model.User;
import br.com.budget.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public Record createRecord(RecordDTO dto, Long userId) {
        Record record = new Record();
        record.setDescription(dto.getDescription());
        record.setAmount(dto.getAmount());
        record.setDate(dto.getDate());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        record.setCategory(category);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        record.setUser(user);

        return recordRepository.save(record);
    }

    public List<Record> getAllByUser(Long userId) {
        return recordRepository.findByUserId(userId);
    }

    public Optional<Record> getById(Long id, Long userId) {
        return recordRepository.findById(id)
                .filter(record -> record.getUser().getId().equals(userId));
    }

    public Record updateRecord(Long id, RecordDTO dto, Long userId) {
        Record record = recordRepository.findById(id)
                .filter(i -> i.getUser().getId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Registro não encontrada ou acesso negado"));

        record.setDescription(dto.getDescription());
        record.setAmount(dto.getAmount());
        record.setDate(dto.getDate());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        record.setCategory(category);

        return recordRepository.save(record);
    }

    public void deleteRecord(Long id, Long userId) {
        Record record = recordRepository.findById(id)
                .filter(i -> i.getUser().getId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Registro não encontrada ou acesso negado"));

        recordRepository.delete(record);
    }
}

