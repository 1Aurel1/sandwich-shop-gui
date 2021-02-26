package model;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SandwichShopTest {

    private SandwichTest sandwichTest = new SandwichTest();

    @Test(expected = Exception.class)
    public void shouldThrowAnException(){

    }

    @Test
    @SneakyThrows
    public void shouldHaveOrderTotalAmount10(){
        List<Sandwich> sandwiches = new ArrayList<>();

        Sandwich sandwich = new Sandwich();
        sandwich.addIngredient(IngredientEnum.HAM);
        sandwich.addIngredient(IngredientEnum.MOZZARELLA);
        sandwich.addIngredient(IngredientEnum.MUSTARD);
        sandwich.setPrice(5D);

        sandwiches.add(sandwich);
        sandwiches.add(sandwich);

        SandwichShop sandwichShop = new SandwichShop();
        sandwichShop.setInOrder(sandwiches);
        Order order = sandwichShop.buildOrder();
        Assert.assertTrue(order.getTotalAmount().equals(10D));
    }
}
