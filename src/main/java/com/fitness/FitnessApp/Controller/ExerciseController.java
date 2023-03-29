package com.fitness.FitnessApp.Controller;

import com.fitness.FitnessApp.Model.Exercise;
import com.fitness.FitnessApp.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/search")
    public String getExercise(@RequestParam(name = "exName", defaultValue = "") String exName,
                              @RequestParam(name = "exType", defaultValue = "") String exType,
                              @RequestParam(name = "muscle", defaultValue = "") String muscle,
                              @RequestParam(name = "difficulty", defaultValue = "") String difficulty,
                              @NonNull Model model) {

        Exercise[] exercises = exerciseService.getExercises(exName, exType, muscle, difficulty);
        model.addAttribute("exercises", exercises);

        return "search";
    }

    @GetMapping("/search")
    public String showHtml() {
        return "search";
    }

    @GetMapping("/callback")
    public String getEmail(OAuth2AuthenticationToken oAuth2AuthenticationToken) {

        String email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();

        String userName = email.split("@")[0];
        return "redirect:/user/" + userName;
    }

    @GetMapping("/user/{userName}")
    @ResponseBody
    public String userSavedItems(@PathVariable("userName") String userName, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String authUserName = authentication.getName().split("@")[0];

            if (userName.equals(authUserName)) {
                return "saved-items"+userName;
            } else {

                return "error";
            }
        } else {
            return "redirect:/login";
        }
    }

}
