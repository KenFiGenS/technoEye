package team.technocat.technoEye.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.technocat.technoEye.model.Camera;
import team.technocat.technoEye.model.CameraDto;
import team.technocat.technoEye.repository.CameraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final CameraRepository cameraRepository;

    public Camera addCamera(Camera camera) {
        return cameraRepository.save(camera);
    }

    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    public Camera findByUrl(long id) {
        return cameraRepository.getReferenceById(id);
    }

    public List<Camera> getActiveCameras() {
        return cameraRepository.findByIsActive(true);
    }

    public Camera updateCameraUrl(CameraDto cameraDto) {
        Camera camera = cameraRepository.findById(cameraDto.getId()).orElseThrow(() -> new RuntimeException("Камера не найдена"));

        Camera updatedCamera = new Camera(
                cameraDto.getId(),
                cameraDto.getName() == null ? camera.getName() : cameraDto.getName(),
                cameraDto.getUrl() == null ? camera.getUrl(): cameraDto.getUrl(),
                cameraDto.getIsActive() == null ? camera.isActive() : cameraDto.getIsActive(),
                cameraDto.getLocation() == null ? camera.getLocation() : cameraDto.getLocation()
        );

        return cameraRepository.save(updatedCamera);
    }

    public Camera findCameraById(long id) {
        return cameraRepository.findById(id).orElseThrow(() -> new RuntimeException("Камера не найдена"));
    }
}
