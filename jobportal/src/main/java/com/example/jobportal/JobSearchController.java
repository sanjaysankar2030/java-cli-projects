package com.example.jobportal;
import com.example.jobportal.model.Job;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
;

@RestController
public class JobSearchController {

    public ArrayList<Job> joblist;

    @PostConstruct
    public void init(){
        Job job1 = new Job("1", "Software Engineer", "Develop software applications", LocalDate.now());
        Job job2 = new Job("2", "Data Scientist", "Analyze data and develop models", LocalDate.now());
        joblist = new ArrayList<Job>();
        joblist.add(job1);
        joblist.add(job2);
    }

    @GetMapping("/jobs")
    public ArrayList<Job> listJobs(){
        for(Job job:joblist){
            job.setLastAccessDate(LocalDateTime.now());
            job.addNumViews();
        }
        return joblist;
    }

    @RequestMapping("/jobs/search/{query}")
    public ArrayList<Job> searchJobs(@RequestParam String query) {
        ArrayList<Job> matchingJobs = new ArrayList<>();
        for (Job job : joblist) {
            if (job.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    job.getDescription().toLowerCase().contains(query.toLowerCase())) {
                matchingJobs.add(job);
            }
        }
            return matchingJobs;

    }
    public

}