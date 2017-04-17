import java.io.File;
import java.util.*;

/**
 * Created by jnaputi253 on 4/6/17.
 */

public class BufferHashTable {
    private Map<Integer, File> pageMap;
    private List<Frame> list;
    private int frameSize;
    private Queue<Integer> lruQueue;

    public BufferHashTable() {
        pageMap = new HashMap<>();
        list = new ArrayList<>();
        lruQueue = new LinkedList<>();
    }

    public BufferHashTable(int frameSize) {
        this();
        this.frameSize = frameSize;
    }

    public void addPage(File pageToAdd) {
        int key = FileUtil.getFilename(pageToAdd);

        pageMap.put(key, pageToAdd);
    }

    public Frame insert(int pageNumber) {
        Frame frameToReturn;
		
		if(!pageMap.containsKey(pageNumber)) {
			System.out.printf("\nThe specified page {%d} does not exist\n\n", pageNumber);
		}

        if(!listContainsPage(pageNumber)) {
            if(list.size() < frameSize) {
                frameToReturn = insertIntoFrameList(pageNumber);
            } else {
                frameToReturn = insertViaLru(pageNumber);
            }
        } else {
            frameToReturn = findFrameByPageNumber(pageNumber);
        }

        return frameToReturn;
    }

    private boolean listContainsPage(int pageToFind) {
        for(Frame frame : list) {
            if(frame.getPage() == pageToFind) {
                return true;
            }
        }

        return false;
    }

    private Frame findFrameByPageNumber(int pageNumber) {
        for(Frame frame : list) {
            if(frame.getPage() == pageNumber) {
                return frame;
            }
        }

        return null;
    }

    private Frame insertIntoFrameList(int pageToInsert) {
        String pageData = FileUtil.getPageContents(pageMap.get(pageToInsert));
        Frame frame = new Frame(pageToInsert, pageData);
        list.add(frame);

        return frame;
    }

    private Frame insertViaLru(int pageToInsert) {
        int affectedIndex = lruQueue.remove();

        Frame frameToRemove = list.get(affectedIndex);

        if(frameToRemove.isDirty()) {
            updatePage(frameToRemove.getPage(), frameToRemove.getFrameContents());
        }

        String pageData = FileUtil.getPageContents(pageMap.get(pageToInsert));
        Frame newFrame = new Frame(pageToInsert, pageData);
        list.set(affectedIndex, newFrame);

        return newFrame;
    }

    private void updatePage(int pageKey, String content) {
        File pageToUpdate = pageMap.get(pageKey);

        if(pageToUpdate == null) {
            System.out.println("Come back here and check this...");
            return;
        }

        FileUtil.updatePage(pageToUpdate, content);
    }

    public Frame lookup(int pageNumber) {
        Frame foundFrame = null;

        if(pageMap.containsKey(pageNumber)) {
            for(Frame frame : list) {
                if(frame.getPage() == pageNumber) {
                    foundFrame = frame;
                    break;
                }
            }
        }

        return foundFrame;
    }

    public void insertIntoLruQueue(int markedPage) {
        for(int currentIndex = 0; currentIndex < list.size(); currentIndex++) {
            if(list.get(currentIndex).getPage() == markedPage) {
                if(!lruQueue.contains(currentIndex)) {
                    lruQueue.add(currentIndex);
                    break;
                }
            }
        }
    }
}
