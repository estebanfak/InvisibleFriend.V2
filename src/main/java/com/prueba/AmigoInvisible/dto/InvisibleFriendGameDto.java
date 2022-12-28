package com.prueba.AmigoInvisible.dto;

import com.prueba.AmigoInvisible.entity.InvisibleFriendGame;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class InvisibleFriendGameDto {
    private long id;
    private String creator;
    private Date dateOfGift;
    private String name;
    private Set<PlayerDto> players = new HashSet<>();

    public InvisibleFriendGameDto(InvisibleFriendGame invisibleFriendGame){
        this.id = invisibleFriendGame.getId();
        this.name = invisibleFriendGame.getName();
        this.players = invisibleFriendGame.getPlayers().stream().map(PlayerDto::new).collect(Collectors.toSet());
        this.dateOfGift = invisibleFriendGame.getDateOfGift();
        this.creator = invisibleFriendGame.getUser().getLastName() + " " + invisibleFriendGame.getUser().getName();
    }
}
