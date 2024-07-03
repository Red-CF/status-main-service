package org.democat.status.main.service.domain.enums;

import lombok.Getter;

import java.util.Map;

@Getter
public enum ClassificationTypes {
    BREED("breed"),
    FACT("fact");

    private String value;

    ClassificationTypes(String  value) {
          this.value = value;
    }

    public static final Map<String,String> VALUES_MAP = Map.of(
            "breed", ClassificationTypes.BREED.getValue(),
            "fact", ClassificationTypes.BREED.getValue()
    );
}
