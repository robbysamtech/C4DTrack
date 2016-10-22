/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.traccar.api.resource;

import java.sql.SQLException;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.traccar.Context;
import org.traccar.api.BaseResource;
import org.traccar.model.SOSNumberInfo;



@Path("sosnumberinfo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SOSResource extends BaseResource {
    @GET
    public Collection<SOSNumberInfo> get(
            @QueryParam("uniqueid") long uniqueid, @QueryParam("priority") String priority)
            throws SQLException {
        if (priority.equals("all")) {
        return Context.getDataManager().getAllSOSNumbers("" + uniqueid);
        } else {
        return Context.getDataManager().getSOSNumberForPriority("" + uniqueid, Integer.parseInt(priority));
        }
    }
    @POST
    public Response add(SOSNumberInfo sosNumberInfo) throws SQLException {
        Context.getPermissionsManager().checkReadonly(getUserId());
        Context.getDataManager().addSOSNumberInfo(sosNumberInfo);
        return Response.ok(sosNumberInfo).build();
    }
}
