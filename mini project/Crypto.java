import javax.management.timer.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout; 
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.util.prefs.BackingStoreException; 

public class Crypto {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setTitle("Shield_Files");
        frame.setSize(1600,800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        displayWelcomeScreen(frame);


        //font
        Font font=new Font("Roboto",Font.BOLD,22);
        Font f=new Font("Serif",Font.BOLD,40);
        Font f2=new Font("Serif",Font.PLAIN,16);
        Font font2=new Font("Roboto",Font.BOLD,16); 

        //LOGO
        ImageIcon icon= new ImageIcon("logo.jpeg");
        frame.setIconImage(icon.getImage());

        //Header
        JPanel heading=new JPanel();
        heading.setBackground(new Color(0,0,0,80));
        heading.setBounds(0,0,1600,100);
        ImageIcon h_Image = new ImageIcon("log.jpg");
        Image img=h_Image.getImage();
        Image temp_img = img.getScaledInstance(65,72,Image.SCALE_AREA_AVERAGING);
        h_Image=new ImageIcon(temp_img);
        JLabel Head_Name = new JLabel("Encrypt-Decrypt File",h_Image,JLabel.LEFT);
        Head_Name.setForeground(Color.WHITE);
        Head_Name.setBounds(200, 60, 480, 60);
        Head_Name.setFont(f);
        heading.add(Head_Name);

        //BackGround
        ImageIcon background_image=new ImageIcon("Background.jpg");
         img=background_image.getImage();
         temp_img = img.getScaledInstance(1600, 1000, Image.SCALE_SMOOTH);
        background_image=new ImageIcon(temp_img);
        JLabel background = new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,1600,1000);

        //file handling And Key
        JPanel file_Handling=new JPanel();
        file_Handling.setLayout(null);
        file_Handling.setSize(500,350);
        file_Handling.setBackground(new Color(0,0,0,90));
        file_Handling.setBounds(500,150,600,230);

        //button
        JButton button=new JButton();
        button.setText("Upload file ");
        button.setBounds(215,40,150,50);
        button.setFont(font2);
        file_Handling.add(button);
        
        //encrypted_content
        JPanel TEXTAREA=new JPanel();
        TEXTAREA.setLayout(null);
        TEXTAREA.setSize(500,350);
        TEXTAREA.setBackground(new Color(0,0,0,90));
        TEXTAREA.setBounds(500,400,600,500);
        
        JTextArea textArea=new JTextArea();
        textArea.setBounds(0,0,600,350);
        textArea.setText("Here will be your Encrypted data");
        textArea.setFont(new Font("arial",Font.ITALIC,14));
        textArea.setLineWrap(true);
        TEXTAREA.add(textArea);

        //creating text field 
        ImageIcon key_img = new ImageIcon("key.jpeg");
        img=key_img.getImage();
        temp_img = img.getScaledInstance(40,40,Image.SCALE_AREA_AVERAGING);
        key_img=new ImageIcon(temp_img);
        JLabel EKey= new JLabel("Enter Secret Key:",key_img,JLabel.LEFT);
        EKey.setForeground(Color.white);
        EKey.setBounds(40, 112, 180, 40);
        EKey.setFont(f2);
        file_Handling.add(EKey);
        JTextField textField=new JTextField(10  );
        textField.setBounds(210, 110, 270,45);
        textField.setFont(font); 
        file_Handling.add(textField);
        button.addActionListener(e->{
            textArea.setText("Here will be your Encrypted file");
            System.out.println("button clicked");
            String text=textField.getText(); 
            int temp=Integer.parseInt(text); 
            operate(temp,textArea,textField);
        });
        

        //LayOut
        background.add(TEXTAREA);
        background.add(file_Handling);
        background.add(heading);
        frame.add(background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void operate(int key, JTextArea textArea, JTextField keyField) {
        JFileChooser fileChooser=new JFileChooser(); 
        fileChooser.showOpenDialog(null); 
        File file=fileChooser.getSelectedFile();

         //file FileInputStream 
         try {
            FileInputStream fis=new FileInputStream(file); 
            byte []data=new byte[fis.available()]; 
            fis.read(data); 
            int i=0;
            StringBuilder st=new StringBuilder();

            for(byte b:data) {
                System.out.println(b);
                data[i]=(byte)(b^key);
                st.append(data[i]+" ");
                i++; 
            }
            textArea.setText(st.toString());
            FileOutputStream fos=new FileOutputStream(file);
            keyField.setText("");
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done"); 
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }

    
    private static void displayWelcomeScreen(JFrame frame){
        final JWindow w=new JWindow(frame);
        w.setSize(1600, 800);
        w.setLocationRelativeTo(null);
        w.setVisible(true);

       
        JPanel panel=new JPanel();
       
        // String imagePath= "C:\\Users\\Abhishek\\Pictures\\ShieldApp\\welcomewindow.jpeg";
        // JLabel label= new JLabel(new ImageIcon(imagePath));
        // label.setHorizontalAlignment(SwingConstants.CENTER);
        // label.setBounds(0, 0, 100, 100);
        // panel.setBackground(Color.pink);
        // panel.setBorder(BorderFactory.createLineBorder(Color.black));
        // panel.add(label);
        // w.add(panel);

        // ImageIcon h_Image = new ImageIcon("");
        // Image img=h_Image.getImage();
        // Image temp_img = img.getScaledInstance(65,72,Image.SCALE_AREA_AVERAGING);
        // h_Image=new ImageIcon(temp_img);
        // JLabel label= new JLabel(new ImageIcon("WelcomeWindow.jpeg"));
        // panel.add(label);
        // w.add(panel);

        JProgressBar progress=new JProgressBar(0,100);
        progress.setForeground(Color.ORANGE);
        w.add(BorderLayout.PAGE_END,progress);
        
        int i=0;
        while(i<=100){
            progress.setValue(i);
            try{
                Thread.sleep(100);
            }catch(Exception e){  }

            
            if(i==100){
                w.dispose();
                //frame.setVisible(true);
                break;
            }else{
                i=i+10;
            }
        }
    }   
}
