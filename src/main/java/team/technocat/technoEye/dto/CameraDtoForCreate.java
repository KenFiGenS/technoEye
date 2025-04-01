package team.technocat.technoEye.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
public class CameraDtoForCreate {
    @NonNull
    @Length(min = 1, max = 64)
    private String name;
    @NonNull
    @URL
    private String url;
    private boolean isActive;
    @NonNull
    @Length(min = 1, max = 150)
    private String location;
}
