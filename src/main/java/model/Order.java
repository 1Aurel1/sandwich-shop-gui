package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class Order {

    @Setter
    @Getter
    private List<Sandwich> sandwiches;

    @Setter
    @Getter
    private Double totalAmount;

    public Order() {
        sandwiches = new ArrayList<>();
    }

}
