package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.ErrorResponse;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.api.response.ValidationErrorResponse;
import com.maruchek.smallshop.service.MonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Monitor REST API operations")
@RestController
@RequestMapping("/api/v1/monitor")
@RequiredArgsConstructor
public class MonitorController {

    private final MonitorService monitorService;

    @Operation(
            summary = "Get all monitors",
            responses = @ApiResponse(responseCode = "200", description = "Monitors", content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = MonitorShortResponse.class))))
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitorShortResponse> getMonitors() {
        return monitorService.getAll();
    }


    @Operation(
            summary = "Get monitor by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Monitor is found",
                            content = @Content(schema = @Schema(implementation = MonitorRequest.class))),
                    @ApiResponse(responseCode = "404", description = "Monitor not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitorResponse getMonitor(@PathVariable Long id) {
        return monitorService.getById(id);
    }

    @Operation(
            summary = "Add new monitor",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New monitor is added",
                            content = @Content(schema = @Schema(implementation = MonitorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonitorResponse> addMonitor(@Valid @RequestBody MonitorRequest request) {

        return ResponseEntity.ok(monitorService.addMonitor(request));
    }

    @Operation(
            summary = "Edit monitor",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Monitor is updated",
                            content = @Content(schema = @Schema(implementation = MonitorRequest.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonitorResponse> editMonitor(@PathVariable Long id,
                                                       @Valid @RequestBody MonitorRequest request) {

        return ResponseEntity.ok(monitorService.updateMonitor(id, request));
    }
}
