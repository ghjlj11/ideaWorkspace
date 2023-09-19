package com.ghj.before.streamtest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 86187
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEqualsEntity {
    private int age;
    private String name;
    @Override
    public boolean equals(Object entity){
        if(entity instanceof TestEqualsEntity){
            if(this.age == ((TestEqualsEntity) entity).age && this.name.equals(((TestEqualsEntity) entity).name)){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        return 1;
    }
}
