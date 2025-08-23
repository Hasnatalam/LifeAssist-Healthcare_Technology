package com.lifeassist.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "caretaker_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaretakerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private int age;
    private String bloodGroup;
    private int pulse;
    private String bp;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ✅ One-to-Many mapping
    @Builder.Default
    @OneToMany(mappedBy = "caretakerDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaretakerDisease> diseases = new ArrayList<>();
    
//    @Builder.Default
//    @OneToMany(mappedBy = "caretaker", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CareServiceBooking> careServiceBooking = new ArrayList<>();
}
