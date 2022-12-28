package com.prueba.AmigoInvisible.controller;

import com.prueba.AmigoInvisible.dto.AddPlayerDto;
import com.prueba.AmigoInvisible.exception.InvisibleFriendGameNotFound;
import com.prueba.AmigoInvisible.exception.UserNotFoundException;
import com.prueba.AmigoInvisible.service.InvisibleFriendGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class InvisibleFriendGameController {
    @Autowired
    private InvisibleFriendGameService invisibleFriendGameService;

    @GetMapping
    private ResponseEntity<?> getAllGames(Authentication authentication) throws UserNotFoundException {
        return ResponseEntity.ok(invisibleFriendGameService.getAllGames(authentication));
    }
    @PostMapping("/newGame")
    public ResponseEntity<?> newGame(Authentication authentication,
                                     @RequestParam String name) throws UserNotFoundException {
        return ResponseEntity.ok(invisibleFriendGameService.newGame(authentication, name));
    }
    @PostMapping("/addPlayer")
    public ResponseEntity<?> addPlayer(Authentication authentication,
                                       @RequestBody AddPlayerDto addPlayerDto) throws UserNotFoundException, InvisibleFriendGameNotFound {
        return ResponseEntity.ok(invisibleFriendGameService.addPlayer(authentication, addPlayerDto));
    }
}
