package team.technocat.technoEye.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CameraDto {
    private long id;
    @Length(min = 1, max = 64)
    private String name;
    @URL
    private String url;
    private Boolean isActive;
    @Length(min = 1, max = 150)
    private String location;
}
