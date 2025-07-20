package com.PentaProject.demoBackend.Model;




import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document("abilities")
@AllArgsConstructor
public class Ability {
    private String name;
    private Integer manacost;
    private String description;
}
