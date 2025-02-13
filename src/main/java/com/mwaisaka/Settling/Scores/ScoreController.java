package com.mwaisaka.Settling.Scores;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ScoreController {

    static Score score = new Score(30,20,10);

    @GetMapping("/health-check")
    public String getHealthCheck(){
        return "Situation Normal All Fired Up!";
    }

    @GetMapping("/score")
    public Score getScore(){
        return score;
    }

    @GetMapping("/score/{wlt}")
    public int wlt(@PathVariable String wlt){
        if (wlt.equalsIgnoreCase("wins"))
            return score.getWins();
        else if (wlt.equalsIgnoreCase("losses"))
            return score.getLosses();
        else
            return score.getTies();

    }

    @PostMapping("/score/{wlt1}")
    public Score wlt1(@PathVariable String wlt1){

        if (wlt1.equalsIgnoreCase("wins")) {
            score.setWins(score.getWins() + 1);
            return score;
        }
        else if (wlt1.equalsIgnoreCase("losses")) {
            score.setLosses(score.getLosses() + 1);
            return score;
        }
        else if (wlt1.equalsIgnoreCase("ties")) {
            score.setTies(score.getTies() + 1);
            return score;
        }
        else {
            System.out.println("Invalid operation!");
            return score;
        }
    }
}

//http://localhost:8080/score/ties