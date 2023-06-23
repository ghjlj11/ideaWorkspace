package com.ghj.pojo;

import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author 86187
 */
@Alias("ShaBi")
public class User {
    private int id;
    private String name;
    private int keshi;
    private LocalDateTime localDateTime;

    public User() {
    }

    public User(int id, String name, int keshi, LocalDateTime localDateTime) {
        this.id = id;
        this.name = name;
        this.keshi = keshi;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keshi=" + keshi +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
