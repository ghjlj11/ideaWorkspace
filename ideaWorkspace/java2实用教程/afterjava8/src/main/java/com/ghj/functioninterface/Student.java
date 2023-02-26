package com.ghj.functioninterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author 86187
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int age;
    private String name;
    @Override
    public int hashCode(){return 1;}

    @Override
    public boolean equals(Object o) {
        if( o instanceof Student){
            return this.age == ((Student) o).getAge() && this.name.equals(((Student) o).getName());
        }
        return false;
    }
}
