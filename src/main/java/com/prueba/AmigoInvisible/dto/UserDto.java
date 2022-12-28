package com.prueba.AmigoInvisible.dto;

import com.prueba.AmigoInvisible.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.prueba.AmigoInvisible.utility.Utility.capitalize;

@Getter
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private Set<InvisibleFriendGameDto> invisibleFriendGameSetDto = new HashSet<>();

    public UserDto(User user){
        this.id = user.getId();
        this.name = capitalize(user.getName());
        this.lastName = capitalize(user.getLastName());
        this.email = user.getEmail();
        this.invisibleFriendGameSetDto = user.getInvisibleFriendGameSet().stream().map(InvisibleFriendGameDto::new).collect(Collectors.toSet());
    }
}
