import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;


public class NetworkServer extends Observable implements Runnable {

	
	
	ServerSocket serversocket;
	Socket klientSock;
	DataInputStream in;
	DataOutputStream out;
	Point point;
	int idCourse;
	int course=0;
	int i =1;
	private boolean temp = true;
	private Thread thread;
	
	public NetworkServer()
	{	
		try
		{
			System.out.println("Nu har vi skapat servern");
			serversocket = new ServerSocket(27015);
			System.out.println("Vi har skapat socket");
			thread = new Thread(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while(temp)
		{
		try
		{
			klientSock = serversocket.accept();
			System.out.println(klientSock.getInetAddress().getHostName() + " har anslutit sig");
			new ServerClientHandler(klientSock);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		}
	}
	
	public void start()
	{
		temp = false;
		try
		{
			new Socket(serversocket.getInetAddress(), serversocket.getLocalPort()).close();
		}
		catch(IOException e)
		{
		}
		System.out.println("f�rs�ker stoppa conent");
		System.out.println(temp);
	}
	
	public void connect()
	{
		thread.start();
	}
	
	
	public void sendPoint(Point point, int id)
	{
		int x;
		int y;
		
		x = (int) point.getX();
		y = (int) point.getY();
		
		try {
			out.writeInt(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeInt(y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeInt(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
}