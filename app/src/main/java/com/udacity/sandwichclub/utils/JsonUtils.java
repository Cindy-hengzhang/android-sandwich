package com.udacity.sandwichclub.utils;

import com.google.gson.Gson;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    // using third party library for parsing JSON
    public static Sandwich parseSandwichJson (String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Sandwich.class);
    }

    // using JSON object and array to parse
    public static Sandwich parseSandwichJson (String json, final String method){
        Sandwich sandwich = new Sandwich();
        try {
            final JSONObject obj = new JSONObject(json);
            final JSONObject sub_obj = obj.getJSONObject("name");

            sandwich.setMainName(sub_obj.getString("mainName"));

            JSONArray alsoKnownAsJSONArr = sub_obj.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsLst = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSONArr.length(); i++) {
                alsoKnownAsLst.add(alsoKnownAsJSONArr.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAsLst);

            sandwich.setPlaceOfOrigin(obj.getString("placeOfOrigin"));

            sandwich.setDescription(obj.getString("description"));

            sandwich.setImage(obj.getString("image"));

            JSONArray ingredientsJSONArr = obj.getJSONArray("ingredients");
            List<String> ingredientsLst = new ArrayList<>();
            for (int i = 0; i < ingredientsJSONArr.length(); i++) {
                ingredientsLst.add(ingredientsJSONArr.getString(i));
            }
            sandwich.setIngredients(ingredientsLst);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

}
