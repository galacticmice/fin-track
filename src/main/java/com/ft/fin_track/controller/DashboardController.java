package com.ft.fin_track.controller;

import com.ft.fin_track.database.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Get current month activities
        List<Activity> activities = ActivityDAO.getActivitiesForCurrentMonth(user.getUserID());
        model.addAttribute("activities", activities);

        // Get categories for dropdown
        List<Category> categories = CategoryDAO.getAllCategories();
        model.addAttribute("categories", categories);

        // Get current month and year
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        // Get monthly summary
        double[] summary = ActivityDAO.getMonthlySummary(user.getUserID(), currentYear, currentMonth);
        double totalIncome = summary[0];
        double totalExpense = summary[1];
        double balance = totalIncome - totalExpense;

        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("balance", balance);

        // Get monthly budget
        Double budget = HistoryDAO.getLatestBudget(user.getUserID(), Date.valueOf(now));
        if (budget == null) {
            budget = 0.0;
        }

        Double budgetRemaining = budget + totalIncome - totalExpense;
        if (budgetRemaining == null) {
            budgetRemaining = 0.0;
        }

        model.addAttribute("budget", budget);
        model.addAttribute("budgetRemaining", budgetRemaining);
        model.addAttribute("budgetPercentage", budget > 0 ? (totalExpense / budget + totalIncome) * 100 : 0);

        // For month/year filter
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("currentYear", currentYear);

        return "dashboard";
    }

    @GetMapping("/dashboard/{year}/{month}")
    public String dashboardFiltered(@PathVariable int year, 
                                   @PathVariable int month,
                                   HttpSession session, 
                                   Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Validate month and year
        if (month < 1 || month > 12 || year < 2000 || year > 2100) {
            return "redirect:/dashboard";
        }

        // Get activities for specified month
        List<Activity> activities = ActivityDAO.getActivitiesForMonth(user.getUserID(), year, month);
        model.addAttribute("activities", activities);

        // Get categories for dropdown
        List<Category> categories = CategoryDAO.getAllCategories();
        model.addAttribute("categories", categories);

        // Get monthly summary
        double[] summary = ActivityDAO.getMonthlySummary(user.getUserID(), year, month);
        double totalIncome = summary[0];
        double totalExpense = summary[1];
        double balance = totalIncome - totalExpense;

        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("balance", balance);

        // Get monthly budget
        LocalDate date = LocalDate.of(year, month, 1);
        Double budget = HistoryDAO.getLatestBudget(user.getUserID(), Date.valueOf(date));
        if (budget == null) {
            budget = 0.0;
        }

        Double budgetRemaining = budget - totalExpense;
        if (budgetRemaining == null) {
            budgetRemaining = 0.0;
        }

        model.addAttribute("budget", budget);
        model.addAttribute("budgetRemaining", budgetRemaining);
        model.addAttribute("budgetPercentage", budget > 0 ? (totalExpense / budget) * 100 : 0);

        // For month/year filter
        model.addAttribute("currentMonth", month);
        model.addAttribute("currentYear", year);

        return "dashboard";
    }

    @PostMapping("/activity/add")
    public String addActivity(@RequestParam int categoryId,
                             @RequestParam boolean activityType,
                             @RequestParam double amount,
                             @RequestParam String description,
                             @RequestParam(required = false) boolean recurring,
                             @RequestParam(required = false, defaultValue = "30") int intervalDays,
                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Create and add activity
        Activity activity = new Activity(user.getUserID(), categoryId, activityType, amount, description);
        Integer activity_id = ActivityDAO.addActivity(activity);

        // If recurring and activity added successfully, add recurrence
        if (activity_id != null && recurring) {
            Recurrence recurrence = new Recurrence(user.getUserID(), activity_id, intervalDays);
            RecurrenceDAO.addRecurrence(recurrence);
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/activity/delete/{activityId}")
    public String deleteActivity(@PathVariable int activityId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        ActivityDAO.deleteActivity(activityId);
        return "redirect:/dashboard";
    }

    @PostMapping("/budget/update")
    public String updateBudget(@RequestParam double budget, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        LocalDate now = LocalDate.now();
        Date firstDayOfMonth = Date.valueOf(now.withDayOfMonth(1));

        History history = new History(user.getUserID(), firstDayOfMonth, budget);
        HistoryDAO.insertOrUpdateHistory(history);

        return "redirect:/dashboard";
    }
}
