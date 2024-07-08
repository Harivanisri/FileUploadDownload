package File.fileupload.Exception;


public class FileStorageException extends RuntimeException{


	 private static final long serialVersionUID = 1 ;
    
	public FileStorageException(String msg) {
		super(msg);
	}
	
	public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
