package com.ghj.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @author 86187
 */
@Alias("ShaBi")
public class User {
    private int id;
    private String name;
    private int keshi;
    public User(){
    }
    public User(int id, String name, int keshi ){
        this.id = id;
        this.name = name;
        this.keshi = keshi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setKeshi(int keshi){
        this.keshi = keshi;
    }

    public int getKeshi() {
        return keshi;
    }

    @Override
    public String toString(){
        return "User{" + "id=" + id +
                ", name=" + name +
                ", keshi=" + keshi +
                "}";
    }
}
