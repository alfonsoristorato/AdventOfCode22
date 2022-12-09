package Day7;

import java.util.*;

public class DirectoryEntry extends FileEntry {
    private final DirectoryEntry parent;
    private Map<String, FileEntry> directoryContent = new HashMap<>();

    public DirectoryEntry(String dirName, DirectoryEntry parent) {
        super(dirName, 0);
        this.parent = parent;
    }

    public Collection<FileEntry> getDirContent(){
        return directoryContent.values();
    }
    public void addFile(FileEntry file) {
        directoryContent.put(file.getName(), file);
    }

    public long getSize() {
        long count = 0;
        for (FileEntry file : directoryContent.values()) {
            count += file.getSize();
        }
        return count;
    }

    public DirectoryEntry getParent() {
        return parent;
    }

    public DirectoryEntry getDirectory(String directoryName) {
        return (DirectoryEntry) directoryContent.get(directoryName);
    }




}
