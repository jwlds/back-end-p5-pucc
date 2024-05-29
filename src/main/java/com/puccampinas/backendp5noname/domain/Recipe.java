package com.puccampinas.backendp5noname.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.puccampinas.backendp5noname.dtos.RecipeDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="recipes")
public class Recipe {
    @Id
    private String id;
    private String name;
    private List<String> ingredients;
    private List<String> preparationMethod;
    private int preparationTime;
    @JsonFormat(pattern="dd/MM/yyyy 'as' HH:mm:ss")
    private LocalDateTime createdAt;


    public Recipe(RecipeDTO data) {
        this.name = data.getName();
        this.ingredients = data.getIngredients();
        this.preparationMethod = data.getPreparationMethod();
        this.preparationTime = data.getPreparationTime();
        this.createdAt = LocalDateTime.now();
    }
}
