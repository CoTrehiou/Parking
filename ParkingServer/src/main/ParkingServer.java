package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ParkingServer {

	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub

		Socket clientSoc;
		ServerSocket serverSoc;
		
		int portNumber; 
		   	
		if (args.length == 1)
		{	
			
			portNumber = Integer.parseInt(args[0]);
			serverSoc = new ServerSocket(portNumber);
							
		}
		else
		{
			System.out.print("Invalid parameters server  ");
			return;
		} 
		
		System.out.println("\nTCP Server Started on Port Number: " + portNumber);
        
        while(true)
        {
            System.out.println("Waiting for Connection ...");
            clientSoc = serverSoc.accept();
            TCPServerThread serverThread = new TCPServerThread(clientSoc);
        }
    }
}

class TCPServerThread extends Thread
{
    Socket ClientSoc;
    DataInputStream din;
    DataOutputStream dout;
  

    TCPServerThread(Socket soc)
    {
        try
        {
            ClientSoc = soc;
            
            din = new DataInputStream(ClientSoc.getInputStream());
            dout = new DataOutputStream(ClientSoc.getOutputStream());
            
            System.out.println("TCP Client Connected ...");
            start();
        }
        catch(Exception ex)
        {
        	
        }
    }
        
        
    @Override
    public void run()
    {
    	    	
		String msgInput;
		String idMsg;
		String msgPresence = "";
		String msgHandle = "";
		String msgRaw = "";
    	
		
    	
    	
        while(true)
        {
            try
            {
   	
            	
            	msgInput =  din.readUTF(); //message brut           	
            	System.out.println(msgRaw);
            	
            	
            	idMsg = msgInput.substring(0,1);
            	//trouver le premier chiffre
            	
            	
            	switch (idMsg) {
            	case "1": 
            		msgRaw = msgInput;
            		break;
            	case "2":
            		msgPresence = msgInput;
            		break;
            	case "3":
            		msgHandle = msgInput;
            		break;
            	case "4":
            		dout.writeUTF(msgRaw);
            		break;
            	case "5":
            		dout.writeUTF(msgPresence);
            		break;
            	case "6":
            		dout.writeUTF(msgHandle);
            		break;
            		
            	}
            	
            	System.out.println(msgRaw);
            	System.out.println(msgPresence);           	
            	System.out.println(msgHandle);
     	
            }
            catch(Exception ex)
            {
            }
            
            
        }
    }
}
