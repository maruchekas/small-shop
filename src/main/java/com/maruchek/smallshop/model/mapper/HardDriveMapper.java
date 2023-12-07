package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.HardDriveResponse;
import com.maruchek.smallshop.api.response.HardDriveShortResponse;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.model.HardDrive;
import com.maruchek.smallshop.model.Monitor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HardDriveMapper {

    HardDriveShortResponse toShortResponse(HardDrive hardDrive);

    HardDriveResponse toFullResponse(HardDrive hardDrive);

    HardDrive toHardDrive(HardDriveRequest request);

    List<HardDriveShortResponse> toListShortResponse(List<HardDrive> hardDrives);
}
