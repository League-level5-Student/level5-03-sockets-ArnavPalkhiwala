package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	Server server;
	Client client;
	String message = "";
	ServerSocket ss;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {

		
		String response = JOptionPane.showInputDialog(null,
				"Do you want to host a new connection? \nRespond with 'yes' or 'no' ");

		if (response.equalsIgnoreCase("yes")) {
			server = new Server(8080);
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			message = JOptionPane.showInputDialog("What message would you like to send to the client?");
			try {
				ss = new ServerSocket(8080);
				Socket socket = ss.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(message);
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "You must have made a mistake. \nThere was an errror. \nTry again");
			}
			server.start();
		}

		else {
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			try {
				Socket s = new Socket("192.168.86.63", 8080);
				
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String messages = dis.readUTF();
				System.out.println(messages);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Try again \nThere was an error.");
			}
			

		}
	}
}
