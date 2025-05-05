package de.ait;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ResultDto {
    //private TagDto[] tags;
    private List<TagDto> tags;
}
