package com.prueba.AmigoInvisible.repository;

import com.prueba.AmigoInvisible.entity.InvisibleFriendGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvisibleFriendGameRepository extends JpaRepository<InvisibleFriendGame, Long> {
}
