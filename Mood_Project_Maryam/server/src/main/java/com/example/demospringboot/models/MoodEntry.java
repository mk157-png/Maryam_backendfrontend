package com.example.demospringboot.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name="MOOD_ENTRIES")
public class MoodEntry {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="MOOD_ENTRY_ID")
   private Long moodEntryId;

   // Many mood entries belong to one user
  
   @JoinColumn(name="USER_ID", nullable=false)
   private Long userId;

   // Many mood entries belong to one mood
 
   @JoinColumn(name="MOOD_ID", nullable=false)
   private Long moodId;

   @Column(name="CREATED_AT", nullable=false)
   private LocalDateTime createdAt;

   @PrePersist
   public void prePersist() {
       createdAt = LocalDateTime.now();
   }

   public Long getMoodEntryId() {
       return moodEntryId;
   }

   public Long getUserId() {
       return userId;
   }

   public void setUserId(Long userId) {
       this.userId = userId;
   }

   public Long getMoodId() {
   return moodId;
   }

   public void setMoodId(Long moodId) {
       this.moodId = moodId;
   }

   public LocalDateTime getCreatedAt() {
       return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }
}