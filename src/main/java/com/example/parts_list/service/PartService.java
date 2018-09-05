package com.example.parts_list.service;

import com.example.parts_list.entity.Part;
import com.example.parts_list.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PartService implements BaseService {
    private final PartRepository PART_REPOSITORY;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.PART_REPOSITORY = partRepository;
    }

    @Override
    public boolean update(Long id, String name, int quantityInStock, String need) {
        Optional <Part> part = PART_REPOSITORY.getOne(id);
        Optional <Part> partName = PART_REPOSITORY.findByName(name);
        if (partName.isPresent() || !part.isPresent()) {
            return false;
        }
        if (!StringUtils.isEmpty(name)) part.get().setName(name);
        if (quantityInStock <= 0) part.get().setQuantityInStock(quantityInStock);
        if (!StringUtils.isEmpty(need)) part.get().setNeed(need.equals("Да"));
        return true;
    }

    @Override
    public boolean create(Part part, String need) {
        Optional <Part> partName = PART_REPOSITORY.findByName(part.getName());
        if (partName.isPresent()){
            return false;
        }
        try {
            part.setName(part.getName());
            part.setQuantityInStock(part.getQuantityInStock());
            part.setNeed(need.equals("Да"));
        }catch (Exception e){
            e.printStackTrace();
        }
        PART_REPOSITORY.save(part);
        return true;
    }
}
