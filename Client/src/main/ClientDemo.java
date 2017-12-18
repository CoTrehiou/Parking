package main;

import java.net.Socket;

public class ClientDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Socket soc= null;
        try {
            soc = new Socket("127.0.0.1",6754);
            Client t= new Client(soc);
            t.display();
        } catch (Exception e) {
            e.printStackTrace();
	}
	}
}


