package petrenko;

/**
 * Created by grpetr189853 on 14.02.2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatNew {
    public static class Server {
        private ServerSocket s;
        private int incoming;
        public List<ThreadedServer> connections =
                Collections.synchronizedList(new ArrayList<ThreadedServer>());

        public Server(ServerSocket s) {
            this.s = s;
            int i = 1;
            try {
//                s=new ServerSocket(8189);
                while (true) {
                    Socket incoming = s.accept();

                    System.out.println("Пришел клиент №" + i);

                    ThreadedServer r = new ThreadedServer(incoming);//Runnable
                    connections.add(r);
                    Thread th = new Thread(r);
                    th.start();
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public class ThreadedServer implements Runnable {
            private Socket incoming;
            BufferedReader in;
            PrintWriter out;
            String name;
            public ThreadedServer(Socket incoming) {
                this.incoming = incoming;
            }

            public void run() {
                try {
                    try {
                        in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
                        out = new PrintWriter(incoming.getOutputStream(), true);
                        name = in.readLine();

                        synchronized (connections) {
                            Iterator<ThreadedServer> iter = connections.iterator();
                            while (iter.hasNext()) {
                                ((ThreadedServer) iter.next()).out.println(name+" пришел");
                            }
                        }
                        out.println("Введите Bye для выхода.");
                        boolean done = false;
                        while (!done) {
                            String line = in.readLine();
                            //out.println(line);
                            synchronized(connections) {
                                Iterator<ThreadedServer> iter = connections.iterator();
                                while(iter.hasNext()) {
                                    ((ThreadedServer) iter.next()).out.println(name+":"+line);
                                }
                            }
                            if (line.trim().equals("Bye")) {
                                done = true;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    } finally {
                        incoming.close();
                    }
                }catch(Exception e){}

            }
        }

    }
    public static class Client {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;


        public Client() {
            Scanner scan = new Scanner(System.in);

            System.out.println("Введите IP для подключения к серверу.");

            String ip = scan.nextLine();

            try {
                socket = new Socket(ip, 8189);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Введите свой ник:");
                out.println(scan.nextLine());

                Resender resend = new Resender();
                resend.start();

                String str = "";
                while (!str.equals("exit")) {
                    str = scan.nextLine();
                    out.println(str);
                }
                resend.setStop();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        private void close() {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                System.err.println("Потоки не были закрыты!");
            }
        }

        private class Resender extends Thread {

            private boolean stoped;

            public void setStop() {
                stoped = true;
            }

            @Override
            public void run() {
                try {
                    while (!stoped) {
                        String str = in.readLine();
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при получении сообщения.");
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Запустить программу в режиме сервера(S) или клиента(С)");
        try {
            while (true) {
                String answ = scanner.nextLine();
                switch (answ.charAt(0)) {
                    case 'S':
                        new Server(new ServerSocket(8189));
                        break;
                    case 'C':
                        //new Client("127.0.0.1",3000).doConnect();
                        new Client();
                        break;
                    default:
                        System.out.println("Некорректный ввод. Повторите попытку.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
