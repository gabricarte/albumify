package ada.tech.albumify.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SummaryDto {
    private String summary;
    public SummaryDto(String summary) {
        this.summary = summary;
    }

}
