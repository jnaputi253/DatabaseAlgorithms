import java.util.Scanner;

/**
 * Created by jnaputi253 on 4/9/17.
 */

public class InputHandler {
    public static String getUpdatedContent() {
        System.out.printf("Enter Content: ");
        Scanner kb = new Scanner(System.in);

        return kb.nextLine();
    }
}
