package Classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by leole on 2/25/18.
 */


public class Person implements Serializable {

    //Gender 0 = male 1 = Females
    private int gender;
    private int age;
    private double height;
    private  double weight;
    private HashMap interests = new HashMap();
    private boolean allergy;
    private Goals goals;
    private String name;


    public Person(){
        //Default values for an example person
        setAllergy(true);
        setGender(0);
        setHeight(180.0); //current unit is cm, but may change
        setWeight(200.0); //current unit is pounds, but may change
        setAge(30);
        setName("John Doe");
    }

    public void setAll(boolean Allergy, int Gender, double Height, double Weight, int Age, String Name){
        setAllergy(Allergy);
        setGender(Gender);
        setHeight(Height);
        setWeight(Weight);
        setAge(Age);
        setName(Name);
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

    public int getAge() { return age; }

    public void setName(String n) {
        name = n;
    };
    public String getName(){
        return name;
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

    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return super.toString();
    }
}
