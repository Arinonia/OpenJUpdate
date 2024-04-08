package fr.arinonia.openjupdate.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "rank")
    private Set<RankJobPermission> rankJobPermissions;

    @OneToMany(mappedBy = "rank")
    private Set<User> users;

    public Rank() {}

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public Set<RankJobPermission> getRankJobPermissions() {
        return this.rankJobPermissions;
    }

    public void setRankJobPermissions(final Set<RankJobPermission> rankJobPermissions) {
        this.rankJobPermissions = rankJobPermissions;
    }
}
