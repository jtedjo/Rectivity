package Classes;

import java.util.HashMap;

/**
 * Created by leole on 2/25/18.
 */


public class Person {

    private int gender;
    private double height;
    private  double weight;
    private HashMap interests;
    private boolean allergy;
    private Goals goals;


    public Person(){


    }

    public boolean isAllergy() {
        return allergy;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public Goals getGoals() {
        return goals;
    }

    public HashMap getInterests() {
        return interests;
    }

    public int getGender() {
        return gender;
    }

    public void setAllergy(boolean allergy) {
        this.allergy = allergy;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setGoals(Goals goals) {
        this.goals = goals;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setInterests(HashMap interests) {
        this.interests = interests;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
