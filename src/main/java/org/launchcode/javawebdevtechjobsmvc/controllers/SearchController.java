package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    public String title;
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("searchType", "all");
        model.addAttribute("columns", columnChoices);
        return "search";
    }
    @RequestMapping(value = "results")
    public String search(@RequestParam String searchType, @RequestParam String searchTerm,String title, Model model) {
         title="Jobs With "+searchType+" : " +searchTerm;
        ArrayList<Job> jobs;
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title",title);
            model.addAttribute("selectedColumn", searchType);
        }
        model.addAttribute("searchType",searchType);
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("title",title);

        return "search";
    }



}
