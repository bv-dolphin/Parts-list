package com.example.parts_list.controller;

import com.example.parts_list.entity.Part;
import com.example.parts_list.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/parts_list")
public class PartController {
    private final PartService PART_SERVICE;
    public PartController(PartService partService) {
        this.PART_SERVICE = partService;
    }

    @GetMapping(path = "")
    public String partsList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "Все компоненты") String sorted,
            @RequestParam(required = false, defaultValue = "") String search,
            Model model
    ){
        List<Part> parts = PART_SERVICE.getPartList(sorted, search, page);

        model.addAttribute("partsList", parts);
        model.addAttribute("search", search);
        model.addAttribute("sorted", sorted);
        model.addAttribute("page", page);

        int computerCompleteCount = PART_SERVICE.computerCompleteCount();
        model.addAttribute("computerCompleteCount", computerCompleteCount);
        return "parts_list";
    }

    @GetMapping(path = "/add_part")
    public String addPart (){
        return "add_part";
    }

    @PostMapping(path = "/add_part")
    public String addPartComplete(
            @Valid  Part part,
            @RequestParam String needed,
            BindingResult bindingResult,
            Model model
            )  {

        boolean isEmptyNeeded = StringUtils.isEmpty(needed);
        if (isEmptyNeeded){
            model.addAttribute("neededError", "Please take 'Да' or 'Нет'.");
        }
        if(isEmptyNeeded || bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "add_part";
        }
            if (!PART_SERVICE.create(part, needed, model )) {
            return "add_part";
        }
        return "redirect:/parts_list";
    }


    @PostMapping(path = "/delete/{id}")
    public String deletePart(@PathVariable Long id){
        PART_SERVICE.delete(id);
        return "redirect:/parts_list";
    }

    @GetMapping(path = "/edition/{id}")
    public String editionPart(
            @PathVariable Long id,
                              Model model
    ) {
        Optional<Part> partOptional = PART_SERVICE.getPart(id);
        if(partOptional.isPresent()) {
            Part part = partOptional.get();
            model.addAttribute("partName", part.getName());
            model.addAttribute("countParts", part.getQuantityInStock());
            model.addAttribute("needed", part.isNeed() ? "Да" : "Нет");
            model.addAttribute("part", part);
        }
        return "edition";
    }



    @PostMapping(path = "/edition/{id}")
    public String editionComplete(
            @RequestParam String name,
            @RequestParam int  quantityInStock,
            @RequestParam String needed,
            @PathVariable Long id,
            Model model
    ){
        if(!PART_SERVICE.update(id, name, quantityInStock, needed, model)) {
            model.addAttribute("partIsAdded",  "Part with this name is already added!");
            return "edition";
        }
        return "redirect:/parts_list";
    }
}
