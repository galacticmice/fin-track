package com.ft.fin_track.controller;

import com.ft.fin_track.database.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RecurrenceController {

    @GetMapping("/recurrences")
    public String recurrences(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Get all recurrences for the user
        List<Recurrence> recurrences = RecurrenceDAO.getRecurrencesForUser(user.getUserID());
        model.addAttribute("recurrences", recurrences);
        
        return "recurrences";
    }
    
    @PostMapping("/recurrence/delete/{recurrenceId}")
    public String deleteRecurrence(@PathVariable int recurrenceId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Verify the recurrence belongs to the user before deleting
        Recurrence recurrence = RecurrenceDAO.getRecurrenceById(recurrenceId);
        if (recurrence != null && recurrence.getUser_id() == user.getUserID()) {
            RecurrenceDAO.deleteRecurrence(recurrenceId);
        }
        
        return "redirect:/recurrences";
    }
    
    @PostMapping("/recurrence/update/{recurrenceId}")
    public String updateRecurrence(@PathVariable int recurrenceId,
                                  @RequestParam int intervalDays,
                                  HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Verify the recurrence belongs to the user before updating
        Recurrence recurrence = RecurrenceDAO.getRecurrenceById(recurrenceId);
        if (recurrence != null && recurrence.getUser_id() == user.getUserID()) {
            // Create a new recurrence object with updated interval_days
            Recurrence updatedRecurrence = new Recurrence(
                recurrence.getRecurrence_id(),
                recurrence.getUser_id(),
                recurrence.getActivity_id(),
                intervalDays,
                recurrence.getLast_change()
            );
            
            RecurrenceDAO.updateRecurrence(recurrenceId, updatedRecurrence);
        }
        
        return "redirect:/recurrences";
    }
}