/*
package org.perscholas.services;

import org.perscholas.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

        public String uploadFile(MultipartFile file) {

            try {
                // location
                Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                Files.copy(file.getInputStream(),copyLocation, StandardCopyOption.REPLACE_EXISTING);
                return copyLocation.toString();
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again.");
            }
        }
}
*/
