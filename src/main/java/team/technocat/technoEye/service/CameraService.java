package team.technocat.technoEye.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import team.technocat.technoEye.dto.CameraDto;
import team.technocat.technoEye.dto.CameraDtoForCreate;
import team.technocat.technoEye.model.Camera;
import team.technocat.technoEye.repository.CameraRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final CameraRepository cameraRepository;

    public CameraDto addCamera(CameraDtoForCreate camera) {
        Camera currentCamera = new Camera();
        BeanUtils.copyProperties(camera, currentCamera);
        CameraDto savedCamera = new CameraDto();
        BeanUtils.copyProperties(cameraRepository.save(currentCamera), savedCamera);
        return savedCamera;
    }

    public List<CameraDto> getAllCameras() {
        List<Camera> cameras = cameraRepository.findAll();
        return getCamerasDto(cameras);
    }

    public CameraDto findByUrl(String url) {
        Camera cameraByUrl = cameraRepository.findByUrl(url).orElseThrow(() -> new RuntimeException("Камера не найдена"));
        CameraDto cameraDto = new CameraDto();
        BeanUtils.copyProperties(cameraByUrl, cameraDto);
        return cameraDto;
    }

    public List<CameraDto> getActiveCameras() {
        List<Camera> cameras = cameraRepository.findByIsActive(true);
        return getCamerasDto(cameras);
    }

    public CameraDto updateCamera(CameraDto cameraDto) {
        Camera camera = cameraRepository.findById(cameraDto.getId()).orElseThrow(() -> new RuntimeException("Камера не найдена"));

        Camera updatedCamera = new Camera(
                cameraDto.getId(),
                cameraDto.getName() == null ? camera.getName() : cameraDto.getName(),
                cameraDto.getUrl() == null ? camera.getUrl(): cameraDto.getUrl(),
                cameraDto.getIsActive() == null ? camera.isActive() : cameraDto.getIsActive(),
                cameraDto.getLocation() == null ? camera.getLocation() : cameraDto.getLocation()
        );

        CameraDto updatedCameraDto = new CameraDto();
        BeanUtils.copyProperties(cameraRepository.save(updatedCamera), updatedCameraDto);
        return updatedCameraDto;
    }

    public CameraDto findCameraById(long id) {
        Camera camera = cameraRepository.findById(id).orElseThrow(() -> new RuntimeException("Камера не найдена"));
        CameraDto cameraDto = new CameraDto();
        BeanUtils.copyProperties(camera, cameraDto);
        return cameraDto;
    }

    private List<CameraDto> getCamerasDto(List<Camera> cameras) {
        List<CameraDto> camerasDto = new ArrayList<>();
        for (Camera camera : cameras) {
            CameraDto cameraDto = new CameraDto();
            BeanUtils.copyProperties(camera, cameraDto);
            camerasDto.add(cameraDto);
        }
        return camerasDto;
    }
}
