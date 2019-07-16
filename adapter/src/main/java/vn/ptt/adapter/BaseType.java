package vn.ptt.adapter;

public enum BaseType {
    TYPE1(0),
    TYPE2(1),
    TYPE3(2),
    TYPE4(3),
    TYPE5(4),
    TYPE6(5),
    TYPE7(6),
    TYPE8(7),
    TYPE9(8),
    TYPE10(9);

    private int value;

    public int getType() {
        return this.value;
    }

    BaseType(int value) {
        this.value = value;
    }

    public static BaseType setType(int value) {
        switch (value) {
            case 0:
                return TYPE1;
            case 1:
                return TYPE2;
            case 2:
                return TYPE3;
            case 3:
                return TYPE4;
            case 4:
                return TYPE5;
            case 5:
                return TYPE6;
            case 6:
                return TYPE7;
            case 7:
                return TYPE8;
            case 8:
                return TYPE9;
            case 9:
                return TYPE10;
        }
        return null;
    }
}
