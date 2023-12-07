package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.model.Laptop;
import com.maruchek.smallshop.model.mapper.LaptopMapper;
import com.maruchek.smallshop.repository.LaptopRepository;
import com.maruchek.smallshop.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final LaptopMapper mapper;


    @Override
    public LaptopResponse getById(long id) {
        Laptop laptop = checkEndGetLaptop(id);
        return mapper.toFullResponse(laptop);
    }

    @Override
    public List<LaptopShortResponse> getAll() {
        List<Laptop> laptops = laptopRepository.findAll();
        return mapper.toListShortResponse(laptops);
    }

    @Override
    public LaptopResponse addLaptop(LaptopRequest request) {
        Laptop laptop = getNewLaptop(request);

        laptopRepository.save(laptop);
        return mapper.toFullResponse(laptop);
    }

    @Override
    public LaptopResponse updateLaptop(long id, LaptopRequest request) {
        Laptop laptop = checkEndGetLaptop(id);

        laptop.setPrice(request.getPrice());
        laptop.setSerialNumber(request.getSerialNumber());
        laptop.setManufacturer(request.getManufacturer());
        laptop.setSize(request.getSize());
        laptop.setStockBalance(request.getStockBalance());

        laptopRepository.save(laptop);

        return mapper.toFullResponse(laptop);
    }

    private Laptop checkEndGetLaptop(long id) {
        return laptopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Laptop with id %s not found", id)));
    }

    private Laptop getNewLaptop(LaptopRequest request) {

        return mapper.toLaptop(request);
    }
}
