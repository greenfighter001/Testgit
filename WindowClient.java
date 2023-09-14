package lifeGame;
/*
 * �������ڴ��ڽ����ʵ�֡�
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WindowClient extends JFrame{
	private static final long serialVersionUID = 1L;
	//�������ܼ�
	private JButton clean_button;
	private JButton kill_button;
	private JButton add_button;
	private JButton start_button;
	private JButton pause_button;
	private JButton random_button;
	private static JPanel button_panel;
	private final Map map;
	private JSlider speed_bar;
	
	private static final int BUTTON_WIDTH=50;
	private static final int BUTTON_HEIGHT=30;
	
	public WindowClient(){
		map = new Map(40,40);
		map.setLayout(null);
        map.setBounds(7, 10, 40*15, 40*15);
        map.setBackground(Color.WHITE);
        map.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(map.is_add){
					int x=e.getX();
					int y=e.getY();
					Map.pause_map[(int)((y-y%15)/15)][(int)((x-x%15)/15)]=1;
					map.setAdd();
				}else if(map.is_kill&&e.getX()<40*15&&e.getY()<40*15){
					int x=e.getX();
					int y=e.getY();
					Map.pause_map[(int)((y-y%15)/15)][(int)((x-x%15)/15)]=0;
					map.setAdd();
					}	
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		    @Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
        });
        
        button_panel= new JPanel();
		button_panel.setLayout(null);
        button_panel.setBounds(0, 620, 620, 90);
        button_panel=control(button_panel);   
		add(map);	
		add(button_panel);
		new Thread(map).start();
	}
	/*
	 * control()����ʵ�ֶ԰�ť�����úͼ���
	 */
	public JPanel control(JPanel c){
        JPanel control=c;
		random_button=new JButton("Random");
		random_button.setFont(new Font("����", Font.BOLD, 10));
		random_button.setMargin(new Insets(0,0,0,0));
		random_button.addActionListener(this.new RandomActionListener());
		random_button.setBounds(20, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		add_button=new JButton("Add");
		add_button.setFont(new Font("����", Font.BOLD, 10));
		add_button.setMargin(new Insets(0,0,0,0));
		add_button.addActionListener(this.new AddActionListener());
		add_button.setBounds(90, 10,BUTTON_WIDTH, BUTTON_HEIGHT);
		
		kill_button=new JButton("Kill");
		kill_button.setMargin(new Insets(0,0,0,0));
		kill_button.setFont(new Font("����", Font.BOLD, 10));
		kill_button.addActionListener(this.new KillActionListener());
		kill_button.setBounds(160, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		start_button=new JButton("Start");
		start_button.setFont(new Font("����", Font.BOLD, 10));
		start_button.setMargin(new Insets(0,0,0,0));
		start_button.addActionListener(this.new StartActionListener());
		start_button.setBounds(230, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		pause_button=new JButton("Pause");
		pause_button.setFont(new Font("����", Font.BOLD, 10));
		pause_button.setMargin(new Insets(0,0,0,0));
		pause_button.addActionListener(this.new PauseActionListener());
		pause_button.setBounds(300, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		clean_button=new JButton("Clear");
		clean_button.setFont(new Font("����", Font.BOLD, 10));
		clean_button.setMargin(new Insets(0,0,0,0));
		clean_button.addActionListener(this.new ClearActionListener());
		clean_button.setBounds(370, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		random_button.setBackground(Color.white);
	    add_button.setBackground(Color.white);
	    clean_button.setBackground(Color.white);
		pause_button.setBackground(Color.white);
		kill_button.setBackground(Color.white);
		start_button.setBackground(Color.white);
		add_button.setBackground(Color.white);
		random_button.setBackground(Color.white);
		
		speed_bar=new JSlider();
		//���û������ͣ�ڿ̶ȴ�  
        speed_bar.setSnapToTicks(true);  
        //���û��ƿ̶�  
        speed_bar.setPaintTicks(true);  
        //���������ο̶ȵļ�� 
        speed_bar.setMaximum(8);
        speed_bar.setMinimum(1);
        speed_bar.setValue(1);
        speed_bar.addChangeListener(new SpeedbarActionListener());
        speed_bar.setMinorTickSpacing(1);  
        speed_bar.setPreferredSize(new Dimension(400, 40));
        speed_bar.setBounds(440,10,150,30);
        speed_bar.setOpaque(true );
	   		
		control.add(speed_bar);
		control.add(clean_button);
		control.add(pause_button);
		control.add(start_button);
		control.add(random_button);
		control.add(kill_button);
		control.add(add_button);
		control.setBorder(BorderFactory.createRaisedBevelBorder());//��͹�߿�
		return control;
	}
	
	/*
	 * RandomActionListenerʵ�ֶ�random_button�ļ���
	 */
	class RandomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			map.is_add=false;
			map.is_kill=false;
			map.setRandom();
		
			random_button.setBackground(Color.green);
		    add_button.setBackground(Color.white);
		    clean_button.setBackground(Color.white);
			pause_button.setBackground(Color.white);
			kill_button.setBackground(Color.white);
			start_button.setBackground(Color.white);
			add_button.setBackground(Color.white);			
		}
	}
	/*
	 * StartActionListenerʵ�ֶ�start_button�ļ���
	 */
	class StartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		    map.setBackground(Color.white);
			map.is_add=false;
			map.is_kill=false;
			map.setMap();
			
			random_button.setBackground(Color.white);
		    add_button.setBackground(Color.white);
		    clean_button.setBackground(Color.white);
			pause_button.setBackground(Color.white);
			kill_button.setBackground(Color.white);
			start_button.setBackground(Color.green);
			add_button.setBackground(Color.white);
			random_button.setBackground(Color.white);
		}
	}
	/*
	 * ClearActionListenerʵ�ֶ�clear_button�ļ���
	 */
	class ClearActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			map.setBackground(Color.WHITE);
			map.is_add=false;
			map.is_kill=false;
			map.setClear();
			
			random_button.setBackground(Color.white);
		    add_button.setBackground(Color.white);
		    clean_button.setBackground(Color.green);
			pause_button.setBackground(Color.white);
			kill_button.setBackground(Color.white);
			start_button.setBackground(Color.white);
			add_button.setBackground(Color.white);
			random_button.setBackground(Color.white);
		}
	}
	/*
	 * PauseActionListenerʵ�ֶ�pause_button�ļ���
	 */
	class PauseActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			map.setPause();
			map.is_add=false;
			map.is_kill=false;
			
			random_button.setBackground(Color.white);
		    add_button.setBackground(Color.white);
		    clean_button.setBackground(Color.white);
			pause_button.setBackground(Color.green);
			kill_button.setBackground(Color.white);
			start_button.setBackground(Color.white);
			add_button.setBackground(Color.white);
			random_button.setBackground(Color.white);
		}
	}
	/*
	 * SpeedbarActionListenerʵ�ֶ�speed_bar�ļ���
	 */
	class SpeedbarActionListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent arg0) {
			map.getSpeed(9-speed_bar.getValue());		
		}
	}
	/*
	 * KillActionListenerʵ�ֶ�kill_button�ļ���
	 */
	class KillActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			map.setPause();
			map.is_kill=true;
			map.is_add=false;
			
			random_button.setBackground(Color.white);
		    add_button.setBackground(Color.white);
		    clean_button.setBackground(Color.white);
			pause_button.setBackground(Color.white);
			kill_button.setBackground(Color.green);
			start_button.setBackground(Color.white);
			add_button.setBackground(Color.white);
			random_button.setBackground(Color.white);
		}
	}
	/*
	 * AddActionListenerʵ�ֶ�add_button�ļ���
	 */
	class AddActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			map.setPause();
			map.is_add=true;
			map.is_kill=false;
			
			random_button.setBackground(Color.white);
		    add_button.setBackground(Color.white);
		    clean_button.setBackground(Color.white);
			pause_button.setBackground(Color.white);
			kill_button.setBackground(Color.white);
			start_button.setBackground(Color.white);
			add_button.setBackground(Color.green);
			random_button.setBackground(Color.white);		
		}
	}
	/*
	 * �������
	 */
	public static void main(String[] args) 
	{
		WindowClient window_client=new WindowClient();
		window_client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window_client.setSize(620,700);
		window_client.setLayout(null);
		window_client.setVisible(true);
		window_client.setResizable(false);
		window_client.setTitle("Game of Life");
		window_client.setLocationRelativeTo(null);
	}
	
}


	

