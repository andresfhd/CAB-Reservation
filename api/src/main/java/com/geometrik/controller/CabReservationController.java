/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geometrik.controller;

import com.geometrik.model.CabReservation;
import com.geometrik.service.CabReservationService;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author soporte@geometrik.com
 */
@Stateless
@Path("cabReservation")
public class CabReservationController {
    
    @EJB
    CabReservationService cabReservationService;
        
    @POST
    @Produces({"application/json"})
    public Response execute(@Context ContainerRequestContext requestContext, 
            CabReservation cabReservation) {
        Response.ResponseBuilder response = null;
        try {           
            String serverResponse = cabReservationService.execute(cabReservation);
            response = Response.ok(serverResponse);
        } catch (Exception ex) {
            response = Response.serverError();
            Logger.getLogger(CabReservationController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return response.build();
    }  
}
