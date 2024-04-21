package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.entities.CommentEntity;
import com.example.ip_backend.models.entities.ProgramEntity;
import com.example.ip_backend.repositories.ProgramEntityRepository;
import com.example.ip_backend.services.CommentService;
import com.example.ip_backend.services.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProgramServiceImpl extends CrudJpaService<ProgramEntity, Integer> implements ProgramService {

    private final ModelMapper modelMapper;

    private final ProgramEntityRepository repository;

    public ProgramServiceImpl(ModelMapper modelMapper, ProgramEntityRepository repository) {
        super(repository,modelMapper,ProgramEntity.class);
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public Page<Program> getAllByUserId(Pageable page, Integer id) {
        List<Program> programList=repository.getAllByUserId(id)
                .stream().map(m->(modelMapper.map(m,Program.class))).collect(Collectors.toList());
        return getPostsPageable(page,programList);
    }
    @Override
    public Page<Program> findByIdPaginated(Pageable page, List<Integer> programIds) {
        List<Program> programList=repository.findAllByIds(programIds)
                .stream().map(m->(modelMapper.map(m,Program.class))).collect(Collectors.toList());
        return getPostsPageable(page,programList);
    }

    @Override
    public Page<Program> getAllByCategoryName(Pageable page, String name) {
        List<Program> categoryList=repository.getAllByCategoryName(name)
                .stream().map(m->(modelMapper.map(m,Program.class))).collect(Collectors.toList());
        return getPostsPageable(page,categoryList);
    }

    @Override
    public Page<Program> getAllByAttributeName(Pageable page, String name) {
        List<Program> attributeList=repository.getAllByAttributeName(name)
                .stream().map(m->(modelMapper.map(m,Program.class))).collect(Collectors.toList());
        return getPostsPageable(page,attributeList);
    }

    @Override
    public Page<Program> getAllByLocationName(Pageable page, String name) {
        List<Program> locationList=repository.getAllByLocationName(name)
                .stream().map(m->(modelMapper.map(m,Program.class))).collect(Collectors.toList());
        return getPostsPageable(page,locationList);
    }

    @Override
    public Page<Program> getAllByPriceIsBetween(Pageable page, BigDecimal lowest, BigDecimal highest) {
        List<Program> programList=repository.getAllByPriceIsBetween(lowest,highest)
                .stream().map(m->(modelMapper.map(m,Program.class))).collect(Collectors.toList());
        return getPostsPageable(page,programList);
    }

    @Override
    public Page<Program> getFiltered(Pageable page, BigDecimal priceFrom, BigDecimal priceTo, String categoryName, String locationName,String attributeName,  String search) {
        List<Program> programs=repository.filteredPosts(priceFrom,priceTo,categoryName,locationName,attributeName,search)
                .stream().map(m->modelMapper.map(m,Program.class)).collect(Collectors.toList());
        log.info("Started find all paginated");

        return getPostsPageable(page,programs);
    }

    @Override
    public Page<Program> findAllPaginated(Pageable page) {
        log.info("Started find all paginated");
        List<Program> programs=repository.findAll().stream()
                .map(m->modelMapper.map(m,Program.class)).collect(Collectors.toList());
       return getPostsPageable(page,programs);
    }

    @Override
    public Integer findMaxId() {
        return repository.findMaxId();
    }

    @Override
    public List<Program> findAllAddedLast24Hours(Integer categoryId) {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        Timestamp timestamp = Timestamp.valueOf(twentyFourHoursAgo);
        List<Program> programss=repository.findAllAddedLast24Hours(timestamp,categoryId).stream()
                .map(m->modelMapper.map(m,Program.class)).collect(Collectors.toList());
        return programss;
    }

    private Page<Program> getPostsPageable
            (Pageable page, List<Program> programs) {
        int pageSize = page.getPageSize();
        int start = page.getPageNumber() * pageSize;
        int end = Math.min((page.getPageNumber() + 1) * pageSize, programs.size());

        return new PageImpl<>(programs.subList(start, end), page, programs.size());
    }
}
