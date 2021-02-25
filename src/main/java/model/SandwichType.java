package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SandwichType {
    DINNER("Dinner"),
    LUNCH("Lunch");

    private final String name;
}
