package team.technocat.technoEye.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.technocat.technoEye.dto.CameraDto;
import team.technocat.technoEye.dto.CameraDtoForCreate;
import team.technocat.technoEye.service.CameraService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cameras")
@Slf4j
public class CameraController {
    private final CameraService cameraService;
    private final DataSource dataSource;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<CameraDto>> getAllCameras(@PathVariable String userId) {
        log.info("Ендпоинт получения всех камер");
        return new ResponseEntity<>(cameraService.getAllCameras(userId), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CameraDto> getCamera(@PathVariable String userId,
                                               @RequestParam long id) {
        log.info("Ендпоинт получения камеры под id: {}", id);
        return new ResponseEntity<>(cameraService.findCameraById(userId, id), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<CameraDto> addCamera(@RequestBody @Valid CameraDtoForCreate camera) {
        log.info("Ендпоинт создания камеры");
        return new ResponseEntity<>(cameraService.addCamera(camera), HttpStatusCode.valueOf(201));
    }

    @PatchMapping("/{userId}/update")
    public ResponseEntity<CameraDto> updateCamera(@PathVariable String userId,
                                                  @RequestBody @Valid CameraDto camera) {
        log.info("Ендпоинт обновления информации о камере");
        return new ResponseEntity<>(cameraService.updateCamera(userId, camera), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/db-check")
    public String check() throws SQLException {
        log.info("Ендпоинт проверки соединения в БД");
        try (Connection conn = dataSource.getConnection()) {
            throw new RuntimeException();
        } finally {
            return "1";
        }
    }
}
