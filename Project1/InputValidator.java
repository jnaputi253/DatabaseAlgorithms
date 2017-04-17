/**
 * Created by jnaputi253 on 4/9/17.
 */
public class InputValidator {
    public boolean isValidInput(String userInput) {
        int inputCheck = 0;

        try {
            inputCheck = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
