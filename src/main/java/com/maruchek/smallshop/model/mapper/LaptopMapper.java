package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.model.Laptop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LaptopMapper {


    LaptopShortResponse toShortResponse(Laptop laptop);

    LaptopResponse toFullResponse(Laptop laptop);

    Laptop toLaptop(LaptopRequest request);

    List<LaptopShortResponse> toListShortResponse(List<Laptop> laptops);
}
