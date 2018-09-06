package com.example.parts_list.service;

import com.example.parts_list.entity.Part;
import org.springframework.ui.Model;


public interface BaseService {
    boolean update(Long id, String name, int quantityInStock, String need, Model model);
    boolean create(Part part, String need, Model model);
}
