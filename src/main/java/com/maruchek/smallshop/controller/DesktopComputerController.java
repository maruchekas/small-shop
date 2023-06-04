package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.api.response.ErrorResponse;
import com.maruchek.smallshop.api.response.ValidationErrorResponse;
import com.maruchek.smallshop.service.DesktopComputerService;
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

@Tag(name = "Desktop computer REST API operations")
@RestController
@RequestMapping("/api/v1/desktop-pc")
@RequiredArgsConstructor
public class DesktopComputerController {

    private final DesktopComputerService computerService;

    @Operation(
            summary = "Get all computers",
            responses = @ApiResponse(responseCode = "200", description = "Computers", content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = DesktopPcShortResponse.class))))
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DesktopPcShortResponse> getComputers() {
        return computerService.getAll();
    }

    @Operation(
            summary = "Get computer by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Computer is found",
                            content = @Content(schema = @Schema(implementation = DesktopComputerRequest.class))),
                    @ApiResponse(responseCode = "404", description = "Computer not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DesktopComputerResponse getComputer(@PathVariable Long id) {
        return computerService.getById(id);
    }

    @Operation(
            summary = "Add new computer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New computer is added",
                            content = @Content(schema = @Schema(implementation = DesktopComputerResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DesktopComputerResponse> addComputer(@Valid @RequestBody DesktopComputerRequest request) {

        return ResponseEntity.ok(computerService.addComputer(request));
    }

    @Operation(
            summary = "Edit computer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Computer is updated",
                            content = @Content(schema = @Schema(implementation = DesktopComputerResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DesktopComputerResponse> editComputer(@PathVariable Long id,
                                                                @Valid @RequestBody DesktopComputerRequest request) {

        return ResponseEntity.ok(computerService.updateComputer(id, request));
    }
}
