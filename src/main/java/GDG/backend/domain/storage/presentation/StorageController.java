package GDG.backend.domain.storage.presentation;

import GDG.backend.domain.storage.presentation.dto.response.StorageProfileResponse;
import GDG.backend.domain.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/{cardId}")
    public StorageProfileResponse saveStorage(@PathVariable Long cardId) {
        return storageService.saveStorage(cardId);
    }

    @DeleteMapping("/{storageId}")
    public void deleteStorage(@PathVariable Long storageId) {
        storageService.deleteStorage(storageId);
    }

    @GetMapping()
    public void getStorageList() {
        storageService.getStorageList();
    }
}
