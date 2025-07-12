package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Participant;
import org.mik.yftwrg.Service.EventService;
import org.mik.yftwrg.Service.ParticipantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/participant_thymeleaf")
@RequiredArgsConstructor
public class participantMVcController {

    private final ParticipantService participantService;
    private  final EventService eventService;

    //1 List all participants
    @GetMapping({"", "/list"})
    public String ListParticipants(Model model){
        model.addAttribute("participants", participantService.getAllParticipants());
        model.addAttribute("participant", new Participant());
        //model.addAttribute("events", eventService.getAllEvents() );
        return "Participant/list";  // templates/participant/list.html
    }


    // 3. Handle create POST
    @PostMapping("/list")
    public String createParticipant(@Valid @ModelAttribute("participant") Participant participant,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Participant/list";
        }

        participantService.saveParticpant(participant);
        redirectAttributes.addFlashAttribute("success", "Organizer created successfully!");
        return "redirect:/participant_thymeleaf";
    }

    // 4. Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Participant participant = participantService.getParticipantById(id);
        model.addAttribute("participant", participant);
        return "Participant/edit";  // templates/participant/editParticipant.html
    }

    // 5. Handle edit POST
    @PostMapping("/edit/{id}")
    public String updateParticipant(@PathVariable Long id,
                                    @Valid @ModelAttribute("participant") Participant updatedParticipant,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            return "Participant/edit";
        }

        participantService.updateParticipant(id, updatedParticipant);
        return "redirect:/participant_thymeleaf";
    }

    // 6. Delete participant
    @PostMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return "redirect:/participant_thymeleaf";
    }
}
