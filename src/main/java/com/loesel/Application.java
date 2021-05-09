/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loesel;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author stewi
 */
public class Application implements Serializable, Comparable<Application> {
    private int id;
    private int jobid;
    private String jobTitle;
    private Instant dateTimeSubmitted;
    private boolean active;
    private String firstName;
    private String firstNameError;
    private String lastName;
    private String lastNameError;
    private String email;
    private String emailError;
    private String phone;
    private String phoneError;
    private Attachment resumeUpload;
    private String resumeError;
    private double desiredSalary;
    private String salaryError;
    private LocalDate earliestStartDate;
    private String startDateError;

    
        public Application(){
        this.id = 0;
        this.jobid = 0;
        this.jobTitle = "";
        this.dateTimeSubmitted = Instant.now();
        this.active = true;
        this.firstName = "";
        this.firstNameError = "";
        this.lastName = "";
        this.lastNameError = "";
        this.email = "";
        this.emailError = "";
        this.phone = "";
        this.phoneError = "";
        this.resumeUpload = new Attachment();
        this.resumeError = "";
        this.desiredSalary = 0.0;
        this.salaryError = "";
        this.earliestStartDate = LocalDate.now();
        this.startDateError = "";
    }
        
    public Application(int id, int jobid, String jobTitle, Instant dateTimeSubmitted, boolean active, String firstName, String firstNameError, String lastName, String lastNameError, String email, String emailError, String phone, String phoneError, Attachment resumeUpload, String resumeError, double desiredSalary, String salaryError, LocalDate earliestStartDate, String startDateError) {
        this.id = id;
        this.jobid = jobid;
        this.jobTitle = jobTitle;
        this.dateTimeSubmitted = dateTimeSubmitted;
        this.active = active;
        this.firstName = firstName;
        this.firstNameError = firstNameError;
        this.lastName = lastName;
        this.lastNameError = lastNameError;
        this.email = email;
        this.emailError = emailError;
        this.phone = phone;
        this.phoneError = phoneError;
        this.resumeUpload = resumeUpload;
        this.resumeError = resumeError;
        this.desiredSalary = desiredSalary;
        this.salaryError = salaryError;
        this.earliestStartDate = earliestStartDate;
        this.startDateError = startDateError;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Instant getDateTimeSubmitted() {
        return dateTimeSubmitted;
    }

    public void setDateTimeSubmitted(Instant dateTimeSubmitted) {
        this.dateTimeSubmitted = dateTimeSubmitted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public Attachment getResumeUpload() {
        return resumeUpload;
    }

    public void setResumeUpload(Attachment resumeUpload) {
        this.resumeUpload = resumeUpload;
    }

    public String getResumeError() {
        return resumeError;
    }

    public void setResumeError(String resumeError) {
        this.resumeError = resumeError;
    }

    public double getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(double desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public String getSalaryError() {
        return salaryError;
    }

    public void setSalaryError(String salaryError) {
        this.salaryError = salaryError;
    }

    public LocalDate getEarliestStartDate() {
        return earliestStartDate;
    }

    public void setEarliestStartDate(LocalDate earliestStartDate) {
        this.earliestStartDate = earliestStartDate;
    }

    public String getStartDateError() {
        return startDateError;
    }

    public void setStartDateError(String startDateError) {
        this.startDateError = startDateError;
    }
    
    @Override
    public int compareTo(Application otherApplication) {
        return this.dateTimeSubmitted.compareTo(otherApplication.dateTimeSubmitted);
    }
    
    @Override
    public String toString() {
        return "Job Title = " + jobTitle + ", First Name = " + firstName + ", Last Name =" + lastName + ", Email = " + email;
    }

}
