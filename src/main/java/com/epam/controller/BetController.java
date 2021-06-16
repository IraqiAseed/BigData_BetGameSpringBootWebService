package com.epam.controller;

import com.epam.services.StatisticsManager;
import com.epam.services.SuspiciousManager;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bets/")
public class BetController {

    @Autowired
    private UserService userService;


    @Autowired
    private SuspiciousManager suspiciousManager;

    @Autowired
    private StatisticsManager statisticsManager;


    //http://localhost:8080/bets/suspiciousAgg?startTime=2014-01-01%2009:55:14&endTime=2014-01-30%2010:55:14
    @GetMapping("suspicious")
    public void suspiciousActivities(@RequestParam String startTime, @RequestParam String endTime) {
        suspiciousManager.validateAll(startTime, endTime);

    }

    //http://localhost:8080/bets/statistics/game?game=whist&startTime=2014-01-01%2009:55:14%20&endTime=2014-12-31%2010:55:14
    @GetMapping("statistics/game")
    public void getStatisticsForGame(@RequestParam String game, @RequestParam String startTime, @RequestParam String endTime) {
        statisticsManager.showGameStatistics(startTime, endTime, game);
    }

    @GetMapping("statistics/games")
    public void getStatisticsForAllGames(@RequestParam String startTime, @RequestParam String endTime) {
        statisticsManager.showGameStatistics(startTime, endTime, null);
    }


}
