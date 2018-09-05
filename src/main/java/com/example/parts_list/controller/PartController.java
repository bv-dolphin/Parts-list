package com.example.parts_list.controller;

import com.example.parts_list.entity.Part;
import com.example.parts_list.repository.PartRepository;
import com.example.parts_list.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parts_list")
public class PartController {
    private final PartService PART_SERVICE;
    private final PartRepository PART_REPOSITORY;

    @Autowired
    public PartController(PartService partService, PartRepository partRepository) {
        this.PART_SERVICE = partService;
        this.PART_REPOSITORY = partRepository;
    }

    @GetMapping(path = "")
    public String partsList(
            @RequestParam(required = false, defaultValue = "1") int page,
            Model model
    ){
        int pageNumber = page > 0 ? page - 1 : 0;
        List<Part> parts = PART_REPOSITORY
                .findAll(PageRequest.of(pageNumber, 10))
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));
        model.addAttribute("partsList", parts);
        return "parts_list";
    }

    @GetMapping(path = "/add_part")
    public String addPart (){
        return "add_part";
    }

    @PostMapping(path = "/add_part")
    public String addPartComplete(
            @Valid Part part,
            @RequestParam @NotBlank(message = "Select the field value.") String need,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("part", part);
            return "add_part";
        }
        if(!PART_SERVICE.create(part, need)){
            model.addAttribute("partIsAdded", "A part with this name has already been added.");
            return "add_part";
        }
        return "redirect:/parts_list";
    }


    @PostMapping(path = "/delete/{id}")
    public String deletePart(@PathVariable Long id){
        Part part = PART_REPOSITORY.getOne(id).get();
        PART_REPOSITORY.delete(part);
        return "redirect:/parts_list";
    }
    @GetMapping(path = "/edition/{id}")
    public String editionPart(
            @PathVariable Long id,
                              Model model
    ) {
        Part part = PART_REPOSITORY.getOne(id).get();
        try{
            model.addAttribute("partName", part.getName());
            model.addAttribute("countParts", part.getQuantityInStock());
            model.addAttribute("needed", part.isNeed() ? "Да" : "Нет");
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("part", part);
        return "edition";
    }

    @PostMapping(path = "/edition/{id}")
    public String editionComplete(
            @RequestParam String name,
            @RequestParam int quantityInStock,
            @RequestParam String need,   //Под вопросом
            @PathVariable Long id,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "/parts_list/edition/{id}";
        }
        if(!PART_SERVICE.update(id, name, quantityInStock, need)) {
            return "/parts_list/edition/{id}";
        }
        return "redirect:/parts_list";
    }
}
