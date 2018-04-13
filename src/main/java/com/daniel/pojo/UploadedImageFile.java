package com.daniel.pojo;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImageFile {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
