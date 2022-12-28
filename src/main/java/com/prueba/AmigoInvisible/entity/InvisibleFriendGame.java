package com.prueba.AmigoInvisible.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvisibleFriendGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "invisibleFriendGame", fetch = FetchType.EAGER)
    private Set<Player> players = new HashSet<>();
    private Date dateOfGift;


    public InvisibleFriendGame(String name, User user, Date dateOfGift){
        this.name = name.toLowerCase();
        this.user = user;
        this.dateOfGift = dateOfGift;
    }
    public void addPlayers (Player player){
        player.setInvisibleFriendGame(this);
        players.add(player);
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
