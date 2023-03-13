package com.fitness.FitnessApp.Controller;

import com.fitness.FitnessApp.Model.Exercise;
import com.fitness.FitnessApp.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/")
    public Exercise[] getExercise(@RequestParam(defaultValue = "") String exName,
                                @RequestParam(defaultValue = "") String exType,
                                @RequestParam(defaultValue = "") String muscle,
                                @RequestParam(defaultValue = "") String difficulty,
                                Model model){
                Exercise[] exercises = exerciseService.getExercises(exName, exType, muscle, difficulty);
                model.addAttribute("exercises", exercises);

                return exercises;
    }

//    @GetMapping("/user2")
//    public Object ggf(OAuth2AuthenticationToken oAuth2AuthenticationToken){
//        if(oAuth2AuthenticationToken != null && oAuth2AuthenticationToken.isAuthenticated())
//
//        return oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();
//        else{
//            return "login";
//        }
//    }
}