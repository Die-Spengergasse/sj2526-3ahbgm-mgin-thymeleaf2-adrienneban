package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Medication;
import at.spengergasse.spring_thymeleaf.entities.MedicationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medication")
public class MedicationController {
    private final MedicationRepository medicationRepository;
    public MedicationController(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @GetMapping("/medications")
    public String medications(Model model) {
        model.addAttribute("medications", medicationRepository.findAll());
        return "medlist";
    }

    @GetMapping("/add")
    public String addMedications(Model model) {
        model.addAttribute("medication", new Medication());
        return "add_medication";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute("medication") Medication medication) {
        medicationRepository.save(medication);
        return  "redirect:/medication/medications";
    }

}
