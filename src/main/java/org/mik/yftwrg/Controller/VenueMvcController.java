package org.mik.yftwrg.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Venue;
import org.mik.yftwrg.Service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/venues_thymeleaf")  // MVC UI endpoints, e.g. /venues, /venues/create
@RequiredArgsConstructor
public class VenueMvcController {
    private final VenueService venueService;

    // Show list of venues (HTML page)
    @GetMapping({"", "/list"})
    public String listVenues(Model model) {
        model.addAttribute("venues", venueService.getAllVenues());
        model.addAttribute("venue", new Venue());
        return "Venue/list"; // Thymeleaf template at templates/venue/list.html
    }

//    // Show form to create new venue
//    @GetMapping("/list")
//    public String showCreateForm(Model model) {
//        model.addAttribute("venues_thymeleaf", new Venue());  // Or use VenueDTO if preferred
//        return "Venue/list";  // Create form template
//    }

    // Handle form submission to create venue
    @PostMapping("/list")
    public String createVenue(@Valid @ModelAttribute("venue") Venue venue, BindingResult result) {
        if (result.hasErrors()) {
            return "Venue/list"; // Show form again with errors
        }
        venueService.saveVenue(venue);
        return "redirect:/venues_thymeleaf";  // Redirect to list page after save
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Venue venue = venueService.getVenueById(id);
        model.addAttribute("venue", venue);
        return "Venue/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateVenue(@PathVariable Long id,
                              @Valid @ModelAttribute("venue") Venue venue,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "Venue/edit";
        }
        venueService.updateVenue(id, venue);
        return "redirect:/venues_thymeleaf";
    }

    @PostMapping("/delete/{id}")
    public String deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return "redirect:/venues_thymeleaf";
    }


}
