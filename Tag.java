public class Tag implements Comparable<Tag>{
    String tag;
    int count;

    public Tag(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Tag o) {
        int compareCount = ((Tag)o).getCount();
        //return this.count - compareCount;
        return compareCount - this.count;
    }
}
