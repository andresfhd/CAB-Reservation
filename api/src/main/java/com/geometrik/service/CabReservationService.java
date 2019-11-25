/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geometrik.service;

import com.geometrik.model.CabReservation;
import com.geometrik.utilities.Utilities;
import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author soporte@geometrik.com
 */
@Stateless
public class CabReservationService {

    public String execute(CabReservation cabReservation) throws IOException, Exception {

        Socket socket = null;
        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        String response = null;
        try {
            socket = new Socket(Utilities.getPropetyValue(Utilities.PropertyKeys.HOST),
                    Integer.valueOf(Utilities.getPropetyValue(Utilities.PropertyKeys.PORT)));
            
            Logger.getLogger(CabReservationService.class.getName())
                    .log(Level.INFO, "Successfull socket connection at: {0}", new Date());

            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            String messageToSend = "MessageID=" + 1 + "\r\n"
                    + "Password=" + "94E848B0F82127103C6450EAEE7BD41E1B54EB8E4B97D022AC2944A111A73805" + "\r\n"
                    + "MessageType=" + "BookingRequest" + "\r\n"
                    + "BookedForNow=" + true + "\r\n"                    
                    + "PickupStreet=" + cabReservation.getLocationName() + "\r\n"
                    + "PickupLatitude=" + cabReservation.getLatitude() + "\r\n"
                    + "PickupLongitude=" + cabReservation.getLongitude()+ "\r\n"                   
                    + "HoldBooking=" + cabReservation.isHoldBooking() + "\r\n";  
            
            bw.write(Base64.getEncoder().encodeToString(messageToSend.getBytes()));
            bw.write("\r\n");
            bw.write("\r\n");
            bw.flush();

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String serverResponse = br.readLine();
            
            byte[] decodedBytes = Base64.getDecoder().decode(serverResponse);
            String decodedString = new String(decodedBytes);
            response = decodedString;
            
            Logger.getLogger(CabReservationService.class.getName())
                    .log(Level.INFO, "Server Response: {0}", decodedString);
        } catch (Exception ex) {
            Logger.getLogger(CabReservationService.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        } finally {
            if (os != null) {
                os.close();
            }
            if (osw != null) {
                osw.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        return response;
    }
}
