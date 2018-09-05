package com.example.parts_list.service;

import com.example.parts_list.entity.Part;

public interface BaseService {
    boolean update(Long id, String name, int quantityInStock, String need);
    boolean create(Part part, String need);
}
