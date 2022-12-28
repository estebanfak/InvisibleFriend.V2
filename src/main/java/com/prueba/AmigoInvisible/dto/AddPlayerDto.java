package com.prueba.AmigoInvisible.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddPlayerDto {
    private long invisibleFriendGameId;
    private String playerName;
    private String playerLastName;
    private String playerEmail;
}
