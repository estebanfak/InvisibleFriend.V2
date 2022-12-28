package com.prueba.AmigoInvisible.service;

import com.prueba.AmigoInvisible.dto.AddPlayerDto;
import com.prueba.AmigoInvisible.dto.InvisibleFriendGameDto;
import com.prueba.AmigoInvisible.dto.PlayerDto;
import com.prueba.AmigoInvisible.exception.InvisibleFriendGameNotFound;
import com.prueba.AmigoInvisible.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface InvisibleFriendGameService {

    InvisibleFriendGameDto newGame(Authentication authentication, String name) throws UserNotFoundException;
    Set<InvisibleFriendGameDto> getAllGames(Authentication authentication) throws UserNotFoundException;
    PlayerDto addPlayer(Authentication authentication, AddPlayerDto addPlayerDto) throws UserNotFoundException, InvisibleFriendGameNotFound;
}
