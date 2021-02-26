package model;

import client.RestClient;
import client.SandwichDto;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class SandwichShop {

    private Set<Sandwich> lunch;
    private Set<Sandwich> dinner;
    private List<Sandwich> inOrder;

    public SandwichShop() {
        loadDataFromRest();
        inOrder = new ArrayList<>();
    }



    public void addSandwichTOOrder(Sandwich sandwich) {
        inOrder.add(sandwich);
    }

    public void removeSandwichTOOrder(Sandwich sandwich) {
        inOrder.remove(sandwich);
    }

    public Order buildOrder() {

        Order order = new Order();
        order.setSandwiches(inOrder);
        order.setTotalAmount(calculateOrderTotalAmount());

        return order;
    }

    private Double calculateOrderTotalAmount() {
        return inOrder.stream()
                .map(Sandwich::getPrice)
                .reduce(0D, Double::sum);
    }

    public String[][] getSandwichesOnType(SandwichType sandwichType) {

        loadDataFromRest();

        switch (sandwichType) {
            case LUNCH:
                return convertSandwiches(lunch);
            case DINNER:
                return convertSandwiches(dinner);
        }

        return null;
    }

    private void loadDataFromRest() {
        RestClient restClient = new RestClient();

        List<SandwichDto> sandwiches = restClient.getSandwiches();

        lunch = sandwiches.stream()
                .filter(sandwichDto -> sandwichDto.getType() == SandwichType.LUNCH)
                .map(SandwichMapper::mapToSandwich)
                .collect(Collectors.toSet());

        dinner = sandwiches.stream()
                .filter(sandwichDto -> sandwichDto.getType() == SandwichType.DINNER)
                .map(SandwichMapper::mapToSandwich)
                .collect(Collectors.toSet());
    }

    public String[][] getOrderSandwiches() {
        return convertSandwiches(inOrder);
    }

    private String[][] convertSandwiches(Collection<Sandwich> sandwiches) {
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
