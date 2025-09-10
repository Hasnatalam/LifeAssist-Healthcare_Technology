package com.lifeassist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
	private Long id;
	private String caregiverFirstName;
	private String caregiverLastName;
	
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
    
    private Double amount;
    
    private String currency;
   
    
    }
