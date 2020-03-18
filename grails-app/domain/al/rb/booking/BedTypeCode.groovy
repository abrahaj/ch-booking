package al.rb.booking

enum BedTypeCode {

    DOUBLE_BED(33,"Double (Bed)","131-150 cm (52-59 inches) wide",""),
    FUTON_MAT(200,"Futon Mat","Variable",""),
    EXTRA_LARGE_DOUBLE(58,"Extra large Double","181-210 cm (71-82 inches) wide",""),
    LARGE_DOUBLE(86,"Large Double","151-180 cm (60-70 inches) wide",""),
    SOFA_BED(102,"Sofa Bed","Variable",""),
    TWIN_BED(113,"Twin (bed)*","90-130 cm (35-51 inches) wide","* Please load one twin bed per person."),
    SINGLE_BED(203,"Single (bed)","90-130 cm (35-51 inches) wide",""),
    BUNK_BED(4001,"Bunk bed","Variable","")


    private final int code
    private final String type
    private final String description
    private final String notes

    private BedTypeCode(code, type, description, notes){
        this.code = code
        this.type = type
        this.description = description
        this.notes = notes
    }

    String getBedDescription() {
        return description
    }
    String getBedType() {
        return type
    }
    String getBedTypeNotes() {
        return notes
    }
    int getCode() {
        return code
    }

    @Override
    String toString() {
        return code + ": " + type
    }
}
