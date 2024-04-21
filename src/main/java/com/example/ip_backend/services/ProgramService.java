package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.entities.ProgramEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface ProgramService extends CrudService<Integer> {
    Page<Program> getAllByUserId(Pageable page, Integer id);
    Page<Program> getAllByCategoryName(Pageable page, String name);
    Page<Program> getAllByAttributeName(Pageable page,String name);
    Page<Program> getAllByLocationName(Pageable page,String name);
    Page<Program> getAllByPriceIsBetween(Pageable page, BigDecimal lowest, BigDecimal highest);
    Page<Program> getFiltered(Pageable page, BigDecimal priceFrom, BigDecimal priceTo, String categoryName, String locationName,String attributeName, String search);
     Page<Program> findByIdPaginated(Pageable page, List<Integer> programIds);
    Page<Program> findAllPaginated(Pageable page);
    Integer findMaxId();

    List<Program> findAllAddedLast24Hours(Integer categoryId);

}
