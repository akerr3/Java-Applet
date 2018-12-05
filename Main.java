package database;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

     public class Main extends Applet implements ActionListener
     {
         String firstName = " ",lastName = " ", email = " ", username = " ", password = " ";
         int age;
         TextField f = new TextField(10);
         TextField l = new TextField(10);
         TextField q = new TextField(10);
         TextField u = new TextField(10);
         TextField p = new TextField(10);
         CheckboxGroup g = new CheckboxGroup();
         Choice c = new Choice();
         Label first = new Label("Enter First Name: ");
         Label last = new Label("Enter Last Name: ");
         Label em = new Label("Enter Email: ");
         Label ag = new Label("Age: ");
         Label user = new Label("Enter User Name: ");
         Label pass = new Label("Enter Password: ");
         int arg = 1;
         Button b1 = new Button("Submit");
         public void init()
         {
        	 createTable(); 
        	 getConnection();
             add(first);
             add(f);
             add(last);
             add(l);
             add(em);
             add(q);
             add(user);
             add(u);
             add(pass);
             add(p);
             add(c);
             add(b1);
             b1.addActionListener(this);

         }
         public void paint(Graphics g)
         {
             g.drawString("First Name: "+firstName, 20,130);
             g.drawString("Last Name: "+lastName, 20,140);
             g.drawString("Email: "+email, 20,150);
         }
         public void actionPerformed(ActionEvent e)
         {
             firstName = f.getText();
             lastName = l.getText();
             email = q.getText();
             username = u.getText();
             password = p.getText();
             insert();
             repaint();
         }
         public void insert()
	{
		final String var1 = f.getText();
		final String var2 = l.getText();
                final String var3 = q.getText();
		final String var5 = username;
                final String var6 = password;
		try{
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO newaccounts(first, last, email, user, pass) VALUES ('"+var1+"','"+var2+"','"+var3+"','"+var5+"','"+var6+"')");
			insert.executeUpdate();
		}
		catch(Exception e){System.out.println(e);}
		finally 
		{
			System.out.println("Insert Completed Table");
		}
		}
         
         
         public static Connection getConnection() {
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/database";
			String username = "root";
			String password = "COSC412-002!";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,username,password);
			System.out.println("connected");
			return con;
		} catch(Exception e){System.out.print(e);}
		
		
		
		return null;
	}
         public static void createTable(){
     		try{
     			Connection con = getConnection();
     			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS newaccounts(id int NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255), email varchar(255), user varchar(255), pass varchar(255), PRIMARY KEY(id))");
     			create.executeUpdate();
     		}catch(Exception e){System.out.println(e);}
     		finally{
     			System.out.println("Function complete.");
     		};
     	}
         public static ArrayList<String> get() throws Exception
     	{
     		try{
     			Connection con = getConnection();
     			PreparedStatement statement = con.prepareStatement("SELECT * FROM newaccounts");
     			
     			ResultSet result = statement.executeQuery();
     			
     			ArrayList<String> array = new ArrayList<String>();
     			System.out.println("ID, First , Last, Email, Username , Password");
     			while(result.next())
     			{
     				System.out.print(result.getString("id"));
     				System.out.print(" , ");
     				System.out.print(result.getString("first"));
     				System.out.print(" , ");
     				System.out.print(result.getString("last"));
     				System.out.print(" , ");
     				System.out.print(result.getString("email"));
     				System.out.print(" , ");
     				System.out.print(result.getString("user"));
     				System.out.print(" , ");
     				System.out.print(result.getString("pass"));
     				System.out.print(" , ");
     				array.add(result.getString("last"));
     			}
     			System.out.println("All records have been selected");
     			return array;
     		}catch(Exception e){System.out.print(e);}
     		return null;
     	}
}
     
            