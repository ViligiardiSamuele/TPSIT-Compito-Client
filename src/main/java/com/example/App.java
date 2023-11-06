package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket s = new Socket("localhost", 3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            //String risposta = new String();
            Scanner scanner = new Scanner(System.in);
            int scelta;
            String nota;
            System.out.println("Connessione effettuata. Digita 0 per uscire.");
            do {
                do {
                    System.out.println("Scegli un'azione:");
                    System.out.println(" 1 - Aggiungi una nota");
                    System.out.println(" 2 - Lista note");
                    System.out.print("Scelta: ");
                    scelta = scanner.nextInt();
                    scanner.nextLine();
                } while(scelta < 0 || scelta > 2);
                switch (scelta) {
                    case 1:
                        out.writeBytes("1\n");
                        System.out.print("Nota: ");
                        nota = scanner.next();
                        scanner.nextLine();
                        out.writeBytes(nota + '\n');
                        break;
                
                    case 2:
                        out.writeBytes("@" + '\n');
                        System.out.println("Note:");
                        System.out.println(in.readLine().replaceAll("&", "\n"));
                        break;
                }
            } while (scelta != 0);
            out.writeBytes("0\n");
            System.out.println("Disconnessione effettuata");
            scanner.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
