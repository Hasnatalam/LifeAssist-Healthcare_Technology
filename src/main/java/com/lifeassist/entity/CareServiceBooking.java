package com.lifeassist.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "care_service_bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareServiceBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    
    // Booking lifecycle
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
    private LocalDateTime confirmationDate;
    private LocalDateTime completionDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;


    // Payment info
    private Double amount;
    @Builder.Default
    private String currency = "INR";


    private String paymentMethod;
    private String transactionId;

    // Service details
    private String serviceType;
    
    @Column(columnDefinition = "TEXT")
    private String notes;

    private String location;



    // Feedback
    private Integer feedbackRating; // 1–5
    private String reviewComment;

    // Audit fields
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Relationships to User (caretaker, caregiver, guardian)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private CaregiverDetails caregiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caretaker_id", nullable = false)
    private CaretakerDetails caretaker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gurdian_id", nullable = false)
    private GurdianDetails gurdian;
}
