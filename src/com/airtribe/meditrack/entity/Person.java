package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.IdGenerator;

public class Person {
    private String id;
    private String name;
    private String age;
    private String Gender;
    private String contactNumber;

    public Person() {
    }

    public Person(String prefix, String name, String age, String contactNumber, String gender) {
        this.id = IdGenerator.generateId(prefix);
        this.name = name;
        this.age = age;
        this.Gender = gender;
        this.contactNumber = contactNumber;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    @Override
    public String toString() {
        return id + ", " + name + ", " + age+" , " + Gender + ", " + contactNumber;
    }

}
