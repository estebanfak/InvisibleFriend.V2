package com.prueba.AmigoInvisible.service.implement;

import com.prueba.AmigoInvisible.constants.Messages;
import com.prueba.AmigoInvisible.dto.AddPlayerDto;
import com.prueba.AmigoInvisible.dto.InvisibleFriendGameDto;
import com.prueba.AmigoInvisible.dto.PlayerDto;
import com.prueba.AmigoInvisible.entity.InvisibleFriendGame;
import com.prueba.AmigoInvisible.entity.Player;
import com.prueba.AmigoInvisible.entity.User;
import com.prueba.AmigoInvisible.exception.InvisibleFriendGameNotFound;
import com.prueba.AmigoInvisible.exception.UserNotFoundException;
import com.prueba.AmigoInvisible.repository.InvisibleFriendGameRepository;
import com.prueba.AmigoInvisible.service.InvisibleFriendGameService;
import com.prueba.AmigoInvisible.service.PlayerService;
import com.prueba.AmigoInvisible.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
@Slf4j
public class InvisibleFriendGameServiceImpl implements InvisibleFriendGameService {
    @Autowired
    private InvisibleFriendGameRepository invisibleFriendGameRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;



    @Override
    public InvisibleFriendGameDto newGame(Authentication authentication, String name/*, Date dateOfGift*/) throws UserNotFoundException {
        User user = userService.findByEmail(authentication.getName());
        InvisibleFriendGame invisibleFriendGame = new InvisibleFriendGame();
        invisibleFriendGame.setUser(user);
        invisibleFriendGame.setName(name);
//        invisibleFriendGame.setDateOfGift(dateOfGift);
        invisibleFriendGameRepository.save(invisibleFriendGame);
        user.addInvisibleFriendGame(invisibleFriendGame);
        userService.save(user);
        return new InvisibleFriendGameDto(invisibleFriendGame);
    }

    @Override
    public Set<InvisibleFriendGameDto> getAllGames(Authentication authentication) throws UserNotFoundException {
        User user = userService.findByEmail(authentication.getName());
        return user.getInvisibleFriendGameSet().stream().map(InvisibleFriendGameDto::new).collect(Collectors.toSet());
    }

    @Override
    public PlayerDto addPlayer(Authentication authentication, AddPlayerDto addPlayerDto) throws UserNotFoundException, InvisibleFriendGameNotFound {
        User user = userService.findByEmail(authentication.getName());
        InvisibleFriendGame invisibleFriendGame = invisibleFriendGameRepository.findById(addPlayerDto.getInvisibleFriendGameId()).orElseThrow(()-> new InvisibleFriendGameNotFound(Messages.GAME_NOT_FOUND));
        Player player = new Player(addPlayerDto.getPlayerName(), addPlayerDto.getPlayerLastName(), addPlayerDto.getPlayerName(), invisibleFriendGame);
        invisibleFriendGame.addPlayers(player);
        invisibleFriendGameRepository.save(invisibleFriendGame);
        return new PlayerDto(playerService.save(player));
    }
}
