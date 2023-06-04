package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;

import java.util.List;

public interface DesktopComputerService {

    DesktopComputerResponse getById(long id);

    List<DesktopPcShortResponse> getAll();

    DesktopComputerResponse addComputer(DesktopComputerRequest request);

    DesktopComputerResponse updateComputer(long id, DesktopComputerRequest request);

}
