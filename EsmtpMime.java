//DTUs mailserver:  dtu-dk.mail.protection.outlook.com.   130.125.82.3

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class EsmtpMime {

    public static void main(String[] args) {

        String host = "dtu-dk.mail.protection.outlook.com.";  //DTU´s mail server
    	//String host = "localhost";  //DTU´s mail server

        int portNumber = 25;  //Standard SMTP port
        String CRLF = "\r\n"; //Standard way to tell that the it is end of line.


        try {

            Socket socket = new Socket(host, portNumber);       //Establish connection to server

            System.out.println("Connection established..");

            InputStream is = socket.getInputStream();  //Get input from socket
            InputStreamReader reader = new InputStreamReader(is);  //Read from the socket
            BufferedReader br = new BufferedReader(reader);

            //Read response from server
            String serverResponse = br.readLine();
            System.out.println(serverResponse);

            if (!serverResponse.startsWith("220"))
            {
                throw new Exception("220 reply not received from the server");
            }

            OutputStream os = socket.getOutputStream(); //Get the sockets output stream.

            String command = "HELO "+ CRLF;
            System.out.println(command);
            os.write(command.getBytes(StandardCharsets.US_ASCII));
            serverResponse = br.readLine();
            System.out.println(serverResponse);

            if (!serverResponse.startsWith("250"))
            {
                throw new Exception("250 reply not received from the server");
            }

            //Send the mail from command

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the email address that you wish to send an email from: ");
            //String sourceEmail = scan.next();
            String sourceEmail = "s190536@student.dk";
            String mailFromCommand = "MAIL FROM: " + sourceEmail + CRLF;
            System.out.println(mailFromCommand);
            os.write(mailFromCommand.getBytes(StandardCharsets.US_ASCII));
            serverResponse = br.readLine();
            System.out.println(serverResponse);

            if (!serverResponse.startsWith("250")){
                throw new Exception("250 reply not received.");
            }

            //Send RCPT TO command:

            //System.out.println("Enter the email address that you wish to send an email to: ");
            //String sendTo = new String();
            //sendTo = scan.next();
            String sendTo = "s190536@student.dtu.dk";
            String rctpCommand = "RCPT TO: " + sendTo + CRLF;
            System.out.println(rctpCommand);
            os.write(rctpCommand.getBytes(StandardCharsets.US_ASCII));

            serverResponse = br.readLine();
            System.out.println(serverResponse);

            if (!serverResponse.startsWith("250")){
                throw new Exception("250 reply not received");
            }


            //Send DATA command:

            String dataString;
            dataString = "DATA "+CRLF;
            System.out.println(dataString);
            os.write(dataString.getBytes(StandardCharsets.US_ASCII));
            serverResponse = br.readLine();
            System.out.println(serverResponse);
            if (!serverResponse.startsWith("354")){
                throw new Exception("354 reply not received");
            }



            //Send subject command:

            /*
            String subjectString = "";
            System.out.println("Enter subject: ");
            subjectString = scan.nextLine();
            
            String subjectString = "Subject:Attachment Test MIME-Version: 1.0 Content-" + CRLF;
            subjectString = "subject: " + subjectString + CRLF+CRLF;
            os.write(subjectString.getBytes(StandardCharsets.US_ASCII));
            os.flush();
           
           */


            //Attach picture
             //ImageConverter abc = new ImageConverter();
            
            
      
            
            /*

            os.write(mail.getBytes(StandardCharsets.US_ASCII));
            serverResponse = br.readLine();
            System.out.println(serverResponse);

            if (!serverResponse.startsWith("250")){
                throw new Exception("250 reply not received");
            }
            else{
                System.out.println("Email to " + sendTo + " successfully send");
            }
            */
            
             String mail = "Dette er min hardcodet mail";
             String image = "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";
      
                 
             String type = "Subject: Attachment Test " + CRLF
                     +"MIME-Version: 1.0" + CRLF
                     +"Content-type: multipart/alternative; boundary=KkK170891tpbkKk__FV_KKKkkkjjwq" +CRLF + CRLF
                     +"--KkK170891tpbkKk__FV_KKKkkkjjwq" + CRLF
                     +"Content-type: text/plain" + CRLF + CRLF
                     + mail + CRLF
                     +"--KkK170891tpbkKk__FV_KKKkkkjjwq" +CRLF
                     +"Content-Type: application/octet-stream;name=picture.jpg" + CRLF
                     +"Content-Transfer-Encoding:base64" + CRLF 
                     + "Content-Disposition:attachment;filename=picture.jpg" + CRLF + CRLF
                     + image  + CRLF + CRLF
                     +"--KkK170891tpbkKk__FV_KKKkkkjjwq--"  + CRLF
                     
                     + CRLF + "." + CRLF;
            
                                             
             
             //System.out.println(type);
                             
             os.write(type.getBytes(StandardCharsets.US_ASCII));
             
             
             
             /*
             //send messages
             System.out.println("Enter messages, end with '.'\n");
             String mail = "";
             char ch;

             do{

                 mail = scan.nextLine()+CRLF;
                 ch = mail.charAt(0);
                 os.write(mail.getBytes(StandardCharsets.US_ASCII));

             }
             while(ch != '.');
             os.write(CRLF.getBytes(StandardCharsets.US_ASCII));
             
             System.out.println(mail);
             
             
*/
        


            //Quit command
            String quitCommand = "QUIT";
            System.out.println("Terminating...");
            os.write(quitCommand.getBytes(StandardCharsets.US_ASCII));
            System.out.println("Termination successful.");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

