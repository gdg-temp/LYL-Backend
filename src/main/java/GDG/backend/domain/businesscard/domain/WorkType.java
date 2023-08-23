package GDG.backend.domain.businesscard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WorkType {

    STUDENT("학생"),
    INOCCUPATION("무직");

    private String value;
}
