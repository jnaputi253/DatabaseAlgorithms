import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Naputi on 4/8/17.
 */

public class FileUtil {
    public static int getFilename(File file) {
        int fileNumber = 0;

        try {
            String fileName = file.getName();
            String[] splitFilename = fileName.split("\\.");

            fileNumber = Integer.parseInt(splitFilename[0]);
        } catch(NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return fileNumber;
    }

    public static String getPageContents(File file) {
        BufferedReader fileReader;
        StringBuilder dataBuilder = null;

        try {
            fileReader = new BufferedReader(new FileReader(file));
            dataBuilder = new StringBuilder();
            String line;

            while((line = fileReader.readLine()) != null) {
                dataBuilder.append(line);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return dataBuilder == null ? "Dummy data" : dataBuilder.toString();
    }

    public static void updatePage(File pageToUpdate, String contents) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(pageToUpdate));
            writer.write(contents);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static List<File> createPages(int pagesToMake) {
        List<File> pages = new ArrayList<>();

        for(int currentPage = 0; currentPage < pagesToMake; currentPage++) {
            try {
                File file = new File(String.format("./%d.txt", currentPage));

                Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                fileWriter.write(String.format("This is Page %d", currentPage));
                fileWriter.close();

                pages.add(file);
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return pages;
    }
}
