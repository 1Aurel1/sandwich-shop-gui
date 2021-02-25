package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class SandwichShop {

    private Set<Sandwich> lunch;
    private Set<Sandwich> dinner;
    private List<Sandwich> inOrder;

    public SandwichShop() {
        lunch = new HashSet<>();
        dinner = new HashSet<>();
        inOrder = new ArrayList<>();
    }

    public void addSandwichTOOrder(Sandwich sandwich) {
        inOrder.add(sandwich);
    }

    public void removeSandwichTOOrder(Sandwich sandwich) {
        inOrder.remove(sandwich);
    }

    public Order buildOrder(List<Sandwich> sandwiches) {

        Order order = new Order();
        order.setSandwiches(sandwiches);
        order.setTotalAmount(calculateOrderTotalAmount(sandwiches));

        return order;
    }

    private Double calculateOrderTotalAmount(List<Sandwich> sandwiches) {
        return sandwiches.stream()
                .map(Sandwich::getPrice)
                .reduce(0D, Double::sum);
    }

    public String[][] getSandwichesOnType(SandwichType sandwichType) {

        switch (sandwichType) {
            case LUNCH:
                return convertSandwiches(lunch);
            case DINNER:
                return convertSandwiches(dinner);
        }

        return null;
    }

    private String[][] convertSandwiches(Set<Sandwich> sandwiches) {
        List<String[]> list = new ArrayList<>();
        for (Sandwich sandwich : sandwiches) {
            String[] toStringArray = sandwich.toStringArray();
            list.add(toStringArray);
        }

        String[][] strings = new String[list.size()][];
        for (int i = 0; i <list.size(); i++) {
            strings[i] = list.get(i);
        }
        if (strings != null)
            return strings;

        return new String[][]{};
    }
}
