package com.example.parts_list.service;

import com.example.parts_list.entity.Part;
import com.example.parts_list.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PartService implements BaseService {
    private final PartRepository PART_REPOSITORY;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.PART_REPOSITORY = partRepository;
    }

    @Override
    public boolean update(Long id, String name, int quantityInStock, String needed, Model model) {
        Optional <Part> part = PART_REPOSITORY.getOne(id);
        Optional <Part> partName = PART_REPOSITORY.findByName(name);
        if (partName.isPresent() && !partName.equals(part)) {
            model.addAttribute("part", part.get());
            return false;
        }
        try {
            if (quantityInStock >= 0)
                part.get().setQuantityInStock(quantityInStock);
        } catch (IllegalArgumentException e){
            model.addAttribute("part", part.get());
            model.addAttribute("quantityInStockError", "Number format except!");
            return false;
        }
        if (!StringUtils.isEmpty(name)) part.get().setName(name);
        if (!StringUtils.isEmpty(needed)) part.get().setNeed(needed.equals("Да"));
        PART_REPOSITORY.save(part.get());
        return true;
    }

    @Override
    public boolean create(Part part, String need, Model model) {
        boolean partFromDb = PART_REPOSITORY.findByName(part.getName()).isPresent();
        if (partFromDb) {
            model.addAttribute("partIsAdded",
                    "A part with this name has already been added.");
            return false;
        }
        part.setName(part.getName());
        part.setNeed(need.equals("Да"));
        try {
            if (part.getQuantityInStock() >= 0) part.setQuantityInStock(part.getQuantityInStock());
            else {
                model.addAttribute("quantityInStockError", "Number < 0 !");
                return false;
            }
        } catch (IllegalArgumentException e){
            model.addAttribute("quantityInStockError", "Number format except!");
            return false;
        }
        return true;
    }

    public int computerCompleteCount(){
       List <Part> listParts = PART_REPOSITORY.findAll();
       if (listParts.size() != 0){
           return listParts.stream()
                   .filter(Part::isNeed)
                   .map(Part::getQuantityInStock).min(Integer::compareTo).get();
       }
       return 0;
    }
}
