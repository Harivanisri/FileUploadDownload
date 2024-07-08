package File.fileupload.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import File.fileupload.Entity.FileDetails;
import File.fileupload.Service.FileUploadService;
@RestController
public class FileUploadController{
	
	@Autowired
	private FileUploadService fileuploadservice;
	
	@PostMapping("/upload")
	public FileDetails uploadfile(@RequestParam("file") MultipartFile file) throws IOException {
		return fileuploadservice.uploadfile(file);
	}
	
	@GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
             FileDetails fileDetails =    fileuploadservice.getFile(id);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(fileDetails.getFileType()))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDetails.getFileName() + "\"")
            .body(new ByteArrayResource(fileDetails.getData()));
    }
	
	 
	
	
}