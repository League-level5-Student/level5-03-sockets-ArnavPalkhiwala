package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton button = new JButton("Click");

	ObjectOutputStream os;
	ObjectInputStream is;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {

		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8080);
			frame.setTitle("Server");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e) -> {
				String message = JOptionPane.showInputDialog("What message do you want to send to the client?");
				server.sendMessage(message);
			});
			frame.add(panel);
			panel.add(button);
			frame.setVisible(true);
			frame.setSize(400, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();

		} else {
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			frame.setTitle("Client");
			button.addActionListener((e) -> {
				String message = JOptionPane.showInputDialog("What message do you want to send to the server?");
				client.sendMessage(message);
			});
			frame.add(panel);
			panel.add(button);
			frame.setVisible(true);
			frame.setSize(400, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}

	}
}
