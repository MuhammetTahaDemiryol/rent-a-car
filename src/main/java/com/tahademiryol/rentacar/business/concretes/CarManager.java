package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateCarRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateAvailableCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateMaintenanceCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.tahademiryol.rentacar.entities.concretes.Car;
import com.tahademiryol.rentacar.entities.enums.State;
import com.tahademiryol.rentacar.repository.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;



    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();

        return cars
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();
    }

    @Override
    public List<GetAllCarsResponse> getAllByState(String state) {
        List<Car> cars = repository.findAll();

        return cars
                .stream()
                .filter((car) -> car.getState().name().equalsIgnoreCase(state) )
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExists(id);
        Car car = repository.findById(id).orElseThrow();
        return mapper.map(car, GetCarResponse.class);
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        repository.save(car);
        return mapper.map(car, CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExists(id);

        Car car = repository.findById(id).orElseThrow();
        car.setDailyPrice(request.getDailyPrice());
        car.setState(request.getState());
        car.setPlate(request.getPlate());
        car.setModelYear(request.getModelYear());
        repository.save(car);
        return mapper.map(car, UpdateCarResponse.class);
    }

    public UpdateMaintenanceCarResponse maintenance(int id){
        checkIfCarExists(id);
        Car car = repository.findById(id).orElseThrow();
        if(car.getState() == State.AVAILABLE){
            car.setState(State.MAINTENANCE);
            repository.save(car);
        }
        else throw new RuntimeException("Car isn't available!");

        return mapper.map(car, UpdateMaintenanceCarResponse.class);
    }

    @Override
    public UpdateAvailableCarResponse makeAvailable(int id) {
        checkIfCarExists(id);

        Car car = repository.findById(id).orElseThrow();
        if(car.getState() == State.AVAILABLE)
            throw new RuntimeException("Car is already available!");
        car.setState(State.AVAILABLE);
        repository.save(car);

        return mapper.map(car, UpdateAvailableCarResponse.class);

    }

    @Override
    public void delete(int id) {
        checkIfCarExists(id);
        repository.deleteById(id);
    }

    // Business rules

    private void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a brand!");
    }
}
