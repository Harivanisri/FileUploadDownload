package File.fileupload.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import File.fileupload.Entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails,Long>{

	List<FileDetails> findByCreatedAtBefore(LocalDateTime cutoffDate);
	
}
