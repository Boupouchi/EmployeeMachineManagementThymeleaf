package com.example.gestionemployemachineth.controllers;

import com.example.gestionemployemachineth.entities.Machine;
import com.example.gestionemployemachineth.repositories.MachineRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MachineController {

        @Autowired
        private MachineRepositry machineRepository;
        @GetMapping("/machine")
        public String showSignUpForm(Machine machine) {
                return "add-machine";
        }

        @PostMapping("/addmachine")
        public String addMachine(Machine machine, BindingResult result, Model model) {
                if (result.hasErrors()) {
                        return "add-machine";
                }
                machineRepository.save(machine);
                model.addAttribute("machines", machineRepository.findAll());
                return "indexM";
        }


        @GetMapping("/edit/machine/{id}")
        public String showUpdateForm(@PathVariable("id") Long id, Model model) {
                Machine machine = machineRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid machine Id:" + id));
                model.addAttribute("machine", machine);
                return "update-machine";
        }

        @PostMapping("/update/machine/{id}")
        public String updateMachine(@PathVariable("id") Long id, Machine machine, BindingResult result, Model model) {
                if (result.hasErrors()) {
                        machine.setId(id);
                        return "update-machine";
                }

                machineRepository.save(machine);
                model.addAttribute("machines", machineRepository.findAll());
                return "indexM";
        }

        @GetMapping("/delete/machine/{id}")
        public String deleteMachine(@PathVariable("id") Long id, Model model) {
                Machine machine = machineRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Machine Id:" + id));
                machineRepository.delete(machine);
                model.addAttribute("machines", machineRepository.findAll());
                return "indexM";
        }
}