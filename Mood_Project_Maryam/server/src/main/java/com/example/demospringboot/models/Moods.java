package com.example.demospringboot.models;

import jakarta.persistence.*;

@Entity
@Table(name="MOODS")
public class Moods {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="MOOD_ID")
   private Long moodId;

   @JoinColumn(name="MOOD_NAME", nullable=false)
   private String moodName;

   @JoinColumn(name="MOOD_SCALE", nullable=false)
   private Long moodScale;

   public String getMoodName() {
       return moodName;
   }

   public Long getMoodScale() {
       return moodScale;
   }

   public Long getMoodId() {
       return moodId;
   }

   public void setMoodId(Long moodId) {
       this.moodId = moodId;
   }
}