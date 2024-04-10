package fr.arinonia.openjupdate.service.impl;

import fr.arinonia.openjupdate.entity.Job;
import fr.arinonia.openjupdate.repository.JobRepository;
import fr.arinonia.openjupdate.response.Response;
import fr.arinonia.openjupdate.service.JobService;
import fr.arinonia.openjupdate.service.StorageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;


@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final StorageService storageService;

    public JobServiceImpl(final JobRepository jobRepository, final StorageService storageService) {
        this.jobRepository = jobRepository;
        this.storageService = storageService;
    }

    @Override
    public Response createJob(final Job job) {
        final Response response = new Response();

        try {
            final Path jobDirectory = this.storageService.createJobDirectory(job.getName());

        } catch (final IOException e) {

        }
        return response;
    }

    @Override
    public Response updateJob(final Job job) {
        final Response response = new Response();
        return response;
    }

    @Override
    public Response deleteJob(final Job job) {
        final Response response = new Response();
        return response;
    }
}
