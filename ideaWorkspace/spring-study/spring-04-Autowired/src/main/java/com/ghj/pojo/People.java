package com.ghj.pojo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 86187
 */
public class People {
    @Autowired
    private Dog dog;
    @Autowired
    private Cat cat;
    @Autowired
    private Phone IPhone;

    @Override
    public String toString() {
        return "People{" +
                "dog=" + dog +
                ", cat=" + cat +
                ", phone=" + IPhone +
                '}';
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Cat getCat() {
        return cat;
    }

    public Phone getIPhone() {
        return IPhone;
    }

    public void setIPhone(Phone IPhone) {
        this.IPhone = IPhone;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

}
