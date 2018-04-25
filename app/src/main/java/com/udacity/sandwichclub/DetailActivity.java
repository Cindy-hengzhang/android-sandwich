package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView sOriginTextView = (TextView) findViewById(R.id.origin_tv);
        TextView sDescriptionTextView = (TextView) findViewById(R.id.description_tv);
        TextView sIngredientTextView = (TextView) findViewById(R.id.ingredients_tv);
        TextView sAlsoKnowTextView = (TextView) findViewById(R.id.also_known_tv);

        StringBuilder ingredientsStrBuilder = new StringBuilder();
        List<String> ingredients = sandwich.getIngredients();

        StringBuilder alsoKnownAsStrBuilder = new StringBuilder();
        List<String> knownAslst = sandwich.getAlsoKnownAs();
        System.out.println(knownAslst.size());

        try {
            for (String ingredient : ingredients) {
                ingredientsStrBuilder.append(ingredient + "\n");
            }
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
        try {
            for (String knowAs : knownAslst) {
                alsoKnownAsStrBuilder.append(knowAs + "\n");
            }
        }catch(NullPointerException e) {
            e.printStackTrace();
        }

        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if(placeOfOrigin == null || placeOfOrigin.length() == 0) {
            sOriginTextView.setText("None");
        } else {
            sOriginTextView.setText(placeOfOrigin);
        }

        String description = sandwich.getDescription();
        if(description == null || description.length() == 0) {
            sDescriptionTextView.setText("None");
        } else {
            sDescriptionTextView.setText(description);
        }

        if(ingredientsStrBuilder.length() == 0) {
            sIngredientTextView.setText("None");
        } else {
            ingredientsStrBuilder.deleteCharAt(ingredientsStrBuilder.length() - 1);
            sIngredientTextView.setText(ingredientsStrBuilder.toString());
        }

        if(alsoKnownAsStrBuilder.length() == 0) {
            sAlsoKnowTextView.setText("None");
        } else {
            alsoKnownAsStrBuilder.deleteCharAt(alsoKnownAsStrBuilder.length() - 1);
            sAlsoKnowTextView.setText(alsoKnownAsStrBuilder.toString());
        }
    }
}
