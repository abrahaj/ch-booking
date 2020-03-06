package al.rb.booking

class ImageFormat {
    /**
     * Specifies whether the image should be displayed as the main photo for the property.
     * 1 = true, 0 = false. Default: 0
     */
    short main
    /**
     * Specifies the sorting position of the image (where 1 is the highest position).
     *
     */
    int sort
    /**
     * The source URL for the image. We will download from this URL and upload to the Booking.com server.
     * Images are uploaded asynchronously. To check if all images have finished uploading, use /ota/OTA_HotelSummaryNotif.
     */
    String url
    static constraints = {
        main nullable: true
        sort nullable: false
        url nullable: false
    }
    
}
