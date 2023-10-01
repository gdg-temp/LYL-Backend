package GDG.backend.domain.link.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LinkType {

    CUSTOM("직접 입력"),
    GITHUB("Github"),
    BEHANCE("Behance"),
    TISTORY("Tistory"),
    LINKEDIN("LinkedIn"),
    NAVERBLOG("NaverBlog"),
    INSTAGRAM("Instagram"),
    NOTION("Notion"),
    MEDIUM("Medium"),
    X("X"),
    THREAD("Thread"),
    YOUTUBE("YouTube"),
    TIKTOK("Tiktok");

    private String value;
}
