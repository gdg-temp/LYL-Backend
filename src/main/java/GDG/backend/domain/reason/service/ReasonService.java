package GDG.backend.domain.reason.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.reason.domain.Reason;
import GDG.backend.domain.reason.domain.repository.ReasonRepository;
import GDG.backend.domain.reason.exception.ReasonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReasonService implements ReasonServiceUtils{

    private final ReasonRepository reasonRepository;


    @Override
    @Transactional
    public Reason createReason(BusinessCard card, String text) {
//        Long countReason = reasonRepository.countAllByBusinessCard(card);
//        if (countReason <= 3) {
//            throw
//        }
        Reason reason = Reason.addReason(text, card);
        reasonRepository.save(reason);
//        reason.setBusinessCard(card);
        return reason;
    }

    @Override
    public void deleteReason(Reason reason) {
        reasonRepository.delete(reason);
    }

    @Override
    public Reason queryReason(Long reasonId) {
        return reasonRepository.findById(reasonId).orElseThrow(() -> ReasonNotFoundException.EXCEPTION);
    }
}
