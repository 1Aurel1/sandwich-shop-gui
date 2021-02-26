package client;

import lombok.Getter;
import lombok.Setter;
import model.SandwichType;

@Getter
@Setter
public class SandwichDto {
    private Long id;
    private String name;
    private SandwichType type;
    private String ingredients;
    private Double price;
}
