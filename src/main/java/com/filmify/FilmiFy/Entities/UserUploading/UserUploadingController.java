package com.filmify.FilmiFy.Entities.UserUploading;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user-uploading")
public class UserUploadingController {

    private final UserUploadingService userUploadingService;

    public UserUploadingController(UserUploadingService userUploadingService){
        this.userUploadingService = userUploadingService;
    }
}
