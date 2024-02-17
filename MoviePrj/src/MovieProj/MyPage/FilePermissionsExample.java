package MovieProj.MyPage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FilePermissionsExample {
	
	// Specify the directory where your file is located
	String directoryPath = "C:\\Users\\LOVE\\Desktop\\java_ws\\MoviePrj";

	// Combine the directory path with the file name
	String filePathString = directoryPath + "\\users.txt";

	// Convert the file path string to a Path object
	Path filePath = Paths.get(filePathString);

	// Use the full path with Files.delete() to delete the file
	try {
		Files.delete(filePath);
		System.out.println("File deleted successfully.");
	} catch (IOException e) {
		System.out.println("Failed to delete the file: " + e.getMessage());
		}
}