package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comment")
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@EntityListeners(AuditingEntityListener.class)

public class CommentEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private Integer id;
    @Basic@Column(name = "created_time")
    private Timestamp createdTime;
    @Basic@Column(name = "text")
    private String text;
    @Basic@Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;
    @ManyToOne@JoinColumn(name = "program_id", referencedColumnName = "id", nullable = false)
    private ProgramEntity program;
    @ManyToOne@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
