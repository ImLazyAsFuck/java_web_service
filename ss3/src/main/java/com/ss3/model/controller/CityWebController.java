package com.ss3.model.controller;

import com.ss3.model.dto.CityDTO;
import com.ss3.model.entity.Season;
import com.ss3.model.service.CityService;
import com.ss3.model.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityWebController {

    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping
    public String listCities(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<CityDTO> cities = cityService.findByNameContainingIgnoreCase(keyword);
        model.addAttribute("cities", cities);
        model.addAttribute("keyword", keyword);
        return "city/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("city", new CityDTO());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("seasons", Season.values());
        return "city/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("city") CityDTO dto) {
        cityService.save(dto);
        return "redirect:/cities";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("city", cityService.findById(id));
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("seasons", Season.values());
        return "city/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cityService.deleteById(id);
        return "redirect:/cities";
    }
}