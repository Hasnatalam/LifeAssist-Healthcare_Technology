package com.lifeassist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lifeassist.dto.BookingRequest;
import com.lifeassist.dto.BookingResponse;
import com.lifeassist.dto.CaregiverResponse;
import com.lifeassist.dto.CaretakerDetailsRequest;
import com.lifeassist.dto.CaretakerDetailsResponse;
import com.lifeassist.dto.CaretakerDiseaseResponse;
import com.lifeassist.entity.Address;
import com.lifeassist.entity.CareServiceBooking;
import com.lifeassist.entity.CaretakerDetails;
import com.lifeassist.entity.CaretakerDisease;
import com.lifeassist.entity.GuardianDetails;
import com.lifeassist.entity.Role;
import com.lifeassist.entity.User;
import com.lifeassist.exception.UserNotFoundException;
import com.lifeassist.repository.BookingRepository;
import com.lifeassist.repository.CaretakerDiseaseRepository;
import com.lifeassist.repository.CaretakerRepository;
import com.lifeassist.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaretakerService {

    private final CaretakerRepository caretakerRepository;
    private final CaretakerDiseaseRepository caretakerDiseaseRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public CaretakerDetailsResponse saveCaretakerDetails(CaretakerDetailsRequest caretakerDetailsReq, User user) {

        // Save caretaker details
        CaretakerDetails caretakerDetails = CaretakerDetails.builder()
                .age(caretakerDetailsReq.getAge())
                .bloodGroup(caretakerDetailsReq.getBloodGroup())
                .bp(caretakerDetailsReq.getBp())
                .user(user)
                .build();

        CaretakerDetails savedCaretakerDetails = caretakerRepository.save(caretakerDetails);

        // Save diseases list
        List<CaretakerDisease> diseases = caretakerDetailsReq.getDiseases()
                .stream()
                .map(d -> CaretakerDisease.builder()
                        .diseaseName(d.getDiseaseName())
                        .caretakerDetails(savedCaretakerDetails)
                        .build())
                .collect(Collectors.toList());

        List<CaretakerDisease> savedAllDisease = caretakerDiseaseRepository.saveAll(diseases);

        // Convert to response DTO
        List<CaretakerDiseaseResponse> caretakerDiseasesRes = savedAllDisease.stream()
                .map(d -> CaretakerDiseaseResponse.builder()
                        .diseaseName(d.getDiseaseName())
                        .build())
                .collect(Collectors.toList());

        // Build response
        return CaretakerDetailsResponse.builder()
                .id(savedCaretakerDetails.getId())
                .age(savedCaretakerDetails.getAge())
                .bloodGroup(savedCaretakerDetails.getBloodGroup())
                .bp(savedCaretakerDetails.getBp())
                .diseases(caretakerDiseasesRes)
                .build();
    }
    
    
    public List<CaregiverResponse> getAllCaregiver(User user){
    	
    	List<Address> address = userRepository.findById(user.getId()).orElseThrow(()-> new UserNotFoundException("User Not Found")).getAddress();
    	
    	List<Address> primaryAddress = address.stream().filter(a->a.isPrimary()==true).collect(Collectors.toList());
    	String area = primaryAddress.get(0).getArea();
    	
    	List<User> caregiver = userRepository.findDistinctByAddress_AreaAndAddress_IsPrimaryTrueAndRole(area, Role.CAREGIVER);

        return caregiver.stream().map(c -> CaregiverResponse.builder()
        		.id(c.getId())
        		.firstName(c.getFirstName())
        		.lastName(c.getLastName())
        		.experienceYears(c.getCaregiverDetails().getExperienceYears())
        		.specialization(c.getCaregiverDetails().getSpecialization())
        		.build()).collect(Collectors.toList());
    }
    
    
    public BookingResponse bookCaregiver(User rowUser, BookingRequest bookingRequest){
    	User user = userRepository.findById(rowUser.getId()).orElseThrow(()-> new UserNotFoundException("User Not found "));
    	//User guardian = userRepository.findById(user.getGuardianDetails().getCaretakerId()).orElseThrow(() -> new UserNotFoundException("Cargiver not found with this id"));;
    	User caregiver = userRepository.findById(bookingRequest.getId()).orElseThrow(() -> new UserNotFoundException("Cargiver not found with this id"));
    	List<Address> primaryAddress = user.getAddress().stream().filter(a->a.isPrimary()==true).collect(Collectors.toList());
    	
    	CareServiceBooking careServiceBooking = CareServiceBooking.builder()
    			.bookingStart(bookingRequest.getBookingStart())
    			.bookingEnd(bookingRequest.getBookingEnd())
    			.amount(caregiver.getCaregiverDetails().getFee())
    			.serviceType(bookingRequest.getServiceType())
    			.notes(bookingRequest.getNotes())
    			.location(primaryAddress.get(0).getArea())
    			.pincode(primaryAddress.get(0).getPinCode())
    			.caregiver(caregiver)
    			.caretaker(user)
    			.build();
    	CareServiceBooking savedBooking = bookingRepository.save(careServiceBooking);
    	
    	BookingResponse bookingResponse = BookingResponse.builder()
    			.id(savedBooking.getBookingId())
    			.caregiverFirstName(savedBooking.getCaregiver().getFirstName())
    			.caregiverLastName(savedBooking.getCaregiver().getLastName())
    			.bookingStart(savedBooking.getBookingStart())
    			.bookingEnd(savedBooking.getBookingEnd())
    			.amount(savedBooking.getAmount())
    			.currency(savedBooking.getCurrency())
    			.build();
    	
    	return bookingResponse;
    }
}
