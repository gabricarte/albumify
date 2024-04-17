package ada.tech.albumify.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SummaryDto {
    private String summary;

    public SummaryDto(String summary) {
        this.summary = summary;
    }

}
