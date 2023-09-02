package GDG.backend.domain.storage.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.storage.domain.Storage;
import GDG.backend.domain.storage.domain.respository.StorageRepository;
import GDG.backend.domain.storage.exception.ExistStorageException;
import GDG.backend.domain.storage.exception.StorageNotFoundException;
import GDG.backend.domain.storage.presentation.dto.response.StorageProfileResponse;
import GDG.backend.domain.user.domain.User;
import GDG.backend.global.utils.security.SecurityUtils;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StorageService {

    private final StorageRepository storageRepository;
    private final UserUtils userUtils;
    private final BusinessCardServiceUtils businessCardServiceUtils;

    @Transactional
    public StorageProfileResponse saveStorage(Long cardId) {
        User user = userUtils.getUserFromSecurityContext();
        BusinessCard businessCard = businessCardServiceUtils.queryBusinessCard(cardId);

        if (storageRepository.existsByUserAndBusinessCard(user, businessCard)) {
            throw ExistStorageException.EXCEPTION;
        }
        Storage storage = Storage.saveCard(user, businessCard);
        storageRepository.save(storage);

        return new StorageProfileResponse();
    }

    @Transactional
    public void deleteStorage(Long storageId) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        Storage storage = queryStorage(storageId);
        storage.validStorage(currentUserId);

        storageRepository.delete(storage);
    }

    public void getStorageList() {
        User user = userUtils.getUserFromSecurityContext();
        List<Storage> storageList = storageRepository.findAllByUser(user);

    }
    public Storage queryStorage(Long storageId) {
        return storageRepository.findById(storageId).orElseThrow(() -> StorageNotFoundException.EXCEPTION);
    }
}
