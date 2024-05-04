package edu.iit.sat.itmd4515.bpasham.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("/beverageinventory")
public class BeverageInventoryBaseResource {
    
    /**
     *Pining if the response is ok and then build
     * @return
     */
    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("/version")
    public Response version(){
        return Response
                .ok("v1")
                .build();
    }
}
