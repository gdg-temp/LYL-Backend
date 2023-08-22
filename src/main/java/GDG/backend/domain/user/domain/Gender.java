package GDG.backend.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    MAN("MAN"),
    WOMAN("WOMAN");

    private String value;
}
