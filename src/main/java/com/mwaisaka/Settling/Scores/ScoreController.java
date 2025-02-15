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

    @PatchMapping("/score/{wlt2}")
    public Score updateScore(@PathVariable String wlt2 ,
                             @RequestParam(name = "new-value", required = false, defaultValue = "0")int newValue){

        if (wlt2.equalsIgnoreCase("wins")){
            score.setWins(newValue);
            return score;
        }
        else if (wlt2.equalsIgnoreCase("losses")){
            score.setLosses(newValue);
            return score;
        }
        else if (wlt2.equalsIgnoreCase("ties")) {
            score.setTies(newValue);
            return score;
        }
        else {
            throw new IllegalArgumentException("invalid parameter: " + wlt2);
        }
    }

    @PutMapping("/score")
    public Score replaceScore(@RequestBody Score newScore){
        score = newScore;
        return score;
    }

    @DeleteMapping("/score") //removes the resource from the server
    public String deleteScore(){
        score.setWins(0);
        score.setTies(0);
        score.setLosses(0);
        return "Score reset to zero.";
    }
}

//http://localhost:8080/score/ties