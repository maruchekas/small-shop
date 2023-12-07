package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.api.response.HardDriveResponse;
import com.maruchek.smallshop.api.response.HardDriveShortResponse;
import com.maruchek.smallshop.model.HardDrive;
import com.maruchek.smallshop.model.mapper.HardDriveMapper;
import com.maruchek.smallshop.repository.HardDriveRepository;
import com.maruchek.smallshop.service.HardDriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HardDriveServiceImpl implements HardDriveService {

    private final HardDriveRepository hardDriveRepository;
    private final HardDriveMapper mapper;


    @Override
    public HardDriveResponse getById(long id) {
        HardDrive hardDrive = checkEndGetHardDrive(id);
        return mapper.toFullResponse(hardDrive);
    }

    @Override
    public List<HardDriveShortResponse> getAll() {
        List<HardDrive> hardDrives = hardDriveRepository.findAll();
        return mapper.toListShortResponse(hardDrives);
    }

    @Override
    public HardDriveResponse addHardDrive(HardDriveRequest request) {
        HardDrive hardDrive = getNewHardDrive(request);

        hardDriveRepository.save(hardDrive);
        return mapper.toFullResponse(hardDrive);
    }

    @Override
    public HardDriveResponse updateHardDrive(long id, HardDriveRequest request) {
        HardDrive hardDrive = checkEndGetHardDrive(id);

        hardDrive.setPrice(request.getPrice());
        hardDrive.setSerialNumber(request.getSerialNumber());
        hardDrive.setManufacturer(request.getManufacturer());
        hardDrive.setCapacity(request.getCapacity());
        hardDrive.setStockBalance(request.getStockBalance());

        hardDriveRepository.save(hardDrive);

        return mapper.toFullResponse(hardDrive);
    }

    private HardDrive checkEndGetHardDrive(long id) {
        return hardDriveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("HDD with id %s not found",
                        id)));
    }

    private HardDrive getNewHardDrive(HardDriveRequest request) {
        return mapper.toHardDrive(request);
    }
}
