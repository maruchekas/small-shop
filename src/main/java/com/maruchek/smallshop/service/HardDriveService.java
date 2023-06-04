package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.HardDriveResponse;
import com.maruchek.smallshop.api.response.HardDriveShortResponse;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;

import java.util.List;

public interface HardDriveService {

    HardDriveResponse getById(long id);

    List<HardDriveShortResponse> getAll();

    HardDriveResponse addHardDrive(HardDriveRequest request);

    HardDriveResponse updateHardDrive(long id, HardDriveRequest request);

}
