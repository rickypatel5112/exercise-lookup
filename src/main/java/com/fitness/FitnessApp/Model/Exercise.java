package com.fitness.FitnessApp.Model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
//import javax.persistence.Entity;
//import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "type",
        "muscle",
        "equipment",
        "difficulty",
        "instructions"
})
@Generated("jsonschema2pojo")
//@Entity
public class Exercise {

    @JsonProperty("name")
//    @Id
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("muscle")
    private String muscle;
    @JsonProperty("equipment")
    private String equipment;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("instructions")
    private String instructions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

//    @JsonProperty("name")
    public String getName() {
        return name;
    }

//    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

//    @JsonProperty("type")
    public String getType() {
        return type;
    }

//    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

//    @JsonProperty("muscle")
    public String getMuscle() {
        return muscle;
    }

//    @JsonProperty("equipment")
    public String getEquipment() {
        return equipment;
    }

//    @JsonProperty("difficulty")
    public String getDifficulty() {
        return difficulty;
    }

//    @JsonProperty("instructions")
public String getInstructions() {
    return instructions;
}

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", muscle='" + muscle + '\'' +
                ", equipment='" + equipment + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}