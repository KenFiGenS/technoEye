package team.technocat.technoEye.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.technocat.technoEye.model.Camera;
import team.technocat.technoEye.service.CameraService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/cameras")
@RequiredArgsConstructor
public class CameraController {
    @Autowired
    private CameraService cameraService;
    @Autowired
    private DataSource dataSource;

    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        return new ResponseEntity<>(cameraService.getActiveCameras(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCamera(@PathVariable long id) {
        return new ResponseEntity<>(cameraService.findCameraById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<Camera> addCamera(@RequestBody Camera camera) {
        return new ResponseEntity<>(cameraService.addCamera(camera), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/db-check")
    public String check() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return "DB connection OK!";
        }
    }
}
