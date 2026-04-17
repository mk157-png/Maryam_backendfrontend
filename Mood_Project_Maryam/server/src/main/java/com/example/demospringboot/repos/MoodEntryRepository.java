package com.example.demospringboot.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demospringboot.models.MoodEntry;
public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long>{
    List<MoodEntry> findByUserId(Long userId);
    @Query(value = """
            SELECT AVG(m.MOOD_SCALE)
            FROM mood_entries me
            JOIN moods m on me.MOOD_ID= m.MOOD_ID
            WHERE me.USER_ID = :userId
            AND me.CREATED_AT >= DATEADD('DAY',-7,CURRENT_TIMESTAMP)
            """, nativeQuery = true )
    Double getWeeklyAverageMoodScale(@Param("userId") Long userId);

    // query to get predict monthly mood scale
    @Query(value= """
            SELECT AVG(m.MOOD_SCALE)
            FROM mood_entries me
            JOIN moods m on me.MOOD_ID = m.MOOD_ID
            WHERE me.USER_ID = :userId
            AND me.CREATED_AT >= DATEADD('MONTH',-1,CURRENT_TIMESTAMP)

            """,nativeQuery = true)
    Double getMonthlyAverageMoodScale(@Param("userId") Long userId);
            
    @Query(value = "SELECT m.MOOD_SCALE " +
               "FROM MOOD_ENTRIES me " +
               "JOIN MOODS m ON me.MOOD_ID = m.MOOD_ID " +
               "WHERE me.USER_ID = :userId " +
               "ORDER BY me.cREATED_AT DESC LIMIT 7", 
       nativeQuery = true)
       List<Integer> findLast7MoodScales(@Param("userId") Long userId);
}