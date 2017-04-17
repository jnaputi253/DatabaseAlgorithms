/**
 * Created by jnaputi253 on 4/6/17.
 */
public class Frame {
    private int pin;
    private boolean isDirty;
    private String frameContents;
    private int page;

    public Frame() {
        pin = 0;
        isDirty = false;
    }

    public Frame(int page, String frameContents) {
        this();
        this.frameContents = frameContents;
        this.page = page;
    }

    public int getPin() {
        return pin;
    }

    public void incrementPin() {
        pin++;
    }

    public void decrementPin() {
        pin--;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirtyValue) {
        isDirty = dirtyValue;
    }

    public int getPage() {
        return page;
    }

    public void displayPage() {
        System.out.printf("Page Content: %s\n\n", frameContents);
    }

    public String getFrameContents() {
        return frameContents;
    }

    public void updatePage(String updatedFrameContent) {
        frameContents = frameContents + "\n" +
                updatedFrameContent;
    }
}
