package team.technocat.technoEye.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.technocat.technoEye.model.Camera;
import team.technocat.technoEye.repository.CameraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraService {
    @Autowired
    private CameraRepository cameraRepository;

    public Camera addCamera(Camera camera) {
        return cameraRepository.save(camera);
    }

    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    public Camera findCameraById(long id) {
        return cameraRepository.getReferenceById(id);
    }

    // Получение всех активных камер
    public List<Camera> getActiveCameras() {
        return cameraRepository.findByIsActive(true);
    }

    // Обновление URL камеры
    public Camera updateCameraUrl(Long id, String newRtspUrl) {
        Camera camera = cameraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Камера не найдена"));
        camera.setRtspUrl(newRtspUrl);
        return cameraRepository.save(camera);
    }
}
