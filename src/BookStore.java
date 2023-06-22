import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.mysql.cj.xdevapi.Table;

public class BookStore {public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			
		String dbname = "BookStore";
		String url = "jdbc:mysql://localhost:3306/"+dbname;
		String uname = "root";
		String pass = "abc123";
		

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Registered");
		
		Connection con = DriverManager.getConnection(url,uname,pass);
		System.out.println("Connected");
		
		JFrame jf = new JFrame("Employee Entry");
		JLabel title = new JLabel("*****Book_Shop*****");
		title.setBounds(150, 0, 531, 50);
		title.setFont(new Font("Vivaldi", Font.BOLD, 30));
		

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null,"Registration",TitledBorder.LEADING, TitledBorder.TOP,null,null));
		panel_1.setBounds(42,74,394,230);
		panel_1.setLayout(null);
		panel_1.setVisible(true);
		
			

		JLabel id = new JLabel("Enter Id");
		id.setBounds(30, 70, 117, 35);
		JTextField tx0 = new JTextField();
		tx0.setBounds(100, 75, 150, 30);

		JLabel name = new JLabel("Book Name ");
		name.setBounds(30, 105, 117, 35);
		JTextField tx1 = new JTextField();
		tx1.setBounds(100, 110, 150, 30);
		
		JLabel edition = new JLabel("Edition ");
		edition.setBounds(30, 140, 117, 35);
		JTextField tx2 = new JTextField();
		tx2.setBounds(100, 145, 150, 30);

		JLabel price= new JLabel("Price ");
		price.setBounds(30, 175, 117, 35);
		JTextField tx3 = new JTextField();
		tx3.setBounds(100, 180, 150, 30);

		JLabel sname= new JLabel("Enter id ");
		sname.setBounds(30, 350, 117, 35);
		JTextField tx4 = new JTextField();
		tx4.setBounds(100, 355, 150, 30);
		

		JButton save = new JButton("Save");
		save.setBounds(30, 220, 80, 40);
		panel_1.add(save);

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id= Integer.parseInt(tx0.getText());
				String name = tx1.getText();
				int edition= Integer.parseInt(tx2.getText());
				int price= Integer.parseInt(tx3.getText());
				try {
					PreparedStatement st = con.prepareStatement("insert into BookStore.tb0 values(?,?,?,?)");
					st.setInt(1, id);
					st.setString(2, name);
					st.setInt(3, edition);
					st.setInt(4, price);
					
					//Table.setModel();
					int a = st.executeUpdate();
					if(a>0)
					{
						System.out.println("Entere Properly");
						JOptionPane.showMessageDialog(null, "Record Added");
					}
					else{
						System.out.println("Enter not Properly");
					}
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		});

		
		
		
		JButton exit = new JButton("Exit");
		exit.setBounds(120, 220, 80, 40);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jf.dispose();
			}
		});
		
		
		JButton clear = new JButton("Clear");
		clear.setBounds(210, 220, 80, 40);
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tx0.setText(null);
				tx1.setText(null);
				tx2.setText(null);
				tx3.setText(null);
			}
		});
		
		
		JButton search = new JButton("Search");
		search.setBounds(30, 400, 150, 40);

		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lb002.setText("Data Search");
				int id= Integer.parseInt(tx4.getText());
				 try {
					PreparedStatement st = con.prepareStatement("select * from BookStore.tb0 where id = ?");
					System.out.println("statement Created");
					st.setInt(1, id);
					//st.setString(,edition);
					ResultSet r1 = null;
					r1 = st.executeQuery();
					while(r1.next())
					{
						System.out.println("searched");
						tx0.setText(""+r1.getInt("id")+"");
						tx1.setText(""+r1.getString("name")+"");
						tx2.setText(""+r1.getInt("edition")+"");
						tx3.setText(""+r1.getInt("price")+"");  
						if(tx1==null)
						{
							JOptionPane.showMessageDialog(null, "Record Added");
						}
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Record null");
					e.printStackTrace();
				}
				 
			}
		});

		
		
		
		JButton update = new JButton("Update");
		update.setBounds(350, 400, 150, 40);

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(tx0.getText());
				int id1 = Integer.parseInt(tx4.getText());
				String name = tx1.getText();
				int edition = Integer.parseInt(tx2.getText());
				int price = Integer.parseInt(tx3.getText());
				try {
					PreparedStatement st = con.prepareStatement("update  BookStore.tb0 set id = ? ,name=?,edition=?, price = ? where id = ?");
					System.out.println("statement Created");
					st.setInt(1, id);
					st.setString(2, name);
					st.setInt(3, edition);
					st.setInt(4, price);
					st.setInt(5, id1);
					int a2 = st.executeUpdate();
	
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		});
		
		
	
		
		
		
		
		
		JButton delete = new JButton("Delete");
		delete.setBounds(510, 400, 150, 40);

		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lb00.setText("Data Deleted");
				 int id = Integer.parseInt(tx4.getText());
				 try {
					PreparedStatement st = con.prepareStatement("delete from BookStore.tb0 where id= ?");
					System.out.println("statement Created");
					st.setInt(1, id);
					int a = st.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
		});
	


		

		JButton Show = new JButton("Show");
		Show.setBounds(510, 215, 150, 40);

		Show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Show");	

DefaultListModel model = new DefaultListModel();
Statement s = null;

try {
s = con.createStatement();
String sql = "SELECT * FROM  BookStore.tb0";

ResultSet rs = s.executeQuery(sql);
// int row = 0;
while((rs!=null) && (rs.next()))
{
	model.addElement(rs.getInt("id") + " - " + rs.getString("name")+ " - " + rs.getInt("edition")+ " - " + rs.getInt("price"));
}

rs.close();

} catch (Exception e) {
// TODO Auto-generated catch block
//JOptionPane.showMessageDialog(null, e.getMessage());
e.printStackTrace();
}

try {
if(s != null) {
s.close();
//con.close();
}
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}



   
// Label Result
final JLabel lblResult = new JLabel();
lblResult.setHorizontalAlignment(SwingConstants.CENTER);
lblResult.setBounds(440, 354, 272, 14);
jf.getContentPane().add(lblResult);
   
   // Scroll Pane
   JScrollPane scrollPane = new JScrollPane();
   
   final JList list = new JList(model);
   list.addMouseListener(new MouseAdapter() {
       public void mouseClicked(MouseEvent evt) {
           lblResult.setText(list.getSelectedValue().toString());
       }
   });
  
   

   
//jf.setContentPane(new JLabel(new ImageIcon("C:/Users/nitis/Downloads/BookShop.jpg")));
   scrollPane.setViewportView(list);
   scrollPane.setBounds(440, 70, 200, 130);
   jf.getContentPane().add(scrollPane);
   jf.setSize(700, 500);
   jf.setLayout(null);
   jf .setVisible(true);

	
			}
		} );		

		
		

		

		
		jf.setSize(700, 500);
		jf.setLayout(null);
		jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(title);
		jf.add(id);
		jf.add(tx0);
		jf.add(name);
		jf.add(tx1);
		jf.add(edition);
		jf.add(tx2);
		jf.add(price);
		jf.add(tx3);
		jf.add(sname);
		jf.add(tx4);
		jf.add(save);
		jf.add(clear);
		jf.add(exit);
		jf.add(search);
		jf.add(update);
		jf.add(delete);
		jf.add(Show);
	}

}