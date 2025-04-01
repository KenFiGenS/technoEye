package team.technocat.technoEye.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CameraDto {
    private long id;
    private String name;
    private String url;
    private Boolean isActive;
    private String location;
}
