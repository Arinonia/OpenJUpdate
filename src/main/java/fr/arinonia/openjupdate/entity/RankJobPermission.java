package fr.arinonia.openjupdate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rank_job_permissions")
public class RankJobPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private boolean canRead;
    private boolean canChangeMaintenance;
    private boolean canChangeJson;

    public RankJobPermission() {}

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(final Rank rank) {
        this.rank = rank;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(final Job job) {
        this.job = job;
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
}
