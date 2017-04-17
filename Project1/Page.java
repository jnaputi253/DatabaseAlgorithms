/**
 * Created by jnaputi253 on 4/7/17.
 */
public class Page {
    private int pageNumber;
    private String pageData;

    public Page(int pageNumber, String pageData) {
        this.pageNumber = pageNumber;
        this.pageData = pageData;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
