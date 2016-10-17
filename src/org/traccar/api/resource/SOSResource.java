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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.traccar.Context;
import org.traccar.api.BaseResource;
import org.traccar.model.SOSNumber;
import org.traccar.model.SOSNumberPriority;




@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SOSResource extends BaseResource {
    @GET
    @Path("sosnumbersforpriority")
    public SOSNumber getSOSNumberForPriority(
            @QueryParam("uniqueid") long uniqueid, @QueryParam("priority") String priority)
            throws SQLException {
        return Context.getDataManager().getSOSNumberForPriority("" + uniqueid, "" + priority);
    }
    @GET
    @Path("allsosnumbers")
    public Collection<SOSNumberPriority> getAllSOSNumbers(
            @QueryParam("uniqueid") String uniqueid)
            throws SQLException {
        return Context.getDataManager().getAllSOSNumbers(uniqueid);
    }
}
