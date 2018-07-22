package com.example.n26.n26transactions.controllers;

import com.example.n26.n26transactions.core.StatisticsCache;
import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
public class StatisticsController {
  private final StatisticsCache statisticsCache;

  @Autowired
  public StatisticsController(StatisticsCache statisticsCache) {
    this.statisticsCache = statisticsCache;
  }

  @GetMapping
  @ResponseBody
  public StatisticsSnapshot postTransaction() {
    return statisticsCache.get();
  }
}
