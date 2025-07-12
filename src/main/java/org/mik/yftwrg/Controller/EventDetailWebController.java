package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.EventDetail;
import org.mik.yftwrg.Service.EventDetailService;
import org.mik.yftwrg.Service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eventDetail") //this directory is eventDetail
@RequiredArgsConstructor
public class EventDetailWebController {

    private final EventDetailService eventDetailService;
    private final EventService eventService;

//    @GetMapping({"", "/list"})
//    public String showListAndForm(Model model){
//        model.addAttribute("eventDetails", eventDetailService.getAllEventDetails());
//        model.addAttribute("eventDetail", new EventDetail());
//        model.addAttribute("events", eventService.getAllEvents());
//        return "eventDetail/list"; // goes from eventDetail to list
//    }

    @GetMapping({"", "/list"})
    public String showListAndForm(Model model){
        model.addAttribute("eventDetails", eventDetailService.getAllEventDetails());

        EventDetail eventDetail = new EventDetail();
        //eventDetail.setEvent(null); // optional, default is null
        eventDetail.setEvent(null); // optional, default is null
        model.addAttribute("eventDetail", eventDetail);
        model.addAttribute("events", eventService.getAllEvents());
        return "eventDetail/list";
    }


    @PostMapping("/list")
    public String saveEventDetail(@ModelAttribute EventDetail eventDetail) {
        // Get the real Event entity from DB if ID is provided
        if (eventDetail.getEvent() != null && eventDetail.getEvent().getId() != null) {
            Event event = eventService.getEventById(eventDetail.getEvent().getId());
            eventDetail.setEvent(event); // now Hibernate sees it as a managed entity
        } else {
            eventDetail.setEvent(null); // allow null for optional selection
        }

        eventDetailService.saveEventDetail(eventDetail);
        return "redirect:/eventDetail";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EventDetail eventDetail = eventDetailService.getEventDetailById(id);
        model.addAttribute("eventDetail", eventDetail);
        model.addAttribute("events", eventService.getAllEvents());
        return "eventDetail/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEventDetail(@PathVariable Long id,
                                    @Valid @ModelAttribute("eventDetail") EventDetail eventDetail,
                                    BindingResult result){
        if (result.hasErrors()) {
            return "eventDetail/edit";
        }
        eventDetailService.updateEventDetail(id, eventDetail);
        return "redirect:/eventDetail";
    }

    @PostMapping("/delete/{id}")
    public String deleteEventDetail(@PathVariable Long id) {
        eventDetailService.deleteEventDetail(id);
        return "redirect:/eventDetail";
    }

}
