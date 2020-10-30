package com.ecutb.web.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    @Id
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("types")
    private List<Object> types;
    @JsonProperty("height")
    private int height;
    @JsonProperty("weight")
    private int weight;
}
