/**
 * Created by jnaputi253 on 4/9/17.
 */
public class BufferManagerTester {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("You must specify a buffer length");
            System.exit(-1);
        }

        int bufferLength = 0;

        try {
            bufferLength = Integer.parseInt(args[0]);

            if(bufferLength < 1) {
                System.out.println("Invalid length");
                System.exit(-1);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        BufferManager manager = new BufferManager(bufferLength);
        InputValidator validator = new InputValidator();
        Menu menu = new Menu(manager, validator);

        menu.start();
    }
}
