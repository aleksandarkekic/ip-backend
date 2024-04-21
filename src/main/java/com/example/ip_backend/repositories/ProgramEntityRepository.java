package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.ProgramEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ProgramEntityRepository extends JpaRepository<ProgramEntity, Integer> {
    List<ProgramEntity> getAllByUserId(Integer id);
    List<ProgramEntity> getAllByCategoryName(String name);
    List<ProgramEntity> getAllByAttributeName(String name);
    List<ProgramEntity> getAllByLocationName(String name);
    List<ProgramEntity> getAllByPriceIsBetween(BigDecimal lowest, BigDecimal highest);

    @Query("SELECT p FROM ProgramEntity p WHERE  p.id IN :integers ")
    List<ProgramEntity> findAllByIds(Iterable<Integer> integers);

    List<ProgramEntity> getAllByUserUsername(String username);
    @Query("SELECT p FROM ProgramEntity p WHERE (:priceFrom IS NULL OR p.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
            "AND (:categoryName IS NULL OR p.category.name = :categoryName) " +
            "AND (:locationName IS NULL OR p.location.name = :locationName) " +
            "AND (:attributeName IS NULL OR p.attribute.name = :attributeName) " +
            "AND (:search IS NULL OR p.name LIKE %:search% OR p.category.name LIKE %:search% OR p.location.name LIKE %:search% OR p.attribute.name LIKE %:search%) "
           )
    List<ProgramEntity> filteredPosts(BigDecimal priceFrom, BigDecimal priceTo, String categoryName, String locationName, String attributeName, String search);
    @Query("SELECT MAX(p.id) FROM ProgramEntity p")
    Integer findMaxId();
    @Query("SELECT p FROM ProgramEntity p WHERE p.createdTime >= :twentyFourHoursAgo AND p.category.id = :categoryId")
    List<ProgramEntity> findAllAddedLast24Hours(@Param("twentyFourHoursAgo") Timestamp twentyFourHoursAgo, @Param("categoryId") Integer categoryId);



}
