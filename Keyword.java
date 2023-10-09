public class Keyword implements Comparable<Keyword>{
    String keyword;
    int count;

    public Keyword(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Keyword o) {
        int keywordCount = ((Keyword)o).getCount();
        return keywordCount - this.count;
    }
}
