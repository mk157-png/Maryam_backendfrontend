package com.example.demospringboot.models;
import jakarta.persistence.*;
@Entity
@Table(name="USERS")

public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private long userId;
    @Column(name="USER_NAME",nullable=false)
    private String userName;
    @Column(name="EmaiL",nullable=false)
    private String email;
    @Column(name="PASSWORD_HASH", nullable=false)
    private String passwordHash;
    @Column(name="FIRST_NAME",nullable=false)
    private String firstName;
    @Column(name="LAST_NAME",nullable=false)
    private String lastName;
    @Column(name="AGE",nullable=false)
    private long age;
    @Column(name="STUDENT_STATUS",nullable=false)
    private boolean studentStatus;
    @Column(name="PRONOUNS",nullable=false)
    private String pronouns;
    @Column(name="RESET_TOKEN")
    private String resetToken;

    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public long getUserId(){
        return userId;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public long getAge(){
        return age;
    }
    public boolean StudentStatus(){
        return studentStatus;
    }
    public String getPronouns(){
        return pronouns;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastname){
        this.lastName=lastname;
    }
    public void setAge(long age){
        this.age=age;
    }
    public void setStudentStatus(boolean studentStatus){
        this.studentStatus=studentStatus;
    }
    public void setPronouns(String pronouns){
        this.pronouns=pronouns;
    }
    public String getResetToken(){
        return resetToken;
    }
    public void setResetToken(String resetToken){
        this.resetToken=resetToken;
    }


    
}
