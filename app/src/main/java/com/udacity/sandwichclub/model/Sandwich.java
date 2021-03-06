package com.udacity.sandwichclub.model;

import java.util.List;

public class Sandwich {
    private Name name = new Name();
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(Name name, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.name = name;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() { return name.getMainName(); }

    public void setMainName(String mainName) { this.name.setMainName(mainName); }

    public List<String> getAlsoKnownAs() { return name.getAlsoKnownAs(); }

    public void setAlsoKnownAs(List<String> alsoKnownAs) { this.name.setAlsoKnownAs(alsoKnownAs); }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}

