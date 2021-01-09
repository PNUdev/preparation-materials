package com.ihorpolataiko.springbootdemoforpnudev.controller;

import com.ihorpolataiko.springbootdemoforpnudev.dto.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("data", "some-data");
        return "index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("attr", "Some attribute");
        return "form";
    }

    @PostMapping
    public String submit(@Valid FormData formData, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // call service and execute some modifying business logic

        List<ObjectError> errors = bindingResult.getAllErrors();

        if (!errors.isEmpty()) {

            String errorMessage = errors.stream()
                    .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining("; "));

            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

            return "redirect:/form";
        }

        redirectAttributes.addFlashAttribute("name", formData.getFirstName());
        redirectAttributes.addFlashAttribute("surname", formData.getSecondName());

        return "redirect:/form";
    }

    @PostMapping("/second")
    public String submitSecond(@ModelAttribute("firstName") String firstName, RedirectAttributes redirectAttributes) {

        // call service and execute some modifying business logic

        redirectAttributes.addFlashAttribute("name", firstName);

        return "redirect:/form";
    }

    @PostMapping("/third")
    public String submitThird(@RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {

        // call service and execute some modifying business logic

        if (params.containsKey("firstName")) {
            redirectAttributes.addFlashAttribute("name", params.get("firstName"));
        }

        return "redirect:/form";
    }

}
