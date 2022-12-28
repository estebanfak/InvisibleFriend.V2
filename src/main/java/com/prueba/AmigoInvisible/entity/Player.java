package com.prueba.AmigoInvisible.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String lastName;

    private String email;
    private String friendToGift;
    private boolean isSelected;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invisibleFriendGame_id")
    private InvisibleFriendGame invisibleFriendGame;

    public Player(String name, String lastName, String email, InvisibleFriendGame invisibleFriendGame){
        this.name = name.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.email = email.toLowerCase();
        this.isSelected = false;
        this.invisibleFriendGame = invisibleFriendGame;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }
}
