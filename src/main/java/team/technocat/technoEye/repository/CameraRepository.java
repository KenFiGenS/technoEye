package team.technocat.technoEye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.technocat.technoEye.model.Camera;

import java.util.List;
import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

    List<Camera> findByIsActive(boolean isActive);
    Optional<Camera> findByUrl(String url);
}
