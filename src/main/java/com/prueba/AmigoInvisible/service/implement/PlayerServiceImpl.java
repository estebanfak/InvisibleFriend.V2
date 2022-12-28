package com.prueba.AmigoInvisible.service.implement;

import com.prueba.AmigoInvisible.constants.Messages;
import com.prueba.AmigoInvisible.dto.PlayerDto;
import com.prueba.AmigoInvisible.entity.Player;
import com.prueba.AmigoInvisible.exception.PlayerNotFoundException;
import com.prueba.AmigoInvisible.repository.PlayerRepository;
import com.prueba.AmigoInvisible.service.EmailSenderService;
import com.prueba.AmigoInvisible.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(PlayerDto::new).collect(Collectors.toList());
    }
    @Override
    public PlayerDto getPlayerByName(String name) throws PlayerNotFoundException {
        return new PlayerDto(playerRepository.findByName(name).orElseThrow(()-> new PlayerNotFoundException(Messages.PLAYER_NOT_FOUND)));
    }
    @Override
    public PlayerDto addPlayer(Player player) {
        return new PlayerDto(playerRepository.save(player));
    }
    @Override
    public String deletePlayer(String name) throws PlayerNotFoundException {
        playerRepository.delete(playerRepository.findByName(name).orElseThrow(()-> new PlayerNotFoundException(Messages.PLAYER_NOT_FOUND)));
        return String.format(Messages.PLAYER_DELETED, name);
    }
    @Override
    public void generateInvisibleFriend() {
        LinkedList<Player> playerLinkedList = new LinkedList<>();
        List<Player> playerList = playerRepository.findAll();
        do{
            Player player = playerList.get((int) Math.floor(Math.random() * playerList.size()));
            playerLinkedList.addLast(player);
            playerList.remove(player);
        }while (playerList.size()!=0);

        for(int i = 0; i<playerLinkedList.size(); i++){
            Player player = playerLinkedList.get(i);
            if(i<playerLinkedList.size()-1){
                player.setFriendToGift(playerLinkedList.get(i+1).getName());
                playerLinkedList.get(i+1).setSelected(true);
            }else{
                player.setFriendToGift(playerLinkedList.get(0).getName());
                playerLinkedList.get(0).setSelected(true);
            }
            setDBAndSendEmail(player);
        }
//        revert();
    }
    @Override
    public void revert() {
        for (Player player : playerRepository.findAll()) {
            player.setSelected(false);
            player.setFriendToGift(null);
            playerRepository.save(player);
        }
    }
    @Override
    public PlayerDto modifyEmail(long id, String email) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id).orElseThrow(()-> new PlayerNotFoundException(Messages.PLAYER_NOT_FOUND));
        player.setEmail(email);
        return new PlayerDto(playerRepository.save(player));
    }
    @Override
    public void resendEmail(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id).orElseThrow(()-> new PlayerNotFoundException(Messages.PLAYER_NOT_FOUND));
        sendEmail(player);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    private void setDBAndSendEmail(Player player){
        playerRepository.save(player);
        sendEmail(player);
    }
    private void sendEmail(Player from) {
        String subject = Messages.EMAIL_SUBJECT;
        String body = String.format(Messages.EMAIL_BODY, from.getName(), from.getFriendToGift());
        emailSenderService.sendEmail(from.getEmail(), subject, body);
    }
}