package com.t0rr3sp3dr0.cc20161.dummy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;

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
        addItem(new DummyItem("ascj", "Alecsandro Silva", "+55 (81) 99687-1272"));
        addItem(new DummyItem("acms", "André De' Carli", "+55 (81) 99656-7303"));
        addItem(new DummyItem("afa4", "Arthur Frade", "+55 (81) 99222-6817"));
        addItem(new DummyItem("bor", "Breno Rodrigues", ""));
        addItem(new DummyItem("cco2", "Claudio Carvalho", "+55 (81) 98859-5275"));
        addItem(new DummyItem("dfb2", "Daniel Filgueira", "+55 (81) 99956-9764"));
        addItem(new DummyItem("dvf", "Danilo Freitas", "+55 (81) 98938-1346"));
        addItem(new DummyItem("egsj", "Evaldo Júnior", "+55 (81) 99737-7461"));
        addItem(new DummyItem("esvm", "Edjan Michiles", "+55 (81) 99509-9949"));
        addItem(new DummyItem("memgra", "Eulália Aires", "+55 (81) 99713-4797"));
        addItem(new DummyItem("fbsj", "Flávio Braga", "+55 (81) 99849-5951"));
        addItem(new DummyItem("gacm", "Guilherme Augusto", ""));
        addItem(new DummyItem("ggfl", "Guilherme Gouveia", "+55 (81) 99286-0885"));
        addItem(new DummyItem("gms2", "Gabriel Monteiro", "+55 (81) 99959-4519"));
        addItem(new DummyItem("ham2", "Henrique Mariz", "+55 (81) 99705-0496"));
        addItem(new DummyItem("irs", "Italo Soares", "+55 (85) 99697-1669"));
        addItem(new DummyItem("jva", "Josenildo Vicente", "+55 (81) 98225-7747"));
        addItem(new DummyItem("jgs3", "Joyce Santana", "+55 (81) 98763-5983"));
        addItem(new DummyItem("lmmc2", "Larícia Mota", "+55 (81) 99969-9962"));
        addItem(new DummyItem("lavn", "Lucas Asafe", "+55 (81) 99899-0714"));
        addItem(new DummyItem("lml", "Lucas Lin", ""));
        addItem(new DummyItem("lvrma", "Lucas Valença", "+55 (81) 98212-6789"));
        addItem(new DummyItem("mlmv", "Malu Menezes", "+55 (81) 99730-0476"));
        addItem(new DummyItem("mngs", "Mateus Nunes", "+55 (81) 99845-8658"));
        addItem(new DummyItem("nesn", "Natanael Neto", "+55 (81) 99843-3904"));
        addItem(new DummyItem("pbsf2", "Pamella Ferreira", "+55 (81) 99656-6121"));
        addItem(new DummyItem("phls", "Pedro Henrique", "+55 (81) 99873-1802"));
        addItem(new DummyItem("psq", "Pedro Queiroga", "+55 (81) 99751-0910"));
        addItem(new DummyItem("phts", "Pedro Tôrres", "+55 (81) 98238-0880"));
        addItem(new DummyItem("rps4", "Rafael Prado", "+55 (79) 98828-4514"));
        addItem(new DummyItem("rpss", "Ramom Pereira", "+55 (87) 99681-8972"));
        addItem(new DummyItem("rjmf", "Renato Ferreira", "+55 (81) 99764-3091"));
        addItem(new DummyItem("rmmaf", "Rodrigo Falcão", "+55 (81) 98188-9846"));
        addItem(new DummyItem("sttf", "Sergio Torres", "+55 (81) 99194-7663"));
        addItem(new DummyItem("vag2", "Victor Aguiar", "+55 (81) 98446-9536"));
        addItem(new DummyItem("vms5", "Victor Martins", "+55 (98) 98258-8853"));
        addItem(new DummyItem("vjsl2", "Vinicius Lira", "+55 (81) 98436-1070"));
        addItem(new DummyItem("wsbj", "Wagner Soares", "+55 (81) 98284-6766"));
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

    public static final Drawable thumbnailPicture(Context context, int resId) {
        try {
            Drawable image = ContextCompat.getDrawable(context, resId);
            Bitmap b = ((BitmapDrawable) image).getBitmap();
            Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 128, 128, false);
            return new BitmapDrawable(Resources.getSystem(), bitmapResized);
        } catch (Exception e) {
            return null;
        }
    }

    public static final Map<String, Drawable> thumbnailsList(Context context) {
        Map<String, Drawable> map = new ArrayMap<>();
        for (DummyItem item : ITEMS)
            map.put(item.id, thumbnailPicture(context, context.getResources().getIdentifier(item.id, "drawable", "com.t0rr3sp3dr0.cc20161")));
        return map;
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
