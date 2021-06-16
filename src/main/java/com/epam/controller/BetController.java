package com.epam.controller;

import com.epam.model.BasicStatistics;
import com.epam.model.UsersScala;
import com.epam.services.implementation.initialize.UserService;
import com.epam.services.implementation.manager.StatisticsManager;
import com.epam.services.implementation.manager.SuspiciousManager;
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
    public String suspiciousActivities(@RequestParam String startTime, @RequestParam String endTime) {
        java.util.List<java.util.List<UsersScala>> users =  suspiciousManager.validateAll(startTime, endTime);

        return users.toString();
    }

    @GetMapping("statistics/game")
    public String getStatisticsForGame(@RequestParam String game, @RequestParam String startTime, @RequestParam String endTime) {
        java.util.List<java.util.List<BasicStatistics>> statistics = statisticsManager.showGameStatistics(startTime, endTime, game);
        return statistics.toString();
    }

   @GetMapping("statistics/games")
    public String getStatisticsForAllGames(@RequestParam String startTime, @RequestParam String endTime) {
       java.util.List<java.util.List<BasicStatistics>> statistics = statisticsManager.showGameStatistics(startTime, endTime, null);
       return statistics.toString();
    }


}
