package com.prueba.AmigoInvisible.dto;

import com.prueba.AmigoInvisible.entity.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.prueba.AmigoInvisible.utility.Utility.capitalize;

@Getter
@NoArgsConstructor
public class PlayerDto {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private boolean hasFriendToGift;
    private boolean isSelected;

    public PlayerDto(Player player){
        this.id = player.getId();
        this.name = capitalize(player.getName());
        this.lastName = capitalize(player.getLastName());
        this.email = player.getEmail();
        this.hasFriendToGift = player.getFriendToGift()!=null;
        this.isSelected = player.isSelected();
    }
}
