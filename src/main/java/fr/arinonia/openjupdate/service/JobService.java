package fr.arinonia.openjupdate.service;


import fr.arinonia.openjupdate.entity.Job;
import fr.arinonia.openjupdate.response.Response;

public interface JobService {

    Response createJob(final Job job);
    Response updateJob(final Job job);
    Response deleteJob(final Job job);


}
