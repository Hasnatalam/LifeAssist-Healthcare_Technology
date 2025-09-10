package com.lifeassist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
	 	private LocalDateTime bookingStart; 
	    private LocalDateTime bookingEnd;          
	    private String serviceType;
	    private String notes;
	    private Long id;
	    }
