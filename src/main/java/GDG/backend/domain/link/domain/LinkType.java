package GDG.backend.domain.link.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LinkType {

    INSTAGRAM("Instagram"),
    LINKEDIN("LinkedIn"),
    KAKAOTALK("KakaoTalk");

    private String value;
}
