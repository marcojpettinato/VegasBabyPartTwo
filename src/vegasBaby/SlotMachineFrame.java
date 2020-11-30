package vegasBaby;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SlotMachineFrame extends JFrame 
{
	private TilePanel tpan;
	private JTextField txtBalance;
	private JButton btnMax, btnMid, btnMin;
	private static double balance = 5.00;
	private double wager = 0.00;
	
	private Tile tileA = new Tile(0,0);
	private ArrayList <Tile> checker = new ArrayList <Tile>(Arrays.asList(tileA,tileA,tileA,tileA));
	
	/**
	 * @param this function takes an integer value to represent the desired width of the frame.
	 * @param this function takes an integer value to represent the desired height of the frame.
	 * this function takes the input values and creates a frame that size centered in the screen of the machine using it.
	 */
	public void centerFrame(int width, int height)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenDim = tk.getScreenSize();
		int screenWidth = (int) screenDim.getWidth();
		int screenHeight = (int) screenDim.getHeight();
		
		int left = (screenWidth - width)/2;
		int top = (screenHeight - height)/2;
		setBounds(left,top,width,height);
	}
	
	/**
	 * this function sets of the contents and takes in the users input and displays a pumpkin on the running of the program
	 */
	public void setupMenu() 
	{
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem miLoad = new JMenuItem("Load");
		JMenuItem miSave = new JMenuItem("Save");
		JMenuItem miPrint = new JMenuItem("Print");
		JMenuItem miRestart = new JMenuItem("Restart");
		JMenuItem miExit = new JMenuItem("Exit");
		
		//Action Listener for loading
		miLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser jfc = new JFileChooser();
				TileReader tr = new TileReader();
				ArrayList<Tile> tiles;
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					tiles = tr.read(jfc.getSelectedFile());
					if (tiles != null) 
					{
						tpan.setTiles(tiles);
						repaint();
					} 
					else 
					{
						JOptionPane.showMessageDialog(null,"Tiles could not be read.");
					}
				}
			}
		});
		
		//Action Listener for saving
		miSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser jfc = new JFileChooser();
				TileWriter tw = new TileWriter();
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					if (tw.write(jfc.getSelectedFile(),tpan.getTiles())) 
					{
						JOptionPane.showMessageDialog(null,"Wrote tiles successfully.");
					} 
					else 
					{
						JOptionPane.showMessageDialog(null,"Could not write tiles.");
					}
				}
			}
		});
		
		miPrint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(int i =0; i < TilePanel.tiles.size(); i++)
				{
					System.out.println(TilePanel.tiles.get(i).toStringFancy());
					
				}
			}
		});
		
		miRestart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SlotMachineApp.frm.setVisible(false);
				
				//TilePanel.tiles = TileRandomizer.TileRandomizer(TilePanel.tiles,TilePanel.colors,TilePanel.shapes);
				SlotMachineFrame from = new SlotMachineFrame();
				from.setVisible(true);
			}
		});
		
		miExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		
		mnuFile.add(miLoad);
		mnuFile.add(miSave);
		mnuFile.add(miPrint);
		mnuFile.add(miRestart);
		mnuFile.add(miExit);
		mbar.add(mnuFile);
		JMenu mnuHelp = new JMenu("Help");
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null, "My Name: Marco Pettinato. Github Link: https://github.com/marcojpettinato/VegasBabyPartTwo.git");
			}
		});
		mnuHelp.add(miAbout);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);
	}
	
	/**
	 * this function sets of the contents and takes in the users input and displays a pumpkin on the running of the program
	 * It also allows for the buttons to be clicked on to wager money, and said buttons are disabled when there
	 * is no money to wager
	 */
	public void setupLook() 
	{
		centerFrame(1025,500);
		//setBounds(100,100,750,300);
		setTitle("Vegas Baby Vegas Slot Machine");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		
		btnMax = new JButton("Max");		
		btnMid = new JButton("Mid");
		btnMin = new JButton("Min");
		panSouth.add(btnMax);
		panSouth.add(btnMid);
		panSouth.add(btnMin);
		c.add(panSouth,BorderLayout.SOUTH);
		tpan = new TilePanel();
		c.add(tpan,BorderLayout.CENTER);
		JLabel lblBalance = new JLabel("$");
		panSouth.add(lblBalance);
		txtBalance = new JTextField(6);
		txtBalance.setEditable(false);
		txtBalance.setText(String.format("%.2f",balance));
		panSouth.add(txtBalance);
		btnMax.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					TileRandomizer.tileRandomizer(TilePanel.tiles);	
					int multID = TileChecker.TileChecker(TilePanel.tiles,checker);
					wager = balance * 1;
					if(multID == 2)
					{
						wager = (wager*100);
					}
					
					else if(multID == 1)
					{
						wager = (wager*25);
					}
					
					else if(multID == 0)
					{
						wager = -wager;
					}
					
					balance += wager;
					txtBalance.setText(String.valueOf(balance));
					if(balance == 0.00)
					{
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					}
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"The window dimmensions must be integers.");
				}
				repaint(); 
			}
		}	
		);
		btnMid.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					TileRandomizer.tileRandomizer(TilePanel.tiles);
					int multID = TileChecker.TileChecker(TilePanel.tiles,checker);
					wager = balance * 0.5;
					if(multID == 2)
					{
						wager = (wager*50);
					}
					
					else if(multID == 1)
					{
						wager = (wager*10);
					}
					
					else if(multID == 0)
					{
						wager = -wager;
					}
					
					balance += wager;
					txtBalance.setText(String.valueOf(balance));
					if(balance == 0.00)
					{
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					}
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"The window dimmensions must be integers.");
				}
				repaint(); 
			}
		}	
		);
		btnMin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					TileRandomizer.tileRandomizer(TilePanel.tiles);
					int multID = TileChecker.TileChecker(TilePanel.tiles,checker);
					wager = balance * 0.1;
					if(multID == 2)
					{
						wager = (wager*10);
					}
					
					else if(multID == 1)
					{
						wager = (wager*5);
					}
					
					else if(multID == 0)
					{
						wager = -wager;
					}
					
					balance += wager;
					txtBalance.setText(String.valueOf(balance));
					if(balance == 0.00)
					{
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					}
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"The window dimmensions must be integers.");
				}
				repaint(); 
			}
		}	
		);
		setupMenu();
	}
	
	//public void spin()
	//{
	//	TilePanel.spin();
	//}
	
	/**
	 * this creates the frame for the slot machine
	 */
	public SlotMachineFrame()
	{
		setupLook();
	}
}
