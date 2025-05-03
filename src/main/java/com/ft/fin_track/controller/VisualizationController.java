package com.ft.fin_track.controller;

import com.ft.fin_track.database.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VisualizationController {

    @GetMapping("/visualization")
    public String visualization(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Get data for the past 12 months
        LocalDate now = LocalDate.now();
        List<String> labels = new ArrayList<>();
        List<Double> incomeData = new ArrayList<>();
        List<Double> expenseData = new ArrayList<>();
        List<Double> balanceData = new ArrayList<>();
        
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
        
        for (int i = 11; i >= 0; i--) {
            LocalDate date = now.minusMonths(i);
            int year = date.getYear();
            int month = date.getMonthValue();
            
            // Add month label
            labels.add(date.format(monthFormatter));
            
            // Get monthly summary
            double[] summary = ActivityDAO.getMonthlySummary(user.getUserID(), year, month);
            double income = summary[0];
            double expense = summary[1];
            double balance = income - expense;
            
            incomeData.add(income);
            expenseData.add(expense);
            balanceData.add(balance);
        }
        
        model.addAttribute("labels", labels);
        model.addAttribute("incomeData", incomeData);
        model.addAttribute("expenseData", expenseData);
        model.addAttribute("balanceData", balanceData);
        
        // Get category breakdown for current month
        Map<String, Double> categoryExpenses = new HashMap<>();
        List<Activity> activities = ActivityDAO.getActivitiesForCurrentMonth(user.getUserID());
        
        for (Activity activity : activities) {
            if (!activity.getActivity_type()) { // Only expenses
                Category category = CategoryDAO.getCategory(activity.getCategory_id());
                String categoryName = category != null ? category.getDescription() : "Unknown";
                
                double amount = activity.getAmount();
                categoryExpenses.put(
                    categoryName, 
                    categoryExpenses.getOrDefault(categoryName, 0.0) + amount
                );
            }
        }
        
        model.addAttribute("categoryLabels", new ArrayList<>(categoryExpenses.keySet()));
        model.addAttribute("categoryData", new ArrayList<>(categoryExpenses.values()));
        
        return "visualization";
    }
}