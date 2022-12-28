package com.prueba.AmigoInvisible.service;

import com.prueba.AmigoInvisible.dto.PlayerDto;
import com.prueba.AmigoInvisible.entity.Player;
import com.prueba.AmigoInvisible.exception.PlayerNotFoundException;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getAllPlayers();
    PlayerDto getPlayerByName(String name) throws PlayerNotFoundException;
    PlayerDto addPlayer(Player player);
    String deletePlayer(String name) throws PlayerNotFoundException;
    void generateInvisibleFriend();
    void revert();
    PlayerDto modifyEmail(long id, String email) throws PlayerNotFoundException;
    void resendEmail(long id) throws PlayerNotFoundException;
    Player save(Player player);
}