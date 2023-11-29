package com.example.thicketchair.controller;

import com.example.thicketchair.dto.request.RequestCreateChairDto;
import com.example.thicketchair.dto.request.RequestUpdateChairDto;
import com.example.thicketchair.dto.response.ResponseChairDto;
import com.example.thicketchair.service.ChairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chairs")
@RequiredArgsConstructor
@Tag(name = "Controller", description = "API")
public class ChairApiController {

    private final ChairService chairService;

    @PostMapping // api 명세 => POST /chairs
    @Operation(summary = "등록", description = "타입, 수량, 가격을 작성하여 신규등록 합니다.")
    public ResponseEntity<?> createChair(@RequestBody @Valid RequestCreateChairDto dto) {
        chairService.createChair(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("좌석 생성 완료");
    }

    @GetMapping("/{uuid}") // api 명세 => GET /chairs/{uuid}
    @Operation( summary = "단일 조회",
            description = "uuid 기준으로 좌석 1개를 조회 합니다.",
             parameters = {@Parameter( name = "uuid",
                                description = "UUID of the chair to find",
                                   required = true,
                                         in = ParameterIn.PATH)})
    public ResponseEntity<?> findByUuid(@PathVariable @NotBlank String uuid) {
        ResponseChairDto responseChairDto = chairService.findChairByByUuid(uuid);
        return new ResponseEntity<>(responseChairDto, HttpStatus.OK);
    }

    @GetMapping // api 명세 => GET /chairs
    @Operation(summary = "전체 조회", description = "전체 좌석을 조회 합니다.")
    public ResponseEntity<?> getAllChairs() {
        List<ResponseChairDto> allChairs = chairService.getAllChairs();
        return new ResponseEntity<>(allChairs, HttpStatus.OK);
    }

    @PatchMapping("/{uuid}") // api 명세 => PATCH /chairs/{uuid}
    @Operation( summary = "수정",
            description = "uuid 기준으로 좌석 1개를 수정 합니다.",
             parameters = {@Parameter( name = "uuid",
                                description = "UUID of the chair to update",
                                   required = true,
                                         in = ParameterIn.PATH)})
    public ResponseEntity<?> updateChair(@PathVariable @NotBlank String uuid,
                                         @RequestBody @Valid RequestUpdateChairDto dto) {
        chairService.updateChair(uuid, dto);
        return ResponseEntity.status(HttpStatus.OK).body("좌석 수정 완료");
    }

    @DeleteMapping("/{uuid}") // api 명세 => DELETE /chairs/{uuid}
    @Operation( summary = "삭제",
            description = "uuid 기준으로 좌석 1개를 삭제 합니다.",
             parameters = {@Parameter(name = "uuid",
                               description = "UUID of the chair to delete",
                                  required = true,
                                        in = ParameterIn.PATH)})
    public ResponseEntity<?> deleteChair(@PathVariable @NotBlank String uuid) {
        chairService.deleteChair(uuid);
        return ResponseEntity.status(HttpStatus.OK).body("좌석 삭제 완료");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> bindingHandler(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }
}
