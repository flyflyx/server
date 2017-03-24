import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 24.03.2017.
 */
public class socket {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
        while (true)
        {
            Socket s = ss.accept();
            Runnable action=new Runnable() {
                public void run() {
                    handle(s);
                }
            };
        }
    }

    static void handle (Socket s)
    {
        try {
            try{
                BufferedReader rdr = new BufferedReader(new InputStreamReader(s.getInputStream(),"us-ascii"));
                Pattern p= Pattern.compile("get\\s+(\\s+)");
                while (true)
                {
                    String line=rdr.readLine();
                    if(line==null||line.isEmpty())
                        break;
                    Matcher m=p.matcher(line);
                    if (m.find())
                    {
                        String resource=m.group(1);
                        System.out.println(resource);
                    }
                    System.out.println(">>"+line);
                }
                OutputStream os=s.getOutputStream();

                os.write("qq".getBytes("us-ascii"));
            }finally {
                s.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
