/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.traccar.notification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.traccar.Context;
import org.traccar.model.Device;
import org.traccar.model.Event;
import org.traccar.model.Position;
import org.traccar.model.SOSNumber;

/**
 *
 * @author cd4
 */
public final class NotificationSMS {

    public static void main(String[] arg) {
        sendSMSAsync(0, null, null);
    }

    private NotificationSMS() {
    }

    public static void sendSMSAsync(final long userId, final Event event, final Position position) {
try {
        String postData = "";
        String retval = "";
        String user = "kinjalbshah";
        String passwd = "temp1234";
        //String mobileNumber = "+919686055994"; // kinjal
        //String mobileNumber = "+919448682752"; //kinjal wife
        String message = "care4dear TestSMS-Kinjal-rob";
        message = NotificationFormatter.formatMessage(userId, event, position);
        System.out.println(message);
        Device device = Context.getIdentityManager().getDeviceById(event.getDeviceId());
        device.getUniqueId();
        System.out.println(device);
        SOSNumber sosNumber = Context.getDataManager().getSOSNumber(device.getUniqueId());
        System.out.println(sosNumber.getSOSNumber());
        String mobileNumber = sosNumber.getSOSNumber();
        String sid = "JALBSH";
        String mtype = "N";
        String dr = "Y";
        postData += "User=" + URLEncoder.encode(user, "UTF-8") + "&passwd=" + passwd + "&mobilenumber="
                + mobileNumber + "&message=" + URLEncoder.encode(message, "UTF-8")
                + "&sid=" + sid + "&mtype=" + mtype + "&DR=" + dr;
        URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

        // If You Are Behind The Proxy Server Set IP And PORT else Comment Below 4 Lines
        //Properties sysProps = System.getProperties();
        //sysProps.put("proxySet", "true");
        //sysProps.put("proxyHost", "Proxy Ip");
        //sysProps.put("proxyPort", "PORT");

        urlconnection.setRequestMethod("POST");
        urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlconnection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
        out.write(postData);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
        retval += decodedString;
        }
        in.close();
        System.out.println(retval);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
