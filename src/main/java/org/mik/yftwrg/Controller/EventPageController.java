package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Service.EventService;
import org.mik.yftwrg.Service.OrganizerService;
import org.mik.yftwrg.Service.ParticipantService;
import org.mik.yftwrg.Service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/events_thymeleaf")
@RequiredArgsConstructor
public class EventPageController {
   private final EventService eventService;
   private final OrganizerService organizerService;
   private final VenueService venueService;
   private final ParticipantService participantService;

    // 1. List all EventList
    @GetMapping({"", "/list"})
    public String showEventList(Model model){
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("event", new Event());
        model.addAttribute("venues", venueService.getAllVenues());
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        model.addAttribute("participants", participantService.getAllParticipants());
        return "event/list";  // templates/event/list.html
    }

    // Create the event
    @PostMapping("/list")
    public String createEvent(@Valid @ModelAttribute("event") Event event,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes
                              ) {
        if (result.hasErrors()) {
            model.addAttribute("events", eventService.getAllEvents());
            model.addAttribute("venues", venueService.getAllVenues());
            model.addAttribute("organizers", organizerService.getAllOrganizers());
            model.addAttribute("participants", participantService.getAllParticipants()); // if used
            return "event/list";
        }

        eventService.saveEvent(event);
        redirectAttributes.addFlashAttribute("success", "Event created successfully!");
        return "redirect:/events_thymeleaf";

    }


    // Create the event edit and delete
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        //model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("venues", venueService.getAllVenues());
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        model.addAttribute("participants", participantService.getAllParticipants());
        return "event/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEvent(@PathVariable Long id,
                              @Valid @ModelAttribute("event") Event event,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("venues", venueService.getAllVenues());
            model.addAttribute("organizers", organizerService.getAllOrganizers());
            model.addAttribute("participants", participantService.getAllParticipants());
            return "event/edit";
        }

        eventService.updateEvent(id, event);
        return "redirect:/events_thymeleaf";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events_thymeleaf";
    }


}
