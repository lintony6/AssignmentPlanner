import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class TonyApp {
	DefaultListModel dlm = new DefaultListModel();
	static Assignment hw = new Assignment();
	private JFrame frmTony;
	private JTextField textField;
	private JLabel lblNewDay;
	private JButton btnNewDay;
	String text="";
	private JPanel panel_1;
	private JLabel lblEnterTheDay;
	private JButton btnRemove;
	private JList list;

	public void setText() {
	dlm.removeAllElements();
	for(int i = 0; i<hw.size();++i) {
		text=hw.getHw(i);
		dlm.addElement(text);
	}
	list.setModel(dlm);
			}
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try{
			FileInputStream fis = new FileInputStream("assignmentdates.txt");
			Scanner fin = new Scanner(fis);
			while(fin.hasNext()) {
				String line = fin.nextLine();
				hw.addHw(line);
		}
			hw.sortArray();
		}
		catch(FileNotFoundException fnf) {
			System.out.println("assignmentdates.txt file not found.");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TonyApp window = new TonyApp();
					window.frmTony.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public TonyApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTony = new JFrame();
		frmTony.setTitle("Tony's Assignment Planner");
		frmTony.setBounds(100, 100, 468, 541);
		frmTony.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		lblNewDay = new JLabel("Enter the day and name (ex:4  chemlab)");
		panel.add(lblNewDay);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
	
		btnNewDay = new JButton("Add");
		
		panel.add(btnNewDay);
		
		panel_1 = new JPanel();
		
		lblEnterTheDay = new JLabel("Remove assignment");
		panel_1.add(lblEnterTheDay);
		
		btnRemove = new JButton("Remove");
		
		panel_1.add(btnRemove);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Arial", Font.BOLD, 11));
		list.setVisibleRowCount(50);
		list.setValueIsAdjusting(true);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
					hw.removeDate(hw.getFirstNum(hw.getHw(index)));
					setText();
				}
				
			
		});
		GroupLayout groupLayout = new GroupLayout(frmTony.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(128)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		frmTony.getContentPane().setLayout(groupLayout);
		setText();
		btnNewDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				text = textField.getText(); 
				File f = new File("assignmentdates.txt");
				FileWriter fw = new FileWriter(f,true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(text);
				pw.close();
				hw.addHw(text);
				hw.sortArray();;
				setText();
				}
				catch(Exception e1) {
					
				}
			}
		});
	}
}
