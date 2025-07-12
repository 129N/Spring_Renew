package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Organizer;
import org.mik.yftwrg.Service.OrganizerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/organizer_thymeleaf")
@RequiredArgsConstructor
public class OrganizerMvcController {
    private final OrganizerService organizerService;

    @GetMapping({"", "/list"})
    public String listOrganizers(Model model, @ModelAttribute(value = "success", binding = false) String success){
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        model.addAttribute("organizer", new Organizer());

        if (success != null && !success.isEmpty()) {
            model.addAttribute("success", success);
        }
        return "Organizer/list";  // templates/organizer/list.html
    }

//    @GetMapping("/list")
//    public String showCreateForm(Model model) {
//        model.addAttribute("organizer", new Organizer());
//        return "Organizer/list";  // templates/organizer/edit.html
//    }

    // Handle create form submission
    @PostMapping("/list")
    public String createOrganizer(@Valid @ModelAttribute("organizer") Organizer organizer,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Organizer/list";
        }

        organizerService.saveOrganizer(organizer);
        redirectAttributes.addFlashAttribute("success", "Organizer created successfully!");
        return "redirect:/organizer_thymeleaf";
    }


//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Organizer organizer = organizerService.getOrganizerById(id);
//        if (organizer == null) {
//            throw new RuntimeException("Organizer not found");
//        }
//        model.addAttribute("organizer", organizer);
//        return "Organizer/edit";  // This renders edit.html inside templates/Organizer/
//    }

    @GetMapping("/organizer/list")
    public String showOrganizers(Model model) {
        List<Organizer> organizers = organizerService.getAllOrganizers(); // make sure fetch type is eager or events are loaded
        model.addAttribute("organizers", organizers);
        return "Organizer/list";
    }



    @PostMapping("/edit/{id}")
    public String updateOrganizer(@PathVariable Long id, @Valid @ModelAttribute("organizer") Organizer organizer, BindingResult result) {
        if (result.hasErrors()) {
            return "Organizer/list";
        }
        organizerService.updateOrganizer(id, organizer);
        return "redirect:/organizer_thymeleaf";
    }


    @PostMapping("/delete/{id}")
    public String deleteVenue(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return "redirect:/organizer_thymeleaf";
    }
}
