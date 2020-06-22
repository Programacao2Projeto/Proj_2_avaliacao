package com.trainingcenter.resources;

import com.trainingcenter.api.Youtube;
import com.trainingcenter.db.YoutubeDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("videos")
@Produces(MediaType.APPLICATION_JSON)
public class YoutubeResource {

    public YoutubeResource(){

    }

    YoutubeDao youtubeDao;
    public YoutubeResource(YoutubeDao youtubeDao) {
        this.youtubeDao = youtubeDao;
    }

    @GET
    public Response getVideos() {
        List<Youtube> allVideos = youtubeDao.getAllVideos();
        return Response.ok(allVideos).build();
    }

    @POST
    public Response createVideo(Youtube youtube) {
        if (youtube == null ) {
            throw new BadRequestException("youtube data missing");
        }
        long id = youtubeDao.insert(youtube);
        youtube = youtubeDao.findById(id);

        if (youtube == null) {
            throw new WebApplicationException("Problemas ao criar o video");
        }

        return Response.ok(youtube).build();
    }

    @GET
    @Path("/{id}")
    public Response getVideos(@PathParam("id") int id) {
        Youtube youtube = youtubeDao.findById(id);

        if (youtube == null)
            throw new WebApplicationException("Este video não existe", Response.Status.NOT_FOUND);

        return Response.ok(youtube).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVideo(@PathParam("id") long id) {

        Youtube youtube = youtubeDao.findById(id);

        if (youtube == null){
            throw new WebApplicationException("Este video não existe", Response.Status.NOT_FOUND);}
        else{
            youtubeDao.delete(youtube);
        }

        return Response.ok(youtube).build();
    }

    @PUT
       public Response updateVideo(Youtube youtube2) {
        if (youtube2 == null ) {
            throw new BadRequestException("youtube data missing");
        }

        Youtube youtube = youtubeDao.findById(youtube2.getId());
        if (youtube == null) {
            throw new WebApplicationException("Video não existe");
        }
        else{

            youtubeDao.update(youtube2);
        }
        return Response.ok(youtube2).build();
    }

}
