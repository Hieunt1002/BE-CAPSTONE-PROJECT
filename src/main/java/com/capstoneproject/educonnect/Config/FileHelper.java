package com.capstoneproject.educonnect.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileHelper {
	public static void addFile(String sourceFilePath, String destinationFolderPath, String fileName) {
        // Tạo đối tượng File từ đường dẫn file nguồn
        File sourceFile = new File(sourceFilePath);

        // Tạo đối tượng File từ đường dẫn file đích
        File destinationFile = new File(destinationFolderPath, fileName);

        try {
            // Sao chép file từ thư mục nguồn sang thư mục đích
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi không thể sao chép file
        }
    }
}