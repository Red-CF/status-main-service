package org.democat.status.main.service.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.democat.status.main.service.domain.enums.ClassificationTypes;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Dog {
    private Long id;
    private String name;
    private String fact;
    private ClassificationTypes classification;
}
