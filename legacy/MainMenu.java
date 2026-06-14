import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class FlightsGUI {

	protected static final GraphicsConfiguration FlightsGUI = null;
	private JFrame frmBrunelFlightBooker;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightsGUI window = new FlightsGUI();
					window.frmBrunelFlightBooker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JComboBox<String> DepartureCity; //all the variables have been set as global across the GUI
	private JComboBox<String> ArrivalCity;
	private JDateChooser DepartureDate;
	private JDateChooser ReturnDate;
	private String DepCity;
	private String ArrCity;
	
	
	/**
	 * Create the application.
	 */
	public FlightsGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBrunelFlightBooker = new JFrame();
		frmBrunelFlightBooker.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmBrunelFlightBooker.getContentPane().setForeground(Color.WHITE);
		frmBrunelFlightBooker.setTitle("Brunel Flight Booker");
		frmBrunelFlightBooker.setBounds(100,100,962,551);
		frmBrunelFlightBooker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBrunelFlightBooker.getContentPane().setLayout(null);
		
		JButton btnShops = new JButton("Shops");
		btnShops.setForeground(Color.WHITE);
		btnShops.setBackground(Color.BLUE);
		btnShops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);/*Due to this part not being implemented yet there will an error message showing up saying its not implemented*/
			}
		});
		btnShops.setBounds(129, 102, 123, 50);
		btnShops.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		frmBrunelFlightBooker.getContentPane().add(btnShops);
		
		
		JButton btnRestaurant = new JButton("Restaurant");
		btnRestaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);/*Due to this part not being implemented yet there will an error message showing up saying its not implemented*/
			}
		});
		btnRestaurant.setForeground(Color.WHITE);
		btnRestaurant.setBackground(Color.BLUE);
		btnRestaurant.setBounds(383, 102, 123, 50);
		btnRestaurant.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		frmBrunelFlightBooker.getContentPane().add(btnRestaurant);
		
		JButton btnFinances = new JButton("Finances");
		btnFinances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);/*Due to this part not being implemented yet there will an error message showing up saying its not implemented*/
			}
		});
		btnFinances.setBounds(627, 102, 123, 50);
		btnFinances.setForeground(Color.WHITE);
		btnFinances.setBackground(Color.BLUE);
		btnFinances.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		frmBrunelFlightBooker.getContentPane().add(btnFinances);
		
		JButton btnDepartureFlights = new JButton("Departure Flights");
		btnDepartureFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);/*Due to this part not being implemented yet there will an error message showing up saying its not implemented*/
			}
		});
		btnDepartureFlights.setForeground(Color.WHITE);
		btnDepartureFlights.setBackground(Color.BLUE);
		btnDepartureFlights.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		btnDepartureFlights.setBounds(129, 400, 123, 50);
		frmBrunelFlightBooker.getContentPane().add(btnDepartureFlights);
		
		JButton btnArrivalFlights = new JButton("Arrival Flights");
		btnArrivalFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);/*Due to this part not being implemented yet there will an error message showing up saying its not implemented*/
			}
		});
		btnArrivalFlights.setForeground(Color.WHITE);
		btnArrivalFlights.setBackground(Color.BLUE);
		btnArrivalFlights.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		btnArrivalFlights.setBounds(627, 400, 123, 50);
		frmBrunelFlightBooker.getContentPane().add(btnArrivalFlights);
		
		DepartureCity = new JComboBox<String>();
		DepartureCity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DepartureCity.setModel(new DefaultComboBoxModel<String>(new String[] {null,"Athens", "Amsterdam", "Bangkok", "Cairo", "Dubai ", "Delhi", "Dublin", "Hong Kong", "Johannesburg", "Lisbon", "London", "Las Vegas", "Lagos", "Madrid", "Moscow", "Marrakesh", "Mexico", "New York", "Paris", "Rome", "Sydney", "San Paulo", "Sweden", "Tokyo ", "Toronto", "Texas"}));
		String DepCity = (String)DepartureCity.getSelectedItem();
		DepartureCity.setBounds(210, 211, 167, 22);
		frmBrunelFlightBooker.getContentPane().add(DepartureCity);
		 
		ArrivalCity = new JComboBox<String>();
		ArrivalCity.setModel(new DefaultComboBoxModel<String>(new String[] {null,"Athens", "Amsterdam", "Bangkok", "Cairo", "Dubai ", "Delhi", "Dublin", "Hong Kong", "Johannesburg", "Lisbon", "London", "Las Vegas", "Lagos", "Madrid", "Moscow", "Marrakesh", "Mexico", "New York", "Paris", "Rome", "Sydney", "San Paulo", "Sweden", "Tokyo ", "Toronto", "Texas"}));
		String ArrCity = (String)ArrivalCity.getSelectedItem(); /* the selected string from the dropbox would stored as a string to later be used in the flightseach class*/
		ArrivalCity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ArrivalCity.setBounds(510, 211, 167, 22);
		frmBrunelFlightBooker.getContentPane().add(ArrivalCity);
		
		DepartureDate = new JDateChooser();
		DepartureDate.setDateFormatString("dd/MM/yyyy");
		DepartureDate.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDateChooser depDate = new JDateChooser();
				String DepartureDate = depDate.getDateFormatString();
			}
		});
		DepartureDate.setBounds(210, 274, 167, 20);
		frmBrunelFlightBooker.getContentPane().add(DepartureDate);
		
		ReturnDate = new JDateChooser();
		ReturnDate.setDateFormatString("dd/MM/yyyy");
		ReturnDate.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDateChooser retDate = new JDateChooser();
				 String ReturnDate = retDate.getDateFormatString();
			}
		});
		
		ReturnDate.setBounds(510, 274, 167, 20);
		frmBrunelFlightBooker.getContentPane().add(ReturnDate);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightsAvailable f1;
				try {
					f1 = new FlightsAvailable(getDepartureCity(),getArrivalCity(),getDepartureDate(),getReturnDate());
					if( DepartureCity.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null,"The departure city must be selected !",null, JOptionPane.ERROR_MESSAGE);/* if the user not selected a departure city the program will pop up with an error message*/ 
						f1.dispose();
					}
					else {
						f1.setVisible(true);/*if there is nothing wrong with the program there will make the second jframe visable*/
					}

					if(ArrivalCity.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null,"The arrival city must be selected !",null, JOptionPane.ERROR_MESSAGE);/*if the user has not selected a arrival city the program will pop up with an error message*/
						f1.dispose();
					}
					else {
						f1.setVisible(true);/*if there is nothing wrong with the program there will make the second jframe visable*/
					}
					if(ArrivalCity.getSelectedItem() == DepartureCity.getSelectedItem()) {
						JOptionPane.showMessageDialog(null,"Please Select an arrival aiport and or a different departure airport",null, JOptionPane.ERROR_MESSAGE);/*if the user has not selected a arrival city the program will pop up with an error message*/
						f1.dispose();
					}
					else {
						f1.setVisible(true);/*if there is nothing wrong with the program there will make the second jframe visable*/
					}	

					if (ReturnDate.getDate() == null) {
						JOptionPane.showMessageDialog(null,"Please Select a return date for your flight ",null, JOptionPane.ERROR_MESSAGE);
						f1.dispose();
					}
					else {
						f1.setVisible(true);
					}
					if(DepartureDate.getDate() == null){
						JOptionPane.showMessageDialog(null,"Please Select a departure date for your flight ",null, JOptionPane.ERROR_MESSAGE);
					}
					else {
						f1.setVisible(true);
					}
					if(DepartureDate.getDate() == ReturnDate.getDate())  {
						JOptionPane.showMessageDialog(null,"Please Select a different return date to your departure date ",null, JOptionPane.ERROR_MESSAGE);
						f1.dispose();
					}
					else {
						f1.setVisible(true);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 /*when the search button is pressed a new frame will open with the flights available*/ 
				
				}
			
			
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.BLUE);
		btnSearch.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		btnSearch.setBounds(383, 350, 123, 50);
		frmBrunelFlightBooker.getContentPane().add(btnSearch);
		
		JLabel lblArrivalCity = new JLabel("Arrival City");
		lblArrivalCity.setBounds(416, 215, 90, 14);
		frmBrunelFlightBooker.getContentPane().add(lblArrivalCity);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setBounds(110, 280, 90, 14);
		frmBrunelFlightBooker.getContentPane().add(lblDepartureDate);
		
		JLabel lblDepartureCity = new JLabel("Departure City");
		lblDepartureCity.setBounds(110, 215, 90, 14);
		frmBrunelFlightBooker.getContentPane().add(lblDepartureCity);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setBounds(416, 274, 90, 14);
		frmBrunelFlightBooker.getContentPane().add(lblReturnDate);
		
	}
	public String getDepartureCity() {
		return DepCity;
	}
	public String getArrivalCity() {
		return ArrCity;
	}
	public String getDepartureDate() {
		String depdate2 = ((JTextField)DepartureDate.getDateEditor().getUiComponent()).getText();
		return depdate2;
	}
	public String getReturnDate() {
		String retdate2 = ((JTextField)ReturnDate.getDateEditor().getUiComponent()).getText();
		return retdate2;
	}
	
	
}

