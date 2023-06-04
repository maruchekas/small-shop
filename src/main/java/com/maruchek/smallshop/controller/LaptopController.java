package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.ErrorResponse;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.api.response.ValidationErrorResponse;
import com.maruchek.smallshop.service.LaptopService;
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

@Tag(name = "Laptop REST API operations")
@RestController
@RequestMapping("/api/v1/laptop")
@RequiredArgsConstructor
public class LaptopController {

    private final LaptopService laptopService;

    @Operation(
            summary = "Get all laptops",
            responses = @ApiResponse(responseCode = "200", description = "laptops", content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = LaptopShortResponse.class))))
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LaptopShortResponse> getLaptops() {
        return laptopService.getAll();
    }


    @Operation(
            summary = "Get laptop by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Laptop is found",
                            content = @Content(schema = @Schema(implementation = LaptopRequest.class))),
                    @ApiResponse(responseCode = "404", description = "laptop not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LaptopResponse getLaptop(@PathVariable Long id) {
        return laptopService.getById(id);
    }

    @Operation(
            summary = "Add new laptop",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New laptop is added",
                            content = @Content(schema = @Schema(implementation = LaptopResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaptopResponse> addLaptop(@Valid @RequestBody LaptopRequest request) {

        return ResponseEntity.ok(laptopService.addLaptop(request));
    }

    @Operation(
            summary = "Edit laptop",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Laptop is updated",
                            content = @Content(schema = @Schema(implementation = LaptopRequest.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaptopResponse> editMonitor(@PathVariable Long id,
                                                      @Valid @RequestBody LaptopRequest request) {

        return ResponseEntity.ok(laptopService.updateLaptop(id, request));
    }
}
