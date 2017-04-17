import java.util.Scanner;

/**
 * Created by jnaputi253 on 4/9/17.
 */
public class Menu {
    private Scanner kb;
    private BufferManager manager;
    private InputValidator validator;
    private boolean isFinished;

    public Menu(BufferManager manager, InputValidator validator) {
        kb = new Scanner(System.in);
        this.manager = manager;
        this.validator = validator;
        isFinished = false;
    }

    public void start() {
        while(!isFinished) {
            displayMenu();
        }
    }

    public void displayMenu() {
        System.out.println("1) Create Pages");
        System.out.println("2) Request Page");
        System.out.println("3) Update Page");
        System.out.println("4) Relinquish Page");
        System.out.println("-1) Exit");
        System.out.print("Choice: ");
        String input = kb.nextLine();

        System.out.println();

        checkUserChoice(input);
    }

    private void checkUserChoice(String input) {
        switch(input) {
            case "1":
                createPages();
                break;
            case "2":
                pinPage();
                break;
            case "3":
                updatePage();
                break;
            case "4":
                unpinPage();
                break;
            case "-1":
                isFinished = true;
                break;
            default:
                System.out.println("\nInvalid input\n");
                break;

        }
    }

    private void createPages() {
        int pagesToMake = 0;

        while(true) {
            System.out.print("How many pages: ");
            String input = kb.nextLine();

            if(validator.isValidInput(input)) {
                pagesToMake = Integer.parseInt(input);
                break;
            } else {
                displayNotANumber(input);
            }
        }

        manager.createPages(pagesToMake);
    }

    private void displayNotANumber(String input) {
        System.out.printf("%s is not a number\n", input);
    }

    private void pinPage() {
        int requestedPage = 0;

        while(true) {
            System.out.print("Page to pin: ");
            String input = kb.nextLine();

            if(validator.isValidInput(input)) {
                requestedPage = Integer.parseInt(input);
                break;
            } else {
                displayNotANumber(input);
            }
        }

        manager.pin(requestedPage);
    }

    private void updatePage() {
        int pageToUpdate = 0;

        while(true) {
            System.out.print("Page to update: ");
            String input = kb.nextLine();

            if(validator.isValidInput(input)) {
                pageToUpdate = Integer.parseInt(input);
                break;
            } else {
                displayNotANumber(input);
            }
        }

        manager.updatePage(pageToUpdate);
    }

    private void unpinPage() {
        int pageToUnpin;

        while(true) {
            System.out.print("Page to unpin: ");
            String input = kb.nextLine();

            if(validator.isValidInput(input)) {
                pageToUnpin = Integer.parseInt(input);
                break;
            } else {
                displayNotANumber(input);
            }
        }

        manager.unpin(pageToUnpin);
    }
}
