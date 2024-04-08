package fr.arinonia.openjupdate.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean active = true;
    private String url;
    private int maxConcurrentDownloads  = 1;
    private String maintenanceMessage = "";
    @OneToMany(mappedBy = "job")
    private Set<RankJobPermission> rankJobPermissions;


    public Job() {}


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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public int getMaxConcurrentDownloads() {
        return this.maxConcurrentDownloads;
    }

    public void setMaxConcurrentDownloads(final int maxConcurrentDownloads) {
        this.maxConcurrentDownloads = maxConcurrentDownloads;
    }

    public String getMaintenanceMessage() {
        return this.maintenanceMessage;
    }

    public void setMaintenanceMessage(final String maintenanceMessage) {
        this.maintenanceMessage = maintenanceMessage;
    }

    public Set<RankJobPermission> getRankJobPermissions() {
        return this.rankJobPermissions;
    }

    public void setRankJobPermissions(final Set<RankJobPermission> rankJobPermissions) {
        this.rankJobPermissions = rankJobPermissions;
    }
}
