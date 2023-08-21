package GDG.backend.domain.oauth.service;

import GDG.backend.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import GDG.backend.domain.oauth.client.OauthMemberClientComposite;
import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.oauth.domain.repository.OauthMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    // 추가
    public Long login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return saved.getId();
    }
}
