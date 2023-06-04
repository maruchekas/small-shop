package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;

import java.util.List;

public interface MonitorService {

    MonitorResponse getById(long id);

    List<MonitorShortResponse> getAll();

    MonitorResponse addMonitor(MonitorRequest request);

    MonitorResponse updateMonitor(long id, MonitorRequest request);

}
