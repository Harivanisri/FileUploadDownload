package File.fileupload.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import File.fileupload.Entity.FileDetails;
import File.fileupload.Repository.FileRepository;
import jakarta.transaction.Transactional;

@Service
public class FileUploadService{
	
	@Autowired
	private FileRepository filerepo;
	
	public FileDetails uploadfile(MultipartFile file) throws IOException {
		
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDetails filedetails = new FileDetails(filename,file.getContentType(),file.getBytes());
		
		FileDetails files = filerepo.save(filedetails);
		return files;
	}
	
	public FileDetails getFile(long id) {
		return filerepo.findById(id).get();
	}
	
	 @Scheduled(cron = "0 0/1 * * * ?") // This cron expression runs the task every minute
	    public void deleteOldFiles() {
	        LocalDateTime cutoffDate = LocalDateTime.now().minusMinutes(2); // Files older than 2 minutes
	        List<FileDetails> 
	        oldFiles = filerepo.findByCreatedAtBefore(cutoffDate);

	        // Delete each file record found
	        for (FileDetails file : oldFiles) {
	            filerepo.delete(file);
	        }

    }
}