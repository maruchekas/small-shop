package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.enums.FormFactor;
import com.maruchek.smallshop.model.DesktopComputer;
import com.maruchek.smallshop.model.mapper.DesktopPcMapper;
import com.maruchek.smallshop.repository.DesktopComputerRepository;
import com.maruchek.smallshop.service.DesktopComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesktopComputerServiceImpl implements DesktopComputerService {

    private final DesktopComputerRepository computerRepository;
    private final DesktopPcMapper mapper;


    @Override
    public DesktopComputerResponse getById(long id) {
        DesktopComputer computer = getComputer(id);
        return mapper.toFullResponse(computer);
    }

    @Override
    public List<DesktopPcShortResponse> getAll() {
        List<DesktopComputer> computers = computerRepository.findAll();
        return mapper.toListShortResponse(computers);
    }

    @Override
    public DesktopComputerResponse addComputer(DesktopComputerRequest request) {
        DesktopComputer computer = getNewComputer(request);

        computerRepository.save(computer);
        return mapper.toFullResponse(computer);
    }

    @Override
    public DesktopComputerResponse updateComputer(long id, DesktopComputerRequest request) {
        DesktopComputer computer = getComputer(id);

        computer.setPrice(request.getPrice());
        computer.setSerialNumber(request.getSerialNumber());
        computer.setManufacturer(request.getManufacturer());
        computer.setFormFactor(FormFactor.valueOf(request.getFormFactor()));
        computer.setStockBalance(request.getStockBalance());

        computerRepository.save(computer);

        return mapper.toFullResponse(computer);
    }

    private DesktopComputer getComputer(long id) {
        return computerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Computer with id %s not found", id)));
    }

    private DesktopComputer getNewComputer(DesktopComputerRequest request) {

        return mapper.toDesktopPc(request);
    }
}
