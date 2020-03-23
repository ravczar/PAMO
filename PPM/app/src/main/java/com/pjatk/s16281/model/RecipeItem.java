package com.pjatk.s16281.model;

public class RecipeItem {
    private String title;
    private String description;
    private double calories;

    private String ingredient_1;
    private String ingredient_2;
    private String ingredient_3;
    private String ingredient_4;
    private String ingredient_5;
    private String ingredient_6;

    private double amount_1;
    private double amount_2;
    private double amount_3;
    private double amount_4;
    private double amount_5;
    private double amount_6;

    public RecipeItem(String name, String desc, double calories){
        this.title = title;
        this.description = desc;
        this.calories = calories;
    }
    public RecipeItem(String title, String desc, double calories,
                      String ingr1, String ingr2, String ingr3, String ingr4, String ingr5, String ingr6,
                      double am1, double am2, double am3, double am4, double am5, double am6
    ){
        this.title = title;
        this.description = desc;
        this.calories = calories;
        this.ingredient_1 = ingr1;
        this.ingredient_2 = ingr2;
        this.ingredient_3 = ingr3;
        this.ingredient_4 = ingr4;
        this.ingredient_5 = ingr5;
        this.ingredient_6 = ingr6;
        this.amount_1 = am1;
        this.amount_2 = am2;
        this.amount_3 = am3;
        this.amount_4 = am4;
        this.amount_5 = am5;
        this.amount_6 = am6;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public double getCalories(){
        return calories;
    }
    public String getIngredient_1(){
        return ingredient_1;
    }
    public String getIngredient_2(){
        return ingredient_2;
    }
    public String getIngredient_3(){
        return ingredient_3;
    }
    public String getIngredient_4(){
        return ingredient_4;
    }
    public String getIngredient_5(){
        return ingredient_5;
    }
    public String getIngredient_6(){
        return ingredient_1;
    }
    public double getAmount_1(){
        return amount_1;
    }
    public double getAmount_2(){
        return amount_2;
    }
    public double getAmount_3(){
        return amount_3;
    }
    public double getAmount_4(){
        return amount_4;
    }
    public double getAmount_5(){
        return amount_5;
    }
    public double getAmount_6(){
        return amount_6;
    }


}
