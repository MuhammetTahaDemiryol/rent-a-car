package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateCarRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetCarResponse;
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
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        List<Car> cars = filterCarsByMaintenanceState(includeMaintenance);

        return cars
                .stream()
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
        car.setState(State.AVAILABLE);
        repository.save(car);
        return mapper.map(car, CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExists(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        return mapper.map(car, UpdateCarResponse.class);

    }

    @Override
    public void delete(int id) {
        checkIfCarExists(id);
        repository.deleteById(id);
    }


    @Override
    public List<GetAllMaintenanceResponse> showMaintenances(int id) {
        Car car = repository.findById(id).orElseThrow();
        return car.getMaintenances().stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenanceResponse.class)).toList();
    }


    @Override
    public void changeState(int carId, State state) {
        Car car = repository.findById(carId).orElseThrow();
        car.setState(state);
        repository.save(car);
    }

    private void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a car!");
    }

    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance)
            return repository.findAll();
        return repository.findAllByStateIsNot(State.MAINTENANCE);
    }

}
