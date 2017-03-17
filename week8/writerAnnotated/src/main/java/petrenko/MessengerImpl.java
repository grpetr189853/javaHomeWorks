package petrenko;


import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by java-1-08 on 12.03.2017.
 */
@Component
public class MessengerImpl implements Messenger {
    public String getMessage(){
        System.out.println("Input your message");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
