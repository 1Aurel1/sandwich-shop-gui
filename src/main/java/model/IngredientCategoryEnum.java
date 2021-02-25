package model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum IngredientCategoryEnum {

    FILLING(1),
    CHEESES(2),
    SAUCES(3);

    private final Integer id;
}
