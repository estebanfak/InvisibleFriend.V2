package com.prueba.AmigoInvisible.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<InvisibleFriendGame> invisibleFriendGameSet = new HashSet<>();

    public User(String name, String lastName, String email, String password){
        this.name = name.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
    }



    public void addInvisibleFriendGame(InvisibleFriendGame invisibleFriendGame){
        invisibleFriendGame.setUser(this);
        invisibleFriendGameSet.add(invisibleFriendGame);
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setLastName(String lastName){
        this.lastName = lastName.toLowerCase();
    }
}
