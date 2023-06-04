package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;

import java.util.List;

public interface LaptopService {

    LaptopResponse getById(long id);

    List<LaptopShortResponse> getAll();

    LaptopResponse addLaptop(LaptopRequest request);

    LaptopResponse updateLaptop(long id, LaptopRequest request);

}
