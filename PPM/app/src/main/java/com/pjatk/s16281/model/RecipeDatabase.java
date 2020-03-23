package com.pjatk.s16281.model;

import java.util.ArrayList;

public class RecipeDatabase {
    private ArrayList<RecipeItem> recipeItems = new ArrayList<>();

    public RecipeDatabase(){
        addSeveralRecipesToDatabase();
    }

    public ArrayList<RecipeItem> getRecipeItems(){
        return recipeItems;
    }

    public RecipeItem getSuitableRecipeByCalories(double calories ) {
        for (RecipeItem recipeItem : recipeItems){
            if (recipeItem.getCalories() <= calories && (recipeItem.getCalories() - calories) <= 300 ) {
                return recipeItem;
            }
        }
        return new RecipeItem("No suitable recipe found", "Please see more in the internet", 0.0);
    }

    private void addSeveralRecipesToDatabase(){

        recipeItems.add(
                new RecipeItem(
                        "Chicken in tomatoes",
                        "Buy chicken and put it in tomatoes, add below ingredients. Oven -200'-0,45[h]",
                        1600,
                        "chicken",
                        "tomatoes",
                        "rice",
                        "olive oil",
                        "pepper",
                        "salt",
                        0.3,
                        0.2,
                        0.2,
                        0.005,
                        0.005,
                        0.002
                )
        );
        recipeItems.add(
                new RecipeItem(
                        "Beef with green stuff and potatoes",
                        "Get the beef, bake it and cook some potatoes, add green stuff. You are done.",
                        2000,
                        "beef",
                        "broccoli",
                        "cucumber",
                        "potatoes",
                        "pepper",
                        "salt",
                        0.3,
                        0.3,
                        0.2,
                        0.3,
                        0.005,
                        0.002
                )
        );
        recipeItems.add(
                new RecipeItem(
                        "Kebab slim version",
                        "Buy prepared kebab meat and other separate products",
                        2500,
                        "kebab meat",
                        "lettuce",
                        "onion",
                        "ketchup",
                        "chili peppers",
                        "garlic sauce",
                        0.2,
                        0.2,
                        0.1,
                        0.01,
                        0.1,
                        0.01
                )
        );
        recipeItems.add(
                new RecipeItem(
                        "Kebab extra lamb",
                        "Buy prepared kebab meat and other separate products",
                        3500,
                        "kebab meat",
                        "lamb meat",
                        "onion",
                        "ketchup",
                        "chili peppers",
                        "garlic sauce",
                        0.4,
                        0.1,
                        0.1,
                        0.01,
                        0.1,
                        0.01
                )
        );
        recipeItems.add(
                new RecipeItem(
                        "Fit Salad for obesity N00b",
                        "Buy below ingredients, cut them in small parts and mix it with olive oil, but not to much",
                        1000,
                        "tomatoes",
                        "lettuce",
                        "onion",
                        "paprika",
                        "sunflower seeds",
                        "olive oil",
                        0.3,
                        0.3,
                        0.15,
                        0.2,
                        0.01,
                        0.01
                )
        );
        recipeItems.add(
                new RecipeItem(
                        "Fit Salad for obesity Master",
                        "Buy below ingredients, cut them in small parts and mix it with olive oil, but not to much",
                        500,
                        "cucumber",
                        "lettuce",
                        "onion",
                        "culiflower",
                        "sunflower seeds",
                        "sunflower oil",
                        0.3,
                        0.3,
                        0.15,
                        0.2,
                        0.01,
                        0.01
                )
        );

        recipeItems.add(
                new RecipeItem(
                        "Fit Salad for obesity Pro",
                        "Buy below ingredients, cut them in small parts and mix it with olive oil, but not to much",
                        300,
                        "cucumber",
                        "lettuce",
                        "paprika",
                        "apple",
                        "pumpkin seeds",
                        "rapeseed oil",
                        0.1,
                        0.3,
                        0.1,
                        0.2,
                        0.01,
                        0.01
                )
        );
    }




}
