package com.example.parts_list.repository;

import com.example.parts_list.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PartRepository extends PagingAndSortingRepository<Part, Long> {
    @Override
    Page<Part> findAll(Pageable pageable);

    Optional <Part> findByName (String name);
    Optional<Part> getOne(Long id);
    void delete(Part part);
}
