package model;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Sandwich implements IdentifiableModel {

    private Long id;
    private String name;
    private SandwichType type;
    private Set<IngredientEnum> ingredients;
    private Double price;

    public Sandwich() {
        ingredients = new HashSet<>();
    }

    public void addIngredient(IngredientEnum ingredient) throws Exception {
        validateAddedIngredient(ingredient);
        ingredients.add(ingredient);
    }

    private void validateAddedIngredient(IngredientEnum ingredient) throws Exception {

        boolean alreadyContainsIngredientFromCategory = ingredients.stream()
                    .map(IngredientEnum::getCategory)
                    .anyMatch(category -> ingredient.getCategory().equals(category));

        if (alreadyContainsIngredientFromCategory) {
            throw new Exception("Can't add two ingredient of the same category");
        }
    }

    public void removeIngredient(IngredientEnum ingredient) {
        ingredients.remove(ingredient);
    }

    public List<String> isValid() {
        return null;
    }

    public void saveSandwich() {

    }

    public static String[] sandwichData() {
        return new String[]{"Id", "Name", "Type", "Price"};
    }

    public String[] toStringArray() {

        String[] strings = new String[4];
        strings[0] = id.toString();
        strings[1] = name;
        strings[2] = type.getName();
        strings[3] = price.toString();

        return strings;
    }
}
