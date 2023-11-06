package com.filmify.FilmiFy.UserUploading;

import com.filmify.FilmiFy.Film.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserUploadingRepository extends JpaRepository<UserUploading, Long> {
}
