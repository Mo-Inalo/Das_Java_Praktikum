package trafficcontrol;

import java.io.*;
import java.util.*;
import java.net.*;


class TrafficControl2
{
    public static void main(String... args) throws Exception
    {
        final Map<String, Integer> counts = new HashMap<String, Integer>();
        for(final String ip: args)
        {
            counts.put(ip, 0);
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        while(true)
                        {
                            Socket s = new Socket("localhost", Integer.parseInt(ip));
                            InputStream is = s.getInputStream();
                            while(true)
                            {
                                int ignored = is.read();
                                synchronized(counts)
                                {
                                    counts.put(ip, counts.get(ip) + 1);
                                }
                                new Thread()
                                {
                                    public void run()
                                    {
                                        try
                                        {
                                            Thread.sleep(60000);
                                            synchronized(counts)
                                            {
                                                counts.put(ip, counts.get(ip) - 1);
                                            }
                                        }
                                        catch(Exception ex)
                                        {}
                                    }
                                }
                                .start();
                            }
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }

                }
            }
            .start();
        }

        while(true)
        {
            for(String ip: args)
                synchronized(counts)
                {
                    if(counts.get(ip) >= 60)
                        System.out.println(ip + ": heavy traffic");
                }
            Thread.sleep(1000);
        }
    }
}
