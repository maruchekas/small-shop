package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.enums.FormFactor;
import com.maruchek.smallshop.repository.DesktopComputerRepository;
import com.maruchek.smallshop.model.DesktopComputer;
import com.maruchek.smallshop.model.mapper.DesktopPcMapper;
import com.maruchek.smallshop.model.mapper.Mapper;
import com.maruchek.smallshop.service.DesktopComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesktopComputerServiceImpl implements DesktopComputerService {

    private final DesktopComputerRepository computerRepository;


    @Override
    public DesktopComputerResponse getById(long id) {
        final long balance = computerRepository.count();
        DesktopComputer computer = checkEndGetComputer(id);
        return DesktopPcMapper.toFullResponse(computer)
                .setStockBalance(balance);
    }

    @Override
    public List<DesktopPcShortResponse> getAll() {
        List<DesktopComputer> computers = computerRepository.findAll();
        return Mapper.convertList(computers, DesktopPcMapper::toShortResponse);
    }

    @Override
    public DesktopComputerResponse addComputer(DesktopComputerRequest request) {
        DesktopComputer computer = getNewComputer(request);

        computerRepository.save(computer);
        return DesktopPcMapper.toFullResponse(computer);
    }

    @Override
    public DesktopComputerResponse updateComputer(long id, DesktopComputerRequest request) {
        DesktopComputer computer = checkEndGetComputer(id);

        computer.setPrice(request.getPrice())
                .setSerialNumber(request.getSerialNumber())
                .setManufacturer(request.getManufacturer())
                .setFormFactor(FormFactor.valueOf(request.getFormFactor()));

        computerRepository.save(computer);

        return DesktopPcMapper.toFullResponse(computer);
    }

    private DesktopComputer checkEndGetComputer(long id) {
        return computerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Computer with id %s not found", id)));
    }

    private DesktopComputer getNewComputer(DesktopComputerRequest request) {
        long balance = computerRepository.count();
        DesktopComputer computer = new DesktopComputer();
        computer.setPrice(request.getPrice());
        computer.setManufacturer(request.getManufacturer());
        computer.setSerialNumber(request.getSerialNumber());
        computer.setFormFactor(FormFactor.valueOf(request.getFormFactor()));
        computer.setStockBalance(++balance);

        return computer;
    }
}
