package al.rb.booking
/**
 * Description and images for room type.
 */
class Description {
    /**
     * A custom, optional name for the room in English.
     * If empty, we assigned a default name based on Room[@RoomType].
     */
    String text
    /**
     * The URL for a room photo. We'll download from this URL and upload to Booking.com servers.
     * Uploading photos is asynchronous.
     * We recommend uploading at least 4 photos per room, with a size of at least 2048x1536 pixels, in landscape orientation.
     */
    String image
    /**
     * One or more comma-separated Image Tag Type Codes that specify what the photo shows.
     * Example: 1,5,116
     */
    ImageTagTypeCode imageTagID

    static constraints = {
        text nullable: true
        image nullable: true
        imageTagID nullable: true
    }

    def buildXml(builder) {
        if(imageTagID){
           imageTagID = imageTagID.getCode()
        }
        builder."Description"{
            "Text" text
            "Image"("ImageTagID":this.imageTagID) image
        }
    }
}
