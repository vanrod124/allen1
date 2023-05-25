

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;


public class Dashboard extends JFrame {

	public Dashboard() {
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        Dashboard();

        setVisible(true);
    }


    private void initComponents() {
        // Create and configure the welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        // Create and configure the logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for confirmation before logging out
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    new Login(); // Open the Login screen
                }
            }
        });
        add(logoutButton, BorderLayout.SOUTH);
    }


			JButton [] buttonselect = new JButton[2];
	      
			ImageIcon imageIcon = new ImageIcon("inventoryicon.png");
	        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(image);
	        
	        ImageIcon imageIcon1 = new ImageIcon("saleicon.png");
	        Image image1 = imageIcon1.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon1 = new ImageIcon(image1);
	        
	        ImageIcon img = new ImageIcon(new ImageIcon("bgdash1.jpg").getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH));
	    	JLabel background = new JLabel("", img, JLabel.CENTER);
	    	
	    	ImageIcon img2 = new ImageIcon(new ImageIcon("left-arrow.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	    	JLabel back = new JLabel("", img2, JLabel.CENTER);
	    	
	    	JLabel [] saleinvents =new JLabel[2];
	    	String[] labels = {"Inventory","Pont of Sales"};
	    	
	    	
	    	JLabel time =new JLabel("Time : ", JLabel.CENTER);
	    	JLabel date =new JLabel("Date : ", JLabel.CENTER);
	    	JLabel timeLabel =new JLabel();
	    	JLabel dateLabel =new JLabel();
	    	
	    	JLabel dash =new JLabel("DashBoard", JLabel.CENTER);

	    	ImageIcon img3 = new ImageIcon(new ImageIcon("close.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	    	JLabel close = new JLabel("", img3, JLabel.CENTER);
	    	
	 void Dashboard(){
		
	    this.setUndecorated(true);
		this.setTitle("Select ");
		this.setSize(400,280);
		this.getContentPane().setBackground(Color.decode("#205072"));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		dash.setBounds(0,20,400,60);
		dash.setFont(new Font("Cambria", Font.BOLD, 30));
		dash.setForeground(Color.GRAY);
		
		close.setBounds(375,5,20,20);
		close.setFont(new Font("Cambria", Font.BOLD, 16));
		close.setForeground(Color.RED);
		
		close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {          
            	  int result = JOptionPane.showConfirmDialog(null, "<html>Are you sure you want to <br> close ? </html>", "CONFIRM", JOptionPane.YES_NO_OPTION);
                  if (result == JOptionPane.YES_OPTION) {  
                      Component component = (Component) e.getSource();
                      Window window = SwingUtilities.getWindowAncestor(component);
                      window.dispose();
                  }	              	    
            }
        });
		
		background.setBounds(0,0,400,300);
		
	     	timeLabel.setBounds(40,10,100,30);
	        timeLabel.setFont(new Font("Arial", Font.BOLD,10));
	        timeLabel.setForeground(Color.WHITE);
	        dateLabel.setForeground(Color.WHITE);
	        dateLabel.setBounds(40,0,200,30);
	        dateLabel.setFont(new Font("Arial", Font.BOLD, 10));
		  Thread updateTimeThread = new Thread(() -> {
	            while (true) {
	                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
	                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.ENGLISH);

	                String formattedTime = timeFormat.format(new Date());
	                String formattedDate = dateFormat.format(new Date());

	                SwingUtilities.invokeLater(() -> {
	                    timeLabel.setText(formattedTime);
	                    dateLabel.setText(formattedDate);
	                });

	                try {
	                    Thread.sleep(1000); // Delay for 1 second
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	        updateTimeThread.start();
	        this.add(date);
	        this.add(dateLabel);
	        this.add(time);
	        this.add(timeLabel);
	     
	        
	        
		
		int y =60;
		for(int i = 0; i <= saleinvents.length-1 ; i++) {
			saleinvents[i] = new JLabel();
			saleinvents[i].setText(labels[i]);
			saleinvents[i].setBounds(y, 70, 150, 25);
			saleinvents[i].setFont(new Font("Cambria", Font.BOLD, 20));
			saleinvents[i].setForeground(Color.GRAY);
			this.add(saleinvents[i]);
			y += 170;
		}

		int x=30;
		for(int i=0; i<=buttonselect.length-1;i++) {
			buttonselect[i] = new JButton();
			buttonselect[i].setBounds(x,100 , 160, 170);
			buttonselect[i].setFocusable(false);
			buttonselect[i].setOpaque(false);
			buttonselect[i].setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.GRAY));
			buttonselect[i].setBackground(Color.WHITE);
			this.add(buttonselect[i]);
			
			x =+ 210;
			
		}

		buttonselect[0].setIcon( scaledImageIcon);
		buttonselect[0].addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				basic sub =new basic();
		            sub.basic();
		        	dispose();
				  }});
		

		buttonselect[1].setIcon( scaledImageIcon1);	
		buttonselect[1].addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pos sub =new pos();
		            sub.pos();
		            dispose();
				  }});
		
		back.setBounds(5,5,30,30);
		back.setFont(new Font("Cambria", Font.BOLD, 16));
		back.setForeground(Color.RED);
		
		back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {          
            	  int result = JOptionPane.showConfirmDialog(null, "Back to login?","CONFIRM", JOptionPane.YES_NO_OPTION);
                  if (result == JOptionPane.YES_OPTION) {  
                	  new Login();
                	  dispose();
                  }	              	    
            }
        });
		this.add(dash);
		this.add(close);

		this.add(back);
		this.add(background);
		this.setLayout(null);
		this.setVisible(true);
	}
	

}
