package main;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.Timer;



/**
 * Created by oxeyo on 17/12/2017.
 */
public class transferfileClient
{
    Socket ClientSoc;
    boolean waitingRes=false;
    DataInputStream din;
    DataOutputStream dout;

    BufferedReader br;
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    transferfileClient(Socket soc)
    {
        try
        {
            ClientSoc=soc;
            din=new DataInputStream(ClientSoc.getInputStream());
            dout=new DataOutputStream(ClientSoc.getOutputStream());
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        catch(Exception ex)
        {
        }
    }

    public void displayMenu() throws Exception
    {
        Capteur c1= new Capteur(1,180,600,"plaque",0,0);
        Capteur c2= new Capteur(2,160,300,"plaque2",0,0);
        Capteur c3= new Capteur(3,170,600,"plaque3",0,0);

        Long d1 = System.currentTimeMillis();
        Long d2 = System.currentTimeMillis();

        while(true)
        {
        	long t1= System.currentTimeMillis()-d1;
        	long t2 = System.currentTimeMillis()-d2;
            if(t1>=10000){
            	 d1 = System.currentTimeMillis();
                dout.writeUTF(c1.getId()+":R"+c1.getRange()+"_P"+c1.getEtat()+c1.getOpt());
               

            }
            if(t2>=30000){

                d2 = System.currentTimeMillis();
                if(c2.getEtat()==0){
                    dout.writeUTF("2:DETECTE");
                }else{
                    dout.writeUTF("2:NA");
                }



            }
            if(c3.getEtat()==0){
                if(c3.getEtat()!=c3.getOldEtat()){
                    dout.writeUTF("3:DETECTE");
                    c3.setOldEtat(c3.getEtat());
                }
            }else{
                if(c3.getEtat()!=c3.getOldEtat()) {
                    dout.writeUTF("3:NA");
                    c3.setOldEtat(c3.getEtat());
                }
            }
            Random rand = new Random();
            int randInt = rand.nextInt( 2);
            c3.setEtat(randInt);
        }
    }
      /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
