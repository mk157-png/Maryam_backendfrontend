
package com.example.demospringboot.models;
import jakarta.persistence.*;
@Entity
@Table(name="PASSWORD_RESETS")
public class password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESET_ID")
    private long resetId;
    @Column(name="USER_ID",nullable=false)
    private long userId;
    @Column(name="TOKEN",nullable=false)
    private String token;
    @Column(name="EXPIRES_AT",nullable=false)
    private java.sql.Timestamp expiresAt;
    @Column(name="USED",nullable=false)
    private boolean used;
    public long getResetId(){
        return resetId;
    }
    public long getUserId(){
      return userId;
    }
    public String getToken(){
        return token;
    }
    public java.sql.Timestamp getExpiresAt(){
        return expiresAt;
    }
    public boolean isUsed(){
        return used;
    }

    public void setResetId(long resetId){
        this.resetId=resetId;
    }
    public void setUserId(long userId){
        this.userId=userId;
    }
    public void setToken(String token){
        this.token=token;
    }
    public void setExpiresAt(java.sql.Timestamp expiresAt){
        this.expiresAt=expiresAt;
    }
    public void setUsed(boolean used){
        this.used=used;
    }
    

}
