package GDG.backend.domain.storage.presentation;

import GDG.backend.domain.storage.presentation.dto.response.StorageProfileResponse;
import GDG.backend.domain.storage.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "명함 저장", description = "다른 사람 명함 관련 API")
@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @Operation(summary = "다른 사람의 명함 저장하기")
    @PostMapping("/{encodeId}")
    public StorageProfileResponse saveStorage(@Parameter(description = "저장할 명함 encodeId", in = PATH) @PathVariable String encodeId) {
        return storageService.saveStorage(encodeId);
    }

    @Operation(summary = "저장한 명함 삭제하기")
    @DeleteMapping("/{storageId}")
    public void deleteStorage(@Parameter(description = "저장한 명함 Id", in = PATH) @PathVariable Long storageId) {
        storageService.deleteStorage(storageId);
    }

    @Operation(summary = "내가 저장한 다른 사람의 명함 조회하기")
    @GetMapping()
    public List<StorageProfileResponse> getStorageList() {
        return storageService.getStorageList();
    }
}
