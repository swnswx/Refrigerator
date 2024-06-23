package com.example.refrigerator;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecipeResponse {
    @SerializedName("COOKRCP01")
    private CookRcp01 cookRcp01;

    public CookRcp01 getCookRcp01() {
        return cookRcp01;
    }

    public static class CookRcp01 {
        @SerializedName("row")
        private List<Recipe> row;

        public List<Recipe> getRow() {
            return row;
        }
    }

    public static class Recipe {
        @SerializedName("RCP_SEQ")
        private String RCP_SEQ;

        @SerializedName("RCP_NM")
        private String RCP_NM;

        @SerializedName("ATT_FILE_NO_MAIN")
        private String ATT_FILE_NO_MAIN;

        @SerializedName("RCP_PARTS_DTLS")
        private String RCP_PARTS_DTLS;

        @SerializedName("MANUAL01")
        private String MANUAL01;
        @SerializedName("MANUAL_IMG01")
        private String MANUAL_IMG01;

        @SerializedName("MANUAL02")
        private String MANUAL02;
        @SerializedName("MANUAL_IMG02")
        private String MANUAL_IMG02;

        @SerializedName("MANUAL03")
        private String MANUAL03;
        @SerializedName("MANUAL_IMG03")
        private String MANUAL_IMG03;

        @SerializedName("MANUAL04")
        private String MANUAL04;
        @SerializedName("MANUAL_IMG04")
        private String MANUAL_IMG04;

        @SerializedName("MANUAL05")
        private String MANUAL05;
        @SerializedName("MANUAL_IMG05")
        private String MANUAL_IMG05;

        @SerializedName("MANUAL06")
        private String MANUAL06;
        @SerializedName("MANUAL_IMG06")
        private String MANUAL_IMG06;

        @SerializedName("MANUAL07")
        private String MANUAL07;
        @SerializedName("MANUAL_IMG07")
        private String MANUAL_IMG07;

        @SerializedName("MANUAL08")
        private String MANUAL08;
        @SerializedName("MANUAL_IMG08")
        private String MANUAL_IMG08;

        @SerializedName("MANUAL09")
        private String MANUAL09;
        @SerializedName("MANUAL_IMG09")
        private String MANUAL_IMG09;

        @SerializedName("MANUAL10")
        private String MANUAL10;
        @SerializedName("MANUAL_IMG10")
        private String MANUAL_IMG10;

        @SerializedName("MANUAL11")
        private String MANUAL11;
        @SerializedName("MANUAL_IMG11")
        private String MANUAL_IMG11;

        @SerializedName("MANUAL12")
        private String MANUAL12;
        @SerializedName("MANUAL_IMG12")
        private String MANUAL_IMG12;

        @SerializedName("MANUAL13")
        private String MANUAL13;
        @SerializedName("MANUAL_IMG13")
        private String MANUAL_IMG13;

        @SerializedName("MANUAL14")
        private String MANUAL14;
        @SerializedName("MANUAL_IMG14")
        private String MANUAL_IMG14;

        @SerializedName("MANUAL15")
        private String MANUAL15;
        @SerializedName("MANUAL_IMG15")
        private String MANUAL_IMG15;

        @SerializedName("MANUAL16")
        private String MANUAL16;
        @SerializedName("MANUAL_IMG16")
        private String MANUAL_IMG16;

        @SerializedName("MANUAL17")
        private String MANUAL17;
        @SerializedName("MANUAL_IMG17")
        private String MANUAL_IMG17;

        @SerializedName("MANUAL18")
        private String MANUAL18;
        @SerializedName("MANUAL_IMG18")
        private String MANUAL_IMG18;

        @SerializedName("MANUAL19")
        private String MANUAL19;
        @SerializedName("MANUAL_IMG19")
        private String MANUAL_IMG19;

        @SerializedName("MANUAL20")
        private String MANUAL20;
        @SerializedName("MANUAL_IMG20")
        private String MANUAL_IMG20;

        // Getter 메서드들
        public String getRCP_SEQ() {
            return RCP_SEQ;
        }

        public String getRCP_NM() {
            return RCP_NM;
        }

        public String getATT_FILE_NO_MAIN() {
            return ATT_FILE_NO_MAIN;
        }

        public String getRCP_PARTS_DTLS() {
            return RCP_PARTS_DTLS;
        }

        public String getMANUAL01() {
            return MANUAL01;
        }

        public String getMANUAL_IMG01() {
            return MANUAL_IMG01;
        }

        public String getMANUAL02() {
            return MANUAL02;
        }

        public String getMANUAL_IMG02() {
            return MANUAL_IMG02;
        }

        public String getMANUAL03() {
            return MANUAL03;
        }

        public String getMANUAL_IMG03() {
            return MANUAL_IMG03;
        }

        public String getMANUAL04() {
            return MANUAL04;
        }

        public String getMANUAL_IMG04() {
            return MANUAL_IMG04;
        }

        public String getMANUAL05() {
            return MANUAL05;
        }

        public String getMANUAL_IMG05() {
            return MANUAL_IMG05;
        }

        public String getMANUAL06() {
            return MANUAL06;
        }

        public String getMANUAL_IMG06() {
            return MANUAL_IMG06;
        }

        public String getMANUAL07() {
            return MANUAL07;
        }

        public String getMANUAL_IMG07() {
            return MANUAL_IMG07;
        }

        public String getMANUAL08() {
            return MANUAL08;
        }

        public String getMANUAL_IMG08() {
            return MANUAL_IMG08;
        }

        public String getMANUAL09() {
            return MANUAL09;
        }

        public String getMANUAL_IMG09() {
            return MANUAL_IMG09;
        }

        public String getMANUAL10() {
            return MANUAL10;
        }

        public String getMANUAL_IMG10() {
            return MANUAL_IMG10;
        }

        public String getMANUAL11() {
            return MANUAL11;
        }

        public String getMANUAL_IMG11() {
            return MANUAL_IMG11;
        }

        public String getMANUAL12() {
            return MANUAL12;
        }

        public String getMANUAL_IMG12() {
            return MANUAL_IMG12;
        }

        public String getMANUAL13() {
            return MANUAL13;
        }

        public String getMANUAL_IMG13() {
            return MANUAL_IMG13;
        }

        public String getMANUAL14() {
            return MANUAL14;
        }

        public String getMANUAL_IMG14() {
            return MANUAL_IMG14;
        }

        public String getMANUAL15() {
            return MANUAL15;
        }

        public String getMANUAL_IMG15() {
            return MANUAL_IMG15;
        }

        public String getMANUAL16() {
            return MANUAL16;
        }

        public String getMANUAL_IMG16() {
            return MANUAL_IMG16;
        }

        public String getMANUAL17() {
            return MANUAL17;
        }

        public String getMANUAL_IMG17() {
            return MANUAL_IMG17;
        }

        public String getMANUAL18() {
            return MANUAL18;
        }

        public String getMANUAL_IMG18() {
            return MANUAL_IMG18;
        }

        public String getMANUAL19() {
            return MANUAL19;
        }

        public String getMANUAL_IMG19() {
            return MANUAL_IMG19;
        }

        public String getMANUAL20() {
            return MANUAL20;
        }

        public String getMANUAL_IMG20() {
            return MANUAL_IMG20;
        }
    }
}
