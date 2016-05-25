package com.t0rr3sp3dr0.cc20161.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
        addItem(new DummyItem("esvm", "Edjan Michiles", "+55 (81) 99509-9949"));
        addItem(new DummyItem("ggfl", "Guilherme Lima", "+55 (81) 99286-0885"));
        addItem(new DummyItem("irs", "Italo Soares", "+55 (85) 99697-1669"));
        addItem(new DummyItem("lmmc2", "Larícia Mota", "+55 (81) 99969-9962"));
        addItem(new DummyItem("lvrma", "Lucas Valença", "+55 (81) 98212-6789"));
        addItem(new DummyItem("mlmv", "Mateus Nunes", "+55 (81) 99845-8658"));
        addItem(new DummyItem("mngs", "Malu Menezes", "+55 (81) 99730-0476"));
        addItem(new DummyItem("phls", "Pedro Henrique", "+55 (81) 99873-1802"));
        addItem(new DummyItem("psq", "Pedro Queiroga", "+55 (81) 99751-0910"));
        addItem(new DummyItem("rmmaf", "Rodrigo Falcão", "+55 (81) 98188-9846"));
        addItem(new DummyItem("rps4", "Rafael Prado", "+55 (79) 98828-4514"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
