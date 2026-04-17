package com.example.demospringboot.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.demospringboot.repos.MoodEntryRepository;

@RestController
@RequestMapping("/api/mood-analysis")
@CrossOrigin(origins = "*") // This will fix localhost cors errors @maryam
public class MoodAnalysisController {
    private final MoodEntryRepository moodEntryRepository;
    
    public MoodAnalysisController(MoodEntryRepository moodEntryRepository) {
        this.moodEntryRepository = moodEntryRepository;
    }

    
    @GetMapping("/user/{userId}/weekly-average")
    public String getWeeklyAverageMoodScale(@PathVariable Long userId) {
       Double avg = moodEntryRepository.getWeeklyAverageMoodScale(userId);
       if(avg == null) {
        return "Mood of user " + userId + " No data";
       }
       return "Mood of User " + userId + " :" + String.format("%.2f", avg);
    }
    @GetMapping("/user/{userId}/monthly-average")
    public String getMonthlyAverageMoodScale(@PathVariable Long userId) {
        Double avg = moodEntryRepository.getMonthlyAverageMoodScale(userId);
        if(avg == null) {
            return "Mood of user with id " + userId + " is not recorded in the last 30 days";
        }
        return "Mood of User " + userId + " :" + String.format("%.2f", avg);
    }

    @GetMapping("/user/{userId}/daily-scales")
    public List<Integer> getDailyScales(@PathVariable Long userId) {
    List<Integer> scales = moodEntryRepository.findLast7MoodScales(userId);
    return scales;
    }


}
