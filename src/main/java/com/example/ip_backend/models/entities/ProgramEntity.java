package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
@Data
@Setter
@Getter
@Entity
@Table(name = "program")
@SQLDelete(sql = "UPDATE program SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@EntityListeners(AuditingEntityListener.class)
public class ProgramEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "difficulty")
    private Integer difficulty;
    @Basic
    @Column(name = "duration")
    private Integer duration;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @Basic
    @Column(name = "coach")
    private String coach;
    @Basic
    @Column(name = "contact")
    private String contact;
    @Basic
    @Column(name = "deleted")
    private Boolean deleted;
    @Basic
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)

    private Timestamp createdTime;
    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<PhotoEntity> photos;
    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<CommentEntity> comments;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "id", nullable = false)
    private AttributeEntity attribute;
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private LocationEntity location;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;




}
