package com.fitness.FitnessApp.Controller;

import com.fitness.FitnessApp.Model.Exercise;
import com.fitness.FitnessApp.Model.User;
import com.fitness.FitnessApp.Repositories.ExerciseRepository;
import com.fitness.FitnessApp.Repositories.UserRepository;
import com.fitness.FitnessApp.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

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

    @PostMapping("/save")
    public String saveExercise(@RequestParam(name = "userName") String userName,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "type") String type,
                               @RequestParam(name = "muscle") String muscle,
                               @RequestParam(name = "difficulty") String difficulty,
                               @RequestParam(name = "equipment") String equipment,
                               @RequestParam(name = "instructions") String instructions) {

        Exercise exercise = new Exercise(name, type, muscle, difficulty, equipment, instructions);

        User user = userRepository.findById(userName).get();
        user.getExercises().add(exercise);

        exerciseRepository.save(exercise);

        String message = "Exercise '" + exercise.getName() + "' saved successfully for user '" + userName + "'";
        return "search";
    }

    @GetMapping("/search")
    public String showHtml() {
        return "search";
    }

    @GetMapping("/callback")
    public String getUserName(OAuth2AuthenticationToken oAuth2AuthenticationToken) {

        String email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();

        String userName = email.split("@")[0];
        return "redirect:/user/" + userName;
    }

    @GetMapping("/user/{userName}")
    public String userSavedItems(@PathVariable("userName") String userName, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {


            String authUserName = authentication.getName().split("@")[0];

            if (!userRepository.existsById(authUserName)) {
                userRepository.save(new User(authUserName));
            }

            User user = userRepository.findById(authUserName).get();

            List<Exercise> exerciseList = user.getExercises();

            System.out.println("DEBUGGING");
            for (Exercise exercise : exerciseList) {
                System.out.println("THE EXERCISES: " + exercise.toString());
            }

            model.addAttribute("exerciseList", exerciseList);
            return "dashboard";
        } else {
            return "redirect:/login";
        }
    }
}
