package fr.arinonia.openjupdate.service.impl;

import fr.arinonia.openjupdate.entity.Rank;
import fr.arinonia.openjupdate.repository.JobRepository;
import fr.arinonia.openjupdate.response.Response;
import fr.arinonia.openjupdate.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    /*
     ! Need to implement Job Service before anything else !
     */

    public JobServiceImpl(final JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Rank saveRank(Rank rank) {
        return this.jobRepository.save(rank);
    }

    @Override
    public List<Rank> getRanks() {
        return List.of();
    }

    @Override
    public Optional<Rank> getRankById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Rank> getRankByName(String name) {
        return Optional.empty();
    }

    @Override
    public Rank updateRank(Rank rank, Long id) {
        return null;
    }

    @Override
    public void deleteRank() {

    }

    @Override
    public Response createRank(String name) {
        return null;
    }
}
