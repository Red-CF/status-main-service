package org.democat.status.main.service.domain.enums;

import lombok.Getter;

import java.util.Map;

@Getter
public enum DogNamesTypes {

    MARCO("marco"),
    NALA("nala"),
    REY_MISTERIO("rey_misterio");

    private final String name;

    DogNamesTypes(String name) {
        this.name = name;
    }

    public static final Map<String,String> VALUES_MAP = Map.of(
            "marco", DogNamesTypes.MARCO.getName(),
            "nala", DogNamesTypes.NALA.getName(),
            "rey_misterio", DogNamesTypes.REY_MISTERIO.getName()
    );
}
