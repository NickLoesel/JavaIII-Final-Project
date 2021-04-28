/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loesel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.text.DateFormatter;


public class Job {
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private double salary;

    public Job(int id, boolean active, LocalDate dateCreated, String title, String city, String state, boolean fullTime, String department, String experience, String wageCategory, double salary) {
        this.id = id;
        this.active = active;
        this.dateCreated = dateCreated;
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = fullTime;
        this.department = department;
        this.experience = experience;
        this.wageCategory = wageCategory;
        this.salary = salary;
    }
    
    
    public Job() {
        id = 0;
        active = false;
        dateCreated = LocalDate.now();
        title = "";
        city = "";
        state = "";
        fullTime = false;
        department = "";
        experience = "";
        wageCategory = "";
        salary = 0.0;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getWageCategory() {
        return wageCategory;
    }

    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Title = " + title + ", Active = " + active + ", City =" + city + ", State = " + state + ", Department = " + department;
    }
    
    public Date newDateCreated(LocalDate dateCreated ){
        Date date = Date.from(dateCreated.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    
    @Override
    public int compareTo(Job otherJob) {
        if (this.dateCreated.compareTo(otherJob.dateCreated) > 0) {
            return this.dateCreated.compareTo(otherJob.dateCreated);
        }
        if (this.dateCreated.compareTo(otherJob.dateCreated) == 0) {
            return this.title.compareTo(otherJob.title);
        }
        else {
            return otherJob.dateCreated.compareTo(this.dateCreated);
        }
    }
    
    
    
    
}
