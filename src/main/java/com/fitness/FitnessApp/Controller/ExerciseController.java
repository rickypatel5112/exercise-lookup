package com.fitness.FitnessApp.Controller;

import com.fitness.FitnessApp.Model.Exercise;
import com.fitness.FitnessApp.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @PostMapping("/search")
    public String getExercise(@RequestParam(name = "exName", defaultValue = "") String exName,
                              @RequestParam(name = "exType", defaultValue = "") String exType,
                              @RequestParam(name = "muscle", defaultValue = "") String muscle,
                              @RequestParam(name = "difficulty", defaultValue = "") String difficulty,
                              @NonNull Model model){

                Exercise[] exercises = exerciseService.getExercises(exName, exType, muscle, difficulty);
                model.addAttribute("exercises", exercises);

                return "search";
    }

    @GetMapping("/search")
    public String showHtml(){
        return "search";
    }

//    @GetMapping("/{userName}")
//    public String ggf(OAuth2AuthenticationToken oAuth2AuthenticationToken){
//
//        String email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();
//
//        String userName = email.split("@")[0];
//
//        System.out.println(userName);
//
//        return "dashboard";
//    }
}