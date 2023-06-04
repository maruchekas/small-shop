package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.model.Laptop;
import com.maruchek.smallshop.model.mapper.LaptopMapper;
import com.maruchek.smallshop.model.mapper.Mapper;
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


    @Override
    public LaptopResponse getById(long id) {
        final long balance = laptopRepository.count();
        Laptop laptop = checkEndGetLaptop(id);
        return LaptopMapper.toFullResponse(laptop)
                .setStockBalance(balance);
    }

    @Override
    public List<LaptopShortResponse> getAll() {
        List<Laptop> laptops = laptopRepository.findAll();
        return Mapper.convertList(laptops, LaptopMapper::toShortResponse);
    }

    @Override
    public LaptopResponse addLaptop(LaptopRequest request) {
        Laptop laptop = getNewLaptop(request);

        laptopRepository.save(laptop);
        return LaptopMapper.toFullResponse(laptop);
    }

    @Override
    public LaptopResponse updateLaptop(long id, LaptopRequest request) {
        Laptop laptop = checkEndGetLaptop(id);

        laptop.setPrice(request.getPrice())
                .setSerialNumber(request.getSerialNumber())
                .setManufacturer(request.getManufacturer())
                .setSize(request.getSize());

        laptopRepository.save(laptop);

        return LaptopMapper.toFullResponse(laptop);
    }

    private Laptop checkEndGetLaptop(long id) {
        return laptopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Laptop with id %s not found", id)));
    }

    private Laptop getNewLaptop(LaptopRequest request) {
        long balance = laptopRepository.count();
        Laptop laptop = new Laptop();
        laptop.setPrice(request.getPrice());
        laptop.setManufacturer(request.getManufacturer());
        laptop.setSerialNumber(request.getSerialNumber());
        laptop.setSize(laptop.getSize());
        laptop.setStockBalance(++balance);

        return laptop;
    }
}