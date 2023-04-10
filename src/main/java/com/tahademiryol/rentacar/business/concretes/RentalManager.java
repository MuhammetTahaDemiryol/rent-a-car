package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.abstracts.PaymentService;
import com.tahademiryol.rentacar.business.abstracts.RentalService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Rental.GetAllRentalsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Rental.GetRentalResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.tahademiryol.rentacar.common.dto.CreateRentalPaymentRequest;
import com.tahademiryol.rentacar.entities.concretes.Rental;
import com.tahademiryol.rentacar.entities.enums.State;
import com.tahademiryol.rentacar.repository.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();

        return rentals
                .stream()
                .map(rental -> mapper.map(rental, GetAllRentalsResponse.class))
                .toList();
    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();

        return mapper.map(rental,GetRentalResponse.class);
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setStartDate(LocalDateTime.now());

        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);

        repository.save(rental);
        carService.changeState(rental.getCar().getId() , State.RENTED);
        return mapper.map(rental, CreateRentalResponse.class);
    }



    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        return mapper.map(rental, UpdateRentalResponse.class);
    }

    @Override
    public void delete(int id) {
        int carId = repository.findById(id).get().getCar().getId();
        carService.changeState(carId,State.AVAILABLE);
        repository.deleteById(id);
    }

    private void checkIfRentalExists(int id){
        if (!repository.existsById(id))
            throw new RuntimeException("No such a rental ");
    }

    private void checkIfCarAvailable(int carId){
        if(!carService.getById(carId).getState().equals(State.AVAILABLE)){
            throw new RuntimeException("The car is not available!");
        }

    }

    public double getTotalPrice(Rental rental){
        return rental.getDailyPrice() * rental.getRentedForDays();
    }
}
