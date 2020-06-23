package org.youtube.resource;

import org.youtube.api.Youtube;
import org.youtube.db.YoutubeDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("videos")
@Produces(MediaType.APPLICATION_JSON)
public class YoutubeResource {

    YoutubeDao youtubeDao;
    public YoutubeResource(YoutubeDao youtubeDao) {
        this.youtubeDao = youtubeDao;
    }

    @GET
    public  List<Youtube> getVideos()
    {
        return this.youtubeDao.read();
    }

    @POST
    public Youtube createVideo(Youtube youtube) {
        if (youtube == null ) {
            throw new BadRequestException("youtube data missing");
        }
        this.youtubeDao.create(youtube);
        return youtube;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVideo(@PathParam("id") long id) {

        if (youtubeDao.delete(id)) {
            return Response.ok().build();
        }
        throw new NotFoundException();
    }

    @PUT
       public Youtube updateVideo(Youtube youtube) {
        if(youtubeDao.update(youtube)){
            return youtube;
        }
        throw new NotFoundException();
    }

}
