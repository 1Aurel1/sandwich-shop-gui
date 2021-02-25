package model;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

public class SandwichTest {

    @Test
    @SneakyThrows
    public void shouldAddTheIngredient() {

        Sandwich sandwich = new Sandwich();
        sandwich.addIngredient(IngredientEnum.HAM);
        sandwich.addIngredient(IngredientEnum.MOZZARELLA);
        sandwich.addIngredient(IngredientEnum.MUSTARD);
        sandwich.setPrice(5D);

        Assert.assertTrue(sandwich.getIngredients().contains(IngredientEnum.HAM));
    }

    @Test(expected = Exception.class)
    @SneakyThrows
    public void shouldThrowAnException() {

        Sandwich sandwich = new Sandwich();
        sandwich.addIngredient(IngredientEnum.HAM);
        sandwich.addIngredient(IngredientEnum.SALAMI);
    }

    @SneakyThrows
    @Test
    public void shouldRemoveIngredient() {

        Sandwich sandwich = new Sandwich();

        sandwich.addIngredient(IngredientEnum.HAM);
        sandwich.addIngredient(IngredientEnum.MOZZARELLA);
        sandwich.addIngredient(IngredientEnum.MUSTARD);
        sandwich.setPrice(5D);

        sandwich.removeIngredient(IngredientEnum.HAM);

        Assert.assertFalse(sandwich.getIngredients().contains(IngredientEnum.HAM));
    }
}
