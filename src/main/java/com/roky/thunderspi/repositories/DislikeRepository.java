package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.PostDislike;
import com.roky.thunderspi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DislikeRepository extends JpaRepository<PostDislike,Long> {
	/*@Query("SELECT l from PostDislike l where l.utilis.id= :userId  AND l.postdislike.postId= :idPublication"
			)
	PostDislike GetDislike(@Param("userId") Long idUser,@Param("idPublication") Long idPub) ;*/
	
	PostDislike findByPostdislikeAndUtilis(Post idPublicaiton, User idUser);

}
