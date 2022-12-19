package Day13;

import java.util.ArrayList;
import java.util.List;

public class Packet implements Comparable<Packet> {
    private String contents;

    public String getContents() {
        return contents;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    private Boolean isInteger = true;
    private List<Packet> subPackets = new ArrayList<>();

    public Packet(String content) {
        handleContent(content);
    }

    private void handleContent(String content) {
        contents = content;
        if (content.startsWith("[")) {
            createSubpacket(content);
        } else {
            value = Integer.parseInt(content);
        }
    }

    private void createSubpacket(String content) {
        content = content.substring(1, content.length() - 1);
        isInteger = false;
        Integer depth = 0;
        StringBuilder builder = new StringBuilder();
        for (Character c : content.toCharArray()) {
            if (depth == 0 && c == ',') {
                subPackets.add(new Packet(builder.toString()));
                builder = new StringBuilder();
            } else {
                if (c == '[') {
                    depth++;
                } else if (c == ']') {
                    depth--;
                }
                builder.append(c);
            }
        }
        if (builder.length() > 0) {
            subPackets.add(new Packet(builder.toString()));
        }
    }

    @Override
    public int compareTo(Packet o) {
        if (this.isInteger && o.isInteger) {
            return Integer.compare(this.value, o.value);
        } else if (!this.isInteger && !o.isInteger) {
            for (int i = 0; i < Math.min(this.subPackets.size(), o.subPackets.size()); i++) {
                Integer compare = this.subPackets.get(i).compareTo(o.subPackets.get(i));
                if (compare != 0) {
                    return compare;
                }
            }
            return Integer.compare(this.subPackets.size(), o.subPackets.size());
        } else if (this.isInteger) {
            return new Packet("[" + value + "]").compareTo(o);
        } else {
            return this.compareTo(new Packet("[" + o.value + "]"));
        }
    }

}
