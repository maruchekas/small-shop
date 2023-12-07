package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.model.Monitor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonitorMapper {

    MonitorShortResponse toShortResponse(Monitor monitor);

    MonitorResponse toFullResponse(Monitor monitor);

    Monitor toMonitor(MonitorRequest request);

    List<MonitorShortResponse> toListShortResponse(List<Monitor> monitors);
}
