import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

public class FlightsAvailable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightsAvailable frame = new FlightsAvailable(null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 * @param string 
	 * @param depDate 
	 * @param arrCity 
	 * @param depCity 
	 * @throws IOException 
	 */
	String flightpath = "C:\\Users\\maria\\eclipse-workspace\\FlightComponent\\src\\Flights.csv";
	public FlightsAvailable(String depCity, String arrCity, String depDate, String retDate ) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1063, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		table = new JTable();
		table.setBounds(753, 297, -353, -128);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Flights Available");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(42, 23, 315, 57);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SeatingPlan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton.setBounds(160, 571, 123, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Purchase");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"This part has not been inplemented yet !",null, JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(42, 632, 123, 50);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Departure Flights");
		lblNewLabel_1.setBounds(52, 97, 99, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 122, 867, 182);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		DefaultTableModel departureModel = new DefaultTableModel();
		departureModel.setColumnIdentifiers(new String[] {"Departing City", "Airport", "Arriving City", "Airport", "Departure Time", "Arrival Time","Flight Duration", "Flight Number"});
		table_1.setModel(departureModel);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(52, 399, 865, 151);
		contentPane.add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		DefaultTableModel arrivalModel = new DefaultTableModel();
		arrivalModel.setColumnIdentifiers(new String[] {"Departing City", "Airport", "Arriving City", "Airport", "Departure Time", "Arrival Time","Flight Duration", "Flight Number"});
		table_2.setModel(arrivalModel);
		table_2.getColumnModel().getColumn(0).setPreferredWidth(93);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(93);
		table_2.getColumnModel().getColumn(6).setPreferredWidth(88);
		
		JLabel lblNewLabel_1_1 = new JLabel("Arrival Flights");
		lblNewLabel_1_1.setBounds(55, 374, 99, 14);
		contentPane.add(lblNewLabel_1_1);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(88);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(89);
	}


	private ArrayList<Flight> departureFlight = new ArrayList<>();
	private ArrayList<Flight> returnFlight = new ArrayList<>();
	public class Flight {
		String DepartureCity;
		String DeparutreAirport;
		String ArrivalCity;
		String ArrivalAirport;
		String DepartureTime;
		String ArrivalTime;
		String FlightDuration;
		String FlightNumber;
		Flight(String DepartureCity, String DepartureAirport, String ArrivalCity, String ArrivalAirport, String DepartureTime, String ArrivalTime, String FlightDuration, String FlightNumber ){
			this.DepartureCity = DepartureCity;
			this.DeparutreAirport = DepartureAirport;
			this.ArrivalCity = ArrivalCity;
			this.ArrivalAirport = ArrivalAirport;
			this.DepartureTime = DepartureTime;
			this.ArrivalTime = ArrivalTime;
			this.FlightDuration = FlightDuration;
			this.FlightNumber = FlightNumber;
		}
					
		}

	
	private void loadData(DefaultTableModel departureModel){
        String[] arr;
        String line;
        String flightpath = "C:\\Users\\maria\\eclipse-workspace\\FlightComponent\\src\\Flights.csv";
        try {
            BufferedReader fs = new BufferedReader(new FileReader(flightpath));
            while ((line = fs.readLine()) !=null) {
                arr = line.split(",");
                departureModel.insertRow(departureModel.getRowCount(),new String[]{arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7]});
            }
        }catch (Exception e) {
            e.printStackTrace();}
        }
	private void loadData1(DefaultTableModel arrivalModel){
        String[] arr;
        String line;
        String flightpath = "C:\\Users\\maria\\eclipse-workspace\\FlightComponent\\src\\Flights.csv";
        try {
            BufferedReader fs = new BufferedReader(new FileReader(flightpath));
            while ((line = fs.readLine()) !=null) {
                arr = line.split(",");
                arrivalModel.insertRow(arrivalModel.getRowCount(),new String[]{arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7]});
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

}
}
