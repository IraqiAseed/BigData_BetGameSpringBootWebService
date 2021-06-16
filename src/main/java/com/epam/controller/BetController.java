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


    @GetMapping("suspicious")
    public void suspiciousActivities(@RequestParam String startTime, @RequestParam String endTime) {
        suspiciousManager.validateAll(startTime, endTime);

    }

    @GetMapping("statistics/game")
    public void getStatisticsForGame(@RequestParam String game, @RequestParam String startTime, @RequestParam String endTime) {
        statisticsManager.showGameStatistics(startTime, endTime, game);
    }

    @GetMapping("statistics/games")
    public void getStatisticsForAllGames(@RequestParam String startTime, @RequestParam String endTime) {
        statisticsManager.showGameStatistics(startTime, endTime, null);
    }


}
