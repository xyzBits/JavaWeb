package com.dongfang.xml.bean;

public class Student {
    private String name;
    private Integer age;
    private String id;

    public Student(String name, Integer age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"id\":\"")
                .append(id).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
