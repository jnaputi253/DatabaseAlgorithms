import java.io.File;
import java.util.List;

/**
 * Created by jnaputi253 on 4/8/17.
 */
public class BufferManager {
    private BufferHashTable bufferHashTable;
    private Frame workingFrame;

    public BufferManager(int frameSize) {
        bufferHashTable = new BufferHashTable(frameSize);
    }

    public void createPages(int pagesToCreate) {
        List<File> pages = FileUtil.createPages(pagesToCreate);

        for(File page : pages) {
            bufferHashTable.addPage(page);
        }
    }

    public void pin(int pageToPin) {
        workingFrame = bufferHashTable.insert(pageToPin);

        if(workingFrame == null) {
            displayNoFrame();
            return;
        }

        workingFrame.incrementPin();
        workingFrame.displayPage();
    }

    public void unpin(int pageToUnpin) {
        workingFrame = bufferHashTable.lookup(pageToUnpin);

        if(workingFrame == null) {
            displayNoFrame();
        }

        if(workingFrame.getPin() > 0) {
            workingFrame.decrementPin();

            if(workingFrame.getPin() == 0) {
                bufferHashTable.insertIntoLruQueue(pageToUnpin);
            }
        }
    }

    public void displayPage(int pageToDisplay) {
        workingFrame = bufferHashTable.lookup(pageToDisplay);

        if(workingFrame == null) {
            displayNoFrame();
            return;
        }

        workingFrame.displayPage();
    }

    public void updatePage(int pageToUpdate) {
        workingFrame = bufferHashTable.lookup(pageToUpdate);

        if(workingFrame == null) {
            displayNoFrame();
            return;
        }

        workingFrame.displayPage();
        String newContent = InputHandler.getUpdatedContent();

        workingFrame.updatePage(newContent);
        workingFrame.setDirty(true);

        workingFrame.displayPage();
    }

    private void displayNoFrame() {
        System.out.println("No frame to work with");
    }
}
