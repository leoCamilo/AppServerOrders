package com.app.appsender;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class Network extends AsyncTask<String, Void, Void> {
    protected Void doInBackground(String... xml) {
        DatagramSocket socket;
        DatagramPacket outPacket;
        byte[] outBuf;
        final int PORT = 8888;
        final String GROUP = "224.2.2.3";

        try {

            socket = new DatagramSocket();
            outBuf = xml[0].getBytes();
            InetAddress address = InetAddress.getByName(GROUP);
            outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
            socket.send(outPacket);
            // Log.d("teste", "Server sends : " + msg);
            // socket.close();

            /*
            String msg = xml[0];
            // String msg = "Foi " + counter++;
            InetAddress group = InetAddress.getByName(GROUP);
            MulticastSocket socket = new MulticastSocket(PORT);
            socket.joinGroup(group);
            DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(), group, PORT);
            socket.send(hi);
            // get their responses!
            // byte[] buf = new byte[1000];
            // DatagramPacket recv = new DatagramPacket(buf, buf.length);
            // socket.receive(recv);
            // String receive_msg = new String(buf, 0, recv.getLength());
            // System.out.println("From " + recv.getAddress() + " Msg : " + receive_msg);

            // OK, I'm done talking - leave the group...
            // socket.leaveGroup(group);

            // Socket socket2 = new Socket(GROUP, PORT);
            // DataOutputStream DOS = new DataOutputStream(socket2.getOutputStream());
            // DOS.writeUTF("HELLO_WORLD");
            // socket.close();
            */
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }

    protected void onPostExecute() {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}

