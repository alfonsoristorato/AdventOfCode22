package Day7;

public class FileEntry {
    private String name;
    private long size;

    public FileEntry(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

}
