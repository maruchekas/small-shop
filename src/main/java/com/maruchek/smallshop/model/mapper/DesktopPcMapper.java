package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.model.DesktopComputer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DesktopPcMapper {

    DesktopPcShortResponse toShortResponse(DesktopComputer computer);

    DesktopComputerResponse toFullResponse(DesktopComputer computer);

    DesktopComputer toDesktopPc(DesktopComputerRequest request);

    List<DesktopPcShortResponse> toListShortResponse(List<DesktopComputer> computers);
}
