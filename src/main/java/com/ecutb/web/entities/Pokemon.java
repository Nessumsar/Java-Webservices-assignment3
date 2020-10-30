package com.ecutb.web.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon implements Serializable {
    private static final long serialVersionUID = 96348939829L;

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
