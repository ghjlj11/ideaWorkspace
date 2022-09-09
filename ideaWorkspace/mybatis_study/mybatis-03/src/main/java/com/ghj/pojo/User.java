package com.ghj.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @author 86187
 */
@Alias("ShaBi")
public class User {
    private int id;
    private String name;
    private int time;

    public User(int id, String name, int time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
