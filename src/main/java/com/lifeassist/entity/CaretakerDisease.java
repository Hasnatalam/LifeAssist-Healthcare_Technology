package com.lifeassist.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "caretaker_diseases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaretakerDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diseaseName;

    @ManyToOne
    @JoinColumn(name = "caretaker_detail_id", nullable = false)
    private CaretakerDetails caretakerDetails;
}

