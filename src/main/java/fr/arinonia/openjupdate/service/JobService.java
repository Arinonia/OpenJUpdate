package fr.arinonia.openjupdate.service;

import fr.arinonia.openjupdate.entity.Rank;
import fr.arinonia.openjupdate.response.Response;

import java.util.List;
import java.util.Optional;

public interface JobService {

    Rank saveRank(Rank rank);
    List<Rank> getRanks();
    Optional<Rank> getRankById(int id);
    Optional<Rank> getRankByName(final String name);
    Rank updateRank(final Rank rank, final Long id);
    void deleteRank();
    Response createRank(final String name);
}
