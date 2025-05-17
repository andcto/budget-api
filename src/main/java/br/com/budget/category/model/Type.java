package br.com.budget.category.model;

import lombok.Getter;

@Getter
public enum Type {
    EXPENSE("Expense"),
    INCOME("Income");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }
}
