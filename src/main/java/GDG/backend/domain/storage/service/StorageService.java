package GDG.backend.domain.storage.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.storage.domain.Storage;
import GDG.backend.domain.storage.domain.respository.StorageRepository;
import GDG.backend.domain.storage.exception.CardHostNotStorageException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.*;

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

        if (businessCard.getUser() == user) {
            throw CardHostNotStorageException.EXCEPTION;
        }

        Storage storage = Storage.saveCard(user, businessCard);
        storageRepository.save(storage);

        return createProfileResponse(storage);
    }

    @Transactional
    public void deleteStorage(Long storageId) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        Storage storage = queryStorage(storageId);
        storage.validStorage(currentUserId);

        storageRepository.delete(storage);
    }

    public List<StorageProfileResponse> getStorageList() {
        User user = userUtils.getUserFromSecurityContext();
        List<Storage> storageList = storageRepository.findAllByUser(user);
        List<StorageProfileResponse> storageProfileResponseList = storageList.stream()
                .map(this::createProfileResponse)
                .collect(Collectors.toList());

        return storageProfileResponseList;
    }

    private StorageProfileResponse createProfileResponse(Storage storage) {
        BusinessCard card = storage.getBusinessCard();
        List<LinkInfoVO> linkInfos;
        if (card.getLinks() != null) {
            linkInfos = card.getLinks().stream()
                    .map(Link::getLinkInfoVO)
                    .collect(Collectors.toList());
        } else {
            linkInfos = new ArrayList<>();
        }

        return new StorageProfileResponse(
                card.getBusinessCardInfo(),
                FALSE,
                linkInfos
        );
    }
    public Storage queryStorage(Long storageId) {
        return storageRepository.findById(storageId).orElseThrow(() -> StorageNotFoundException.EXCEPTION);
    }
}
