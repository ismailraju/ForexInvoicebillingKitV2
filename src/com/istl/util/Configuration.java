package com.istl.util;

public class Configuration
  {
    // define variables necessary for algorithm
    public int minEyeDistance = 40;             // minimal eye distance in input image
    public int maxEyeDistance = 200;            // maximal eye distance in input image
    public int maxFacesCount = 10;              // maximal number of faces to be found
    public String faceDetectMode = "accurate";  // face detect mode
    public String strFileImage = " ";            // filename of input image to be processed
    public String strFileSegmentMask = "segmentation_output.png";      // filename of output segmentation mask
    public int segmentMaskFaceIndex = 0;        // index of a face to be segmented
    public String strFileCrop = "f:/crop_output.jpg";             // filename of output crop image
    public int cropFaceIndex = 0;               // index of a face to be cropped
    public String licensePath = "f:/iface_license.lic";
    public String image0Path = "f:/temp1.jpg";
    public String image1Path = "f:/temp2.jpg";
    
}