package com.ss3.model.controller;

import com.ss3.model.dto.CountryDTO;
import com.ss3.model.entity.Continental;
import com.ss3.model.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryWebController {

    private final CountryService countryService;

    @GetMapping
    public String listCountries(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<CountryDTO> countries = keyword == null || keyword.isBlank()
                ? countryService.findAll()
                : countryService.findByNameContainingIgnoreCase(keyword);
        model.addAttribute("countries", countries);
        model.addAttribute("keyword", keyword);
        return "country/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("country", new CountryDTO());
        model.addAttribute("continentals", Continental.values());
        return "country/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("country") CountryDTO dto) {
        countryService.save(dto);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryService.findById(id));
        model.addAttribute("continentals", Continental.values());
        return "country/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        countryService.deleteById(id);
        return "redirect:/countries";
    }
}
