package fr.arinonia.openjupdate.repository;

import fr.arinonia.openjupdate.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Rank, Long> {
}
