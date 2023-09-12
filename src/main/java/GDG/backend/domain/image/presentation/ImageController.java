package GDG.backend.domain.image.presentation;

import GDG.backend.domain.image.presentation.dto.UploadImageResponse;
import GDG.backend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/images")
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public UploadImageResponse uploadImage(@RequestPart(value = "file") MultipartFile file) {
        return imageService.uploadImage(file);
    }
}
