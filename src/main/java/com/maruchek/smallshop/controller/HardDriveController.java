package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.api.response.ErrorResponse;
import com.maruchek.smallshop.api.response.HardDriveResponse;
import com.maruchek.smallshop.api.response.HardDriveShortResponse;
import com.maruchek.smallshop.api.response.ValidationErrorResponse;
import com.maruchek.smallshop.service.HardDriveService;
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

@Tag(name = "Hard Disk Drive REST API operations")
@RestController
@RequestMapping("/api/v1/hdd")
@RequiredArgsConstructor
public class HardDriveController {

    private final HardDriveService hardDriveService;

    @Operation(
            summary = "Get all HDD",
            responses = @ApiResponse(responseCode = "200", description = "Hard Disk Drives", content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = HardDriveShortResponse.class))))
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HardDriveShortResponse> getHardDrives() {
        return hardDriveService.getAll();
    }


    @Operation(
            summary = "Get HDD by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "HDD is found",
                            content = @Content(schema = @Schema(implementation = HardDriveRequest.class))),
                    @ApiResponse(responseCode = "404", description = "HDD not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HardDriveResponse getHardDrive(@PathVariable Long id) {
        return hardDriveService.getById(id);
    }

    @Operation(
            summary = "Add new HDD",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New HDD is added",
                            content = @Content(schema = @Schema(implementation = HardDriveResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HardDriveResponse> addHardDrive(@Valid @RequestBody HardDriveRequest request) {

        return ResponseEntity.ok(hardDriveService.addHardDrive(request));
    }

    @Operation(
            summary = "Edit HDD",
            responses = {
                    @ApiResponse(responseCode = "200", description = "HDD is updated",
                            content = @Content(schema = @Schema(implementation = HardDriveRequest.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HardDriveResponse> editMonitor(@PathVariable Long id,
                                                         @Valid @RequestBody HardDriveRequest request) {

        return ResponseEntity.ok(hardDriveService.updateHardDrive(id, request));
    }
}
