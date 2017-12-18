package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Socket ClientSoc;

	DataInputStream din;
	DataOutputStream dout;
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;
	FileOutputStream fos;
	BufferedReader br;

	public Client(Socket socSocket) {
		try {

			din = new DataInputStream(ClientSoc.getInputStream());// client reçoit comme info
			dout = new DataOutputStream(ClientSoc.getOutputStream());// client envoie comme info

			br = new BufferedReader(new InputStreamReader(System.in));// buffered reader

			toServer = new ObjectOutputStream(dout);
			fromServer = new ObjectInputStream(din);

		} catch (Exception ex) {
		}
	}

	public void display() throws Exception {

		int choice;
		String numero1, numero2;
		String msgPresence = "";
		String msgHandle = "";
		String msgRaw = "";

		while (true) {
			System.out.println("\n\n[ DISPLAY ]");
			System.out.println("1. Données brutes");
			System.out.println("2. Detection");
			System.out.println("3. Données unique");
			System.out.println("4. Fermer");
			System.out.print("\nEnter Choice :");

			choice = Integer.parseInt(br.readLine());

			if (choice == 1) {

				dout.writeUTF("4");// premier service
				msgRaw = din.readUTF();
				System.out.println(msgRaw);

			} else if (choice == 2) {
				dout.writeUTF("5");// deuxieme service
				msgPresence = din.readUTF();
				System.out.println(msgPresence);

			} else if (choice == 3) {
				dout.writeUTF("6");// troisième service
				msgHandle = din.readUTF();
				System.out.println(msgHandle);

			} else if (choice == 4) {
				dout.close();
				System.exit(1);
			}
		}
	}

}
