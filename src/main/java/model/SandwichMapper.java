package model;

import client.SandwichDto;

import java.util.Arrays;

public class SandwichMapper {
    public static Sandwich mapToSandwich(SandwichDto sandwichDto) {
        Sandwich sandwich = new Sandwich();
        sandwich.setType(sandwichDto.getType());
        sandwich.setPrice(sandwichDto.getPrice());
        sandwich.setId(sandwichDto.getId());
        sandwich.setName(sandwichDto.getName());
        Arrays.stream(sandwichDto.getIngredients().split(","))
                .map(s -> IngredientEnum.valueOf(s.toUpperCase()))
                .forEach(ing -> {
                    try {
                        sandwich.addIngredient(ing);
                    } catch (Exception e) {
                        System.out.println("Cant add to ingredients of the same category");
                    }
                });
        return sandwich;
    }

    public static SandwichDto mapToSandwichDto(Sandwich sandwich) {
        SandwichDto dto = new SandwichDto();
        dto.setType(sandwich.getType());
        dto.setPrice(sandwich.getPrice());
        dto.setId(sandwich.getId());
        dto.setName(sandwich.getName());

        String ingredients = "";
        Object[] ingredientEnums = sandwich.getIngredients().toArray();
        for (int i = 0; i < ingredientEnums.length; i++) {
            if (i == ingredientEnums.length -1) {
                ingredients = ingredients + ingredientEnums[i].toString();
            }else {
                ingredients = ingredients + ingredientEnums[i].toString() + ",";
            }

        }
        dto.setIngredients(ingredients);

        return dto;
    }
}
