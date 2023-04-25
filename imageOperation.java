import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class imageOperation{
    public static void operate(int key){

        //Just simply XOR all the bytes of file with key (Encryption) / ViceVersa for Decryption.
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        //file input stream
        try {
            FileInputStream fis = new FileInputStream(file);

            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for(byte b:data){
                System.out.println(b);
                data[i] = (byte)(b^key);        //XOR
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        JFrame f = new JFrame();  //creates a GUI window/frame to add components
        f.setTitle("Image Operation");  //sets title of frame
        f.setSize(400,400);         //sets x-dimension,y-dimension of frame
        f.setLocationRelativeTo(null);         
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //exit out of application(means jab apan close window pr click krege tab program bhi stop ho jayega)


        ImageIcon image = new ImageIcon("encryption-decryption logo.png ");         //create an ImageIcon
        f.setIconImage(image.getImage());       //change icon of a frame

        //font
        Font font = new Font("Roboto", Font.BOLD, 25);

        //creating button
        JButton button = new JButton();
        button.setBounds(100, 200, 360, 60);
        button.setText("Open Image");
        button.setFocusable(false);
        button.setFont(font);
        
        //creating text field
        JTextField textField = new JTextField(10);
        textField.setFont(font);
        button.addActionListener(e->{
            String text = textField.getText();
            int temp = Integer.parseInt(text);
            operate(temp);
        });

        f.setLayout(new FlowLayout());

        f.add(button);
        f.setResizable(false);      //prevent frame from being resized(window ki size change hi nhi kr payege)
        f.add(textField);
        f.setVisible(true);     //make frame visible
    }
}