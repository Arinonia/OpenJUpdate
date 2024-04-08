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
    private boolean canRead;
    private boolean canChangeMaintenance;
    private boolean canChangeJson;
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

    public boolean isCanRead() {
        return this.canRead;
    }

    public void setCanRead(final boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanChangeMaintenance() {
        return this.canChangeMaintenance;
    }

    public void setCanChangeMaintenance(final boolean canChangeMaintenance) {
        this.canChangeMaintenance = canChangeMaintenance;
    }

    public boolean isCanChangeJson() {
        return this.canChangeJson;
    }

    public void setCanChangeJson(final boolean canChangeJson) {
        this.canChangeJson = canChangeJson;
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
