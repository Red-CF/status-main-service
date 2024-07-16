package org.democat.status.main.service.web.client.models.breeds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributesMO {
    private String name;
    private String description;
    @JsonProperty(namespace = "hypoallergenic")
    private boolean isHypoallergenic;
}
