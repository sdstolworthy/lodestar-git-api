package com.redhat.labs.omp.services;

import com.redhat.labs.omp.models.filesmanagement.CreateCommitMultipleFilesRequest;
import com.redhat.labs.omp.models.GetFileResponse;
import com.redhat.labs.omp.models.GitLabCreateProjectRequest;
import com.redhat.labs.omp.models.GitLabCreateFileInRepositoryRequest;
import com.redhat.labs.omp.resources.filters.Logged;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/v4")
@RegisterRestClient(configKey = "gitlab.api")
@ClientHeaderParam(name = "Private-Token", value = "{com.redhat.labs.omp.config.GitLabConfig.getPersonalAccessToken}")
public interface GitLabService {
    // reference: https://docs.gitlab.com/ee/api/projects.html#list-all-projects
    @GET
    @Path("/projects")
    @Produces("application/json")
    Response getProjects();

    // reference: https://docs.gitlab.com/ee/api/projects.html#remove-project
    @DELETE
    @Path("/projects/{id}")
    @Produces("application/json")
    Response deleteProject(@PathParam("id") @Encoded String projectId);

    // reference: https://docs.gitlab.com/ee/api/projects.html#create-project
    @POST
    @Path("/projects")
    @Produces("application/json")
    Response createNewProject(GitLabCreateProjectRequest request);

    // reference: https://docs.gitlab.com/ee/api/repository_files.html#create-new-file-in-repository
    @POST
    @Path("/projects/{id}/repository/files/{file_path}")
    @Produces("application/json")
    Response createFileInRepository(@PathParam("id") @Encoded String projectId, @PathParam("file_path") @Encoded String filePath, GitLabCreateFileInRepositoryRequest request);

    // reference https://docs.gitlab.com/ee/api/commits.html#create-a-commit-with-multiple-files-and-actions
    @POST
    @Path("/projects/{id}/repository/commits")
    @Produces("application/json")
    Response createFilesInRepository(@PathParam("id") @Encoded String projectId, CreateCommitMultipleFilesRequest request);


    // reference https://docs.gitlab.com/ee/api/repository_files.html
    //https://gitlab.consulting.redhat.com/api/v4/projects/9407/repository/files/schema%2Fmeta.dat?ref=master
    @GET
    @Logged
    @Path("/projects/{id}/repository/files/{file_path}")
    @Produces("application/json")
    GetFileResponse getFile(@PathParam("id") @Encoded String projectId, @PathParam("file_path") @Encoded String filePath, @QueryParam("ref") @Encoded String ref);


}