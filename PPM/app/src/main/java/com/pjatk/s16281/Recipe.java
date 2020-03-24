package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pjatk.s16281.model.RecipeDatabase;
import com.pjatk.s16281.model.RecipeItem;


public class Recipe extends AppCompatActivity {
    private Boolean recipeDisplayed;
    private RecipeDatabase recipes;
    private RecipeItem selectedRecipe;
    private double bmiPassed;
    private double ppmPassed;

    private TextView bmi;
    private TextView ppm;
    private TextView title;
    private TextView description;
    private TextView calories;
    private TextView ingredient1;
    private TextView ingredient2;
    private TextView ingredient3;
    private TextView ingredient4;
    private TextView ingredient5;
    private TextView ingredient6;
    private TextView amount1;
    private TextView amount2;
    private TextView amount3;
    private TextView amount4;
    private TextView amount5;
    private TextView amount6;
    private ImageView recipeImage;
    private Button getRecipe;
    private Button backToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        // init views from input
        bmi = findViewById(R.id.bmi_view);
        ppm = findViewById(R.id.ppm_view);
        title = findViewById(R.id.view_title);
        description = findViewById(R.id.view_description);
        calories = findViewById(R.id.view_calories);
        ingredient1 = findViewById(R.id.view_ingr1);
        ingredient2 = findViewById(R.id.view_ingr2);
        ingredient3 = findViewById(R.id.view_ingr3);
        ingredient4 = findViewById(R.id.view_ingr4);
        ingredient5 = findViewById(R.id.view_ingr5);
        ingredient6 = findViewById(R.id.view_ingr6);
        amount1 = findViewById(R.id.view_amnt1);
        amount2 = findViewById(R.id.view_amnt2);
        amount3 = findViewById(R.id.view_amnt3);
        amount4 = findViewById(R.id.view_amnt4);
        amount5 = findViewById(R.id.view_amnt5);
        amount6 = findViewById(R.id.view_amnt6);
        //recipeImage = findViewById(R.id.recipe_image);


        // init recipe db and boolean flag
        recipes = new RecipeDatabase();
        recipeDisplayed = false;

        // retrieve passed weight and height
        bmiPassed = getIntent().getDoubleExtra("bmi_passed",0.0);
        ppmPassed = getIntent().getDoubleExtra("ppm_passed",0.0);

        // set weight and height to inputs fields to be user-visible
        bmi.setText(  bmiPassed == 0 ? "Did you calculate it?" : Double.toString(bmiPassed) );
        ppm.setText(  ppmPassed == 0 ? "Did you calculate it?" : Double.toString(ppmPassed) );

        // hide items by default
        hideAllRecipeItems();

        // BTN initialize
        backToMain = findViewById(R.id.recipe_back);
        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        getRecipe = findViewById(R.id.recipe_button);
        getRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if( bmiPassed != 0 && ppmPassed !=0 && !recipeDisplayed){
                    displaySuitableRecipe();
                    recipeDisplayed = !recipeDisplayed;
                }
            }
        });

    }

    /*private void displayImage(ImageView imageViewLinking, String pictureUrl ) throws IOException {
        URL url = new URL(pictureUrl);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        imageViewLinking.setImageBitmap(bmp);
    }*/

    private void displaySuitableRecipe(){
        selectedRecipe = recipes.getSuitableRecipeByCalories(ppmPassed);
        displayAllRecipeItems();

        title.append(selectedRecipe.getTitle());
        description.append(selectedRecipe.getDescription());
        calories.append(Double.toString(selectedRecipe.getCalories()));
        ingredient1.append((selectedRecipe.getIngredient_1()));
        ingredient2.append((selectedRecipe.getIngredient_2()));
        ingredient3.append((selectedRecipe.getIngredient_3()));
        ingredient4.append((selectedRecipe.getIngredient_4()));
        ingredient5.append((selectedRecipe.getIngredient_5()));
        ingredient6.append((selectedRecipe.getIngredient_6()));
        amount1.append((Double.toString(selectedRecipe.getAmount_1())));
        amount2.append((Double.toString(selectedRecipe.getAmount_2())));
        amount3.append((Double.toString(selectedRecipe.getAmount_3())));
        amount4.append((Double.toString(selectedRecipe.getAmount_4())));
        amount5.append((Double.toString(selectedRecipe.getAmount_5())));
        amount6.append((Double.toString(selectedRecipe.getAmount_6())));
        /*try{
            Picasso.with(getBaseContext()).load(selectedRecipe.getPhoto()).into(recipeImage);
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }*/

    }


    private void hideAllRecipeItems(){
        title.setVisibility(View.INVISIBLE);
        description.setVisibility(View.INVISIBLE);
        calories.setVisibility(View.INVISIBLE);
        ingredient1.setVisibility(View.INVISIBLE);
        ingredient2.setVisibility(View.INVISIBLE);
        ingredient3.setVisibility(View.INVISIBLE);
        ingredient4.setVisibility(View.INVISIBLE);
        ingredient5.setVisibility(View.INVISIBLE);
        ingredient6.setVisibility(View.INVISIBLE);
        amount1.setVisibility(View.INVISIBLE);
        amount2.setVisibility(View.INVISIBLE);
        amount3.setVisibility(View.INVISIBLE);
        amount4.setVisibility(View.INVISIBLE);
        amount5.setVisibility(View.INVISIBLE);
        amount6.setVisibility(View.INVISIBLE);
        //recipeImage.setVisibility(View.INVISIBLE);
    }

    private void displayAllRecipeItems(){
        title.setVisibility(View.VISIBLE);
        description.setVisibility(View.VISIBLE);
        calories.setVisibility(View.VISIBLE);
        ingredient1.setVisibility(View.VISIBLE);
        ingredient2.setVisibility(View.VISIBLE);
        ingredient3.setVisibility(View.VISIBLE);
        ingredient4.setVisibility(View.VISIBLE);
        ingredient5.setVisibility(View.VISIBLE);
        ingredient6.setVisibility(View.VISIBLE);
        amount1.setVisibility(View.VISIBLE);
        amount2.setVisibility(View.VISIBLE);
        amount3.setVisibility(View.VISIBLE);
        amount4.setVisibility(View.VISIBLE);
        amount5.setVisibility(View.VISIBLE);
        amount6.setVisibility(View.VISIBLE);
        //recipeImage.setVisibility(View.VISIBLE);
    }

}

