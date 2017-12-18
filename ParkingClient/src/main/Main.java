package main;

import java.io.IOException;
import java.net.Socket;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Socket soc= null;
        try {
            soc = new Socket("127.0.0.1",6754);
            transferfileClient t=new transferfileClient(soc);
            t.displayMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}