package code.pos;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) {
    	new CashRegister();
        try {
            //connection number
            int i = 1;
            //establish server socket
            ServerSocket s = new ServerSocket(8080);

            while (true) {
                //wait for client connection
                Socket socket = s.accept();
                System.out.println("WebServer running on port: " + s.getLocalPort());
                System.out.println("The connection number is: " + i);
                Runnable r = new ThreadHandler(socket);
                Thread t = new Thread(r);
                t.start();
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/**
 * This class handles the client input for one server socket
 */
class ThreadHandler implements Runnable {
    private Socket incomig;

    public ThreadHandler(Socket i) {
        incomig = i;
    }

    public void run() {
        try {
            try {
                InputStream is = incomig.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = incomig.getOutputStream();
                Scanner in = new Scanner(is);
                PrintWriter out = new PrintWriter(os, true);
                
              

               /*while (true)*/ {
                    String line = br.readLine();
                    if (line.equals("\r\n") && line.equals("")) {
                        //break;
                    }
                    System.out.println("the client request is :" + line);
                    //read request line
                    String[] requests = line.split(" ");
                    //read request head
                    //read request body
                    if (requests[0].equals("GET")) {
                        //response GET request
                        //respense with the file that the request wants
                    	System.out.println("request is GET");
                        String path = System.getProperties().getProperty("user.dir") + requests[1].replaceAll("\\/", "//");
                        System.out.println("The file that the client wants is:" + path);
                        doGet(path, out,requests[1]);
                    }                             
                }

            } finally {
                incomig.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doGet(String requestPath, PrintWriter out, String filename) {
        
        final String CRLF = "\r\n";
        String statusLine = null;
        String date = new Date().toString();
        String contentTypeLine = "";
        String contentLengthLine = "";
        String entityBody = "";
        String Filename= filename;
        File f = new File(requestPath);
        long lm= f.lastModified();
        long[] index1=null;
        long[] index2=null;

        Filename=Filename.replaceAll("/","");
        if(!Filename.equals("index3.html")) {
        File requestFile = new File(requestPath);
        System.out.println(requestPath);
        
        System.out.println(Filename);
        Path path = Paths.get(requestPath);
        
        if (requestFile.exists()) {
        	
            statusLine = "HTTP/1.0 200 OK";
            contentLengthLine = String.valueOf(requestFile.length());
            try {
                contentTypeLine = Files.probeContentType(path);
                List<String> contents = Files.readAllLines(path);
                for (String line : contents) {
                    entityBody += line + CRLF;
                }
            } catch (IOException e) {
                statusLine = "HTTP/1.0 400 BadRequest";
                entityBody = "<HTML>400 Not BadRequest</TITLE></HEAD>" +
                        "<BODY>400 BadRequest" +
                        e.getMessage();
                e.printStackTrace();
            }
        } else {
            statusLine = "HTTP/1.0 404 Not Found";
            entityBody = "<HTML>404 Not Found</TITLE></HEAD>" +
                    "<BODY>404 Not Found";

        }
        }else  {
            statusLine = "HTTP/1.0 403 Forbidden";
            entityBody = "<HTML>403 Forbidden</TITLE></HEAD>" +
                    "<BODY>403 Forbidden";

        }
        out.print(statusLine + CRLF);
        System.out.println(statusLine + CRLF);
        out.print("Date: " + date + CRLF);
        out.print("Content-Type: " + contentTypeLine + CRLF);
        out.print("Content-Length: " + contentLengthLine + CRLF);
        out.print(CRLF);
        out.print(entityBody + CRLF);
        out.flush();
        System.out.println("over");
    }
}