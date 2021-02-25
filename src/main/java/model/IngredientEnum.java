package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IngredientEnum {

    HAM("Ham", IngredientCategoryEnum.FILLING),
    SALAMI("Salami", IngredientCategoryEnum.FILLING),
    SOTTILETTA("Sottiletta", IngredientCategoryEnum.CHEESES),
    MOZZARELLA("Mozzarella", IngredientCategoryEnum.CHEESES),
    MAYONNAISE("Mayonnaise", IngredientCategoryEnum.SAUCES),
    MUSTARD("Mustard", IngredientCategoryEnum.SAUCES);

    private final String name;
    private final IngredientCategoryEnum category;
}
