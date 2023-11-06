package com.filmify.FilmiFy.UserUploading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUploadingService {

    private final UserUploadingRepository userUploadingRepository;

    @Autowired
    public UserUploadingService(UserUploadingRepository userUploadingRepository){
        this.userUploadingRepository = userUploadingRepository;
    }

}
