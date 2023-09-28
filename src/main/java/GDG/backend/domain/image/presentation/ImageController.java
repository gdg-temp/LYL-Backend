package GDG.backend.domain.image.presentation;

import GDG.backend.domain.image.presentation.dto.UploadImageResponse;
import GDG.backend.domain.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "이미지", description = "이미지 업로드 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ImageController {

    private final ImageService imageService;

    @SecurityRequirements
    @Operation(summary = "이미지 업로드")
    @PostMapping("/images")
    public UploadImageResponse uploadImage(@RequestPart(value = "file") MultipartFile file) {
        return imageService.uploadImage(file);
    }
}
