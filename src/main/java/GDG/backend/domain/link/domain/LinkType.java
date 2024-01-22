package GDG.backend.domain.link.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LinkType {

    custom("직접 입력"),
    github("github"),
    behance("behance"),
    tistory("tistory"),
    linkedIn("linkedIn"),
    naverblog("naverblog"),
    instagram("instagram"),
    notion("notion"),
    medium("medium"),
    x("x"),
    thread("thread"),
    youtube("youtube"),
    tiktok("tiktok");

    private String value;
}
