package com.prueba.AmigoInvisible.controller;

import com.prueba.AmigoInvisible.constants.Messages;
import com.prueba.AmigoInvisible.entity.Player;
import com.prueba.AmigoInvisible.exception.PlayerNotFoundException;
import com.prueba.AmigoInvisible.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<?> getAllPlayers(){
        return ResponseEntity.ok(playerService.getAllPlayers());
    }
    @GetMapping("/{name}")
    public ResponseEntity<?> getPlayerByName(@PathVariable String name) throws PlayerNotFoundException {
        return ResponseEntity.ok(playerService.getPlayerByName(name));
    }
    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player){
        return ResponseEntity.ok(playerService.addPlayer(player));
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deletePlayer(@PathVariable String name) throws PlayerNotFoundException {
        return ResponseEntity.ok(playerService.deletePlayer(name));
    }
    @PostMapping("/generate")
    public ResponseEntity<?> generateInvFriend(){
        playerService.generateInvisibleFriend();
        return ResponseEntity.ok(Messages.SUCCESS);
    }
    @PostMapping("/revert")
    public ResponseEntity<?> revert(){
        playerService.revert();
        return ResponseEntity.ok(Messages.REVERTED);
    }
    @PostMapping("/modify/{id}")
    public ResponseEntity<?> modifyEmail(@PathVariable long id,
                                          @RequestParam String email) throws PlayerNotFoundException {
        return ResponseEntity.ok(playerService.modifyEmail(id, email));
    }
    @PostMapping("/resend/{id}")
    public ResponseEntity<?> resendEmail(@PathVariable long id) throws PlayerNotFoundException {
        playerService.resendEmail(id);
        return ResponseEntity.ok("Email sent successfully");
    }
}