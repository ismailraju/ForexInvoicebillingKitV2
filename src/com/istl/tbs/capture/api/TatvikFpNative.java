package com.istl.tbs.capture.api;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;

public interface TatvikFpNative extends Library {	

	final int MAX_IMAGE_WIDTH = 1600;
	final int MAX_IMAGE_HEIGHT = 1500;
	final int IMAGE_MAX_SIZE = 	(MAX_IMAGE_WIDTH * MAX_IMAGE_HEIGHT);
	final int MAX_FP_COUNT	=	4;
	final int TATVIK_STATUS_OK	= 	0;
	
	// Generic Error Codes                           
	final int TATVIK_GENERIC_ERR                         = (0); 
	final int TATVIK_ERR_INVALID_PARAM_VALUE             = (TATVIK_GENERIC_ERR-1);    ///< Invalid parameter value
	final int TATVIK_ERR_MEM_ALLOC                       = (TATVIK_GENERIC_ERR-2);    ///< Insufficient memory
	final int TATVIK_ERR_NOT_SUPPORTED                   = (TATVIK_GENERIC_ERR-3);    ///< Requested functionality isn't supported
	final int TATVIK_ERR_FILE_OPEN                       = (TATVIK_GENERIC_ERR-4);    ///< File open failed
	final int TATVIK_ERR_FILE_READ                       = (TATVIK_GENERIC_ERR-5);    ///< File read failed
	final int TATVIK_ERR_THREAD_RESUME_ERR               = (TATVIK_GENERIC_ERR-6);    ///< Resume thread failed
	final int TATVIK_ERR_THREAD_CREATE_ERR               = (TATVIK_GENERIC_ERR-7);    ///< Create thread failed
	final int TATVIK_ERR_EVENT_CREATE_ERR                = (TATVIK_GENERIC_ERR-8);    ///< Create event failed
	final int TATVIK_ERR_LOG_LEVEL_LOW                   = (TATVIK_GENERIC_ERR-9);    ///< Log level is lowly
	final int TATVIK_ERR_LOG_DIRECTORY_IS_NO_EXIST       = (TATVIK_GENERIC_ERR-10);   ///< Log directory is no exist
	final int TATVIK_ERR_DIRECTORY_CREATE                = (TATVIK_GENERIC_ERR-11);   ///< Directory create failed
 
	final int TATVIK_ERR_FILE_WRITE                      = (TATVIK_GENERIC_ERR-12);    ///< File write failed
                                                     
	// Device Related Error Codes                     
	final int TATVIK_DEVICE_ERR                          = (-600);
	final int TATVIK_ERR_DEVICE_IO                       = (TATVIK_DEVICE_ERR-0);     ///< Device communication failed
	final int TATVIK_ERR_COMMAND_FAILED                  = (TATVIK_DEVICE_ERR-1);     ///< Command execution failed
	final int TATVIK_ERR_COMMAND_TIMEOUT                 = (TATVIK_DEVICE_ERR-2);     ///< Command execution timed out
	final int TATVIK_ERR_NO_DEVICE                       = (TATVIK_DEVICE_ERR-3);     ///< No device is detected/active
	final int TATVIK_ERR_NO_MATCHING_DEVICE              = (TATVIK_DEVICE_ERR-4);     ///< No matching device is detected
	final int TATVIK_ERR_DEVICE_ACTIVE                   = (TATVIK_DEVICE_ERR-5);     ///< Initialization failed because in use by another thread/process
	final int TATVIK_ERR_NOT_INITIALIZED                 = (TATVIK_DEVICE_ERR-6);     ///< Device needs to be initialized  
	final int TATVIK_ERR_IS_INITIALIZED                  = (TATVIK_DEVICE_ERR-7);     ///< Device is initialized  
	final int TATVIK_ERR_DEVICE_BUSY                     = (TATVIK_DEVICE_ERR-8);     ///< Another thread is currently using device functions
	final int TATVIK_ERR_NO_HARDWARE_SUPPORT             = (TATVIK_DEVICE_ERR-9);     ///< No hardware support for requested function 
	final int TATVIK_ERR_DEVICE_READ_PARAM               = (TATVIK_DEVICE_ERR-10);    ///< Device parameters can't be read
	final int TATVIK_ERR_DEVICE_WRITE_PARAM              = (TATVIK_DEVICE_ERR-11);    ///< Parameter write to device failed
	final int TATVIK_ERR_DEVICE_INVALID_PARAM            = (TATVIK_DEVICE_ERR-12);    ///< Parameter read from device is invalid
	final int TATVIK_ERR_DEVICE_INSUFFICIENT_MEMORY      = (TATVIK_DEVICE_ERR-13);    ///< No memory available for specified action (exceeded size);
	final int TATVIK_ERR_DEVICE_WRONG_OPERATION_MODE     = (TATVIK_DEVICE_ERR-14);    ///< Device operation mode is incorrect
	final int TATVIK_ERR_IS_CAPTUREING                   = (TATVIK_DEVICE_ERR-15);    ///< Device is capturing
	final int TATVIK_ERR_DEVICE_NO_SUPPORT               = (TATVIK_DEVICE_ERR-16);    ///< Device is no support
                                                     
	final int TATVIK_ERR_DEVICE_EXCEED_MAX               = (TATVIK_DEVICE_ERR-17);    ///< To exceed the maximum of device number.
	final int TATVIK_ERR_HANDLE_INVALID                  = (TATVIK_DEVICE_ERR-18);    ///< Invalid handle for device
	final int TATVIK_ERR_HANDLE_NOT_MATCH                = (TATVIK_DEVICE_ERR-19);    ///< Input handle and current handle not match
	final int TATVIK_ERR_AUTO_CAP_AREA_INFO              = (TATVIK_DEVICE_ERR-20);    ///< Input auto capture area information error
	final int TATVIK_ERR_NOT_CAPTURING                   = (TATVIK_DEVICE_ERR-21);    ///< No capture is running
	final int TATVIK_ERR_CAPTURE_PALMMOVING              = (TATVIK_DEVICE_ERR-22);    ///< Palm is Moving
                                                     
	final int TATVIK_ERR_CONTROL_STREAM                  = (TATVIK_DEVICE_ERR-23);    ///< Control stream failed
	final int TATVIK_ERR_STILL_INFORMATION_SET           = (TATVIK_DEVICE_ERR-24);    ///< Set still stream information failed
	final int TATVIK_ERR_STUFF_AND_RUN_STREAM            = (TATVIK_DEVICE_ERR-25);    ///< Stuff and run stream failed
	final int TATVIK_ERR_SET_VIDEO_PROP                  = (TATVIK_DEVICE_ERR-26);    ///< Set video property failed
	final int TATVIK_ERR_SET_EXPOSURE_FAILED             = (TATVIK_DEVICE_ERR-27);    ///< Set exposure failed
	final int TATVIK_ERR_SETUP_DI_ENUM_DEVICE_INFO       = (TATVIK_DEVICE_ERR-28);    ///< Setup DiEnum device information failed
	final int TATVIK_ERR_USB_VERSION_NO_SUPPORT          = (TATVIK_DEVICE_ERR-29);    ///< USB version is no support
	final int TATVIK_ERR_ENTRY_POINT_INVALID             = (TATVIK_DEVICE_ERR-30);    ///< Invalid entry point
                                                     
	// Clyde Mosaic error codes
	final int TATVIK_MOSAIC_ERR                          =	(-800);
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters   =	(TATVIK_MOSAIC_ERR-1);     ///< Invalid Input Parameters
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters1  =	(TATVIK_MOSAIC_ERR-2);     ///< Invalid Input Parameters1
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters2  =	(TATVIK_MOSAIC_ERR-3);     ///< Invalid Input Parameters2
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters3  =	(TATVIK_MOSAIC_ERR-4);     ///< Invalid Input Parameters3
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters4  =	(TATVIK_MOSAIC_ERR-5);     ///< Invalid Input Parameters4
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters5  =	(TATVIK_MOSAIC_ERR-6);     ///< Invalid Input Parameters5
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters6  =	(TATVIK_MOSAIC_ERR-7);     ///< Invalid Input Parameters6
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters7  =	(TATVIK_MOSAIC_ERR-8);     ///< Invalid Input Parameters7
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters8  =	(TATVIK_MOSAIC_ERR-9);     ///< Invalid Input Parameters8
	final int TATVIK_ERR_MOSAIC_InvalidInputParameters9  =	(TATVIK_MOSAIC_ERR-10);    ///< Invalid Input Parameters9
	final int TATVIK_ERR_MOSAIC_InvalidInputPointer      =	(TATVIK_MOSAIC_ERR-11);    ///< Invalid Input Pointer
	final int TATVIK_ERR_MOSAIC_CannotAllocateMemory     =	(TATVIK_MOSAIC_ERR-12);    ///< Can't Allocate Memory
	final int TATVIK_ERR_MOSAIC_CannotOpenFile           =	(TATVIK_MOSAIC_ERR-13);    ///< Can't Open File
	final int TATVIK_ERR_MOSAIC_CannotOpenImageFile      =	(TATVIK_MOSAIC_ERR-14);    ///< Can't Open Image File
	final int TATVIK_ERR_MOSAIC_CannotReadImageData      =	(TATVIK_MOSAIC_ERR-15);    ///< Can't Read Image Data
	final int TATVIK_ERR_MOSAIC_CannotOpenCaptureDevice  =	(TATVIK_MOSAIC_ERR-16);    ///< Can't Open Capture Device, Please connect the fingerprint sensor to your computer or check whether its driver is installed
	final int TATVIK_ERR_MOSAIC_DirtySensorWindow        =	(TATVIK_MOSAIC_ERR-17);    ///< The sensor window might be dirty, please clean it before you capture fingerprints
	final int TATVIK_ERR_MOSAIC_LowContrast              =	(TATVIK_MOSAIC_ERR-18);    ///< The sensor might be Low Contrast Please calibrate it
	final int TATVIK_ERR_MOSAIC_LowExplosure             =	(TATVIK_MOSAIC_ERR-19);    ///< The sensor might be Low Exposure Please calibrate it
	final int TATVIK_ERR_MOSAIC_LowBrightness            =	(TATVIK_MOSAIC_ERR-20);    ///< The sensor might be Low Exposure Please calibrate it
	final int TATVIK_ERR_MOSAIC_TooWhite                 =	(TATVIK_MOSAIC_ERR-21);    ///< If you have placed your finger quite hard on the sensor, then the brightness of the sensor might be too big or the contrast is too low
	final int TATVIK_ERR_MOSAIC_TooDark                  =	(TATVIK_MOSAIC_ERR-22);    ///< The brightness of the sensor might be too small
	final int TATVIK_ERR_MOSAIC_SmallDynamicRange        =	(TATVIK_MOSAIC_ERR-23);    ///< If you have placed your finger quite hard on the sensor, then the contrast of the sensor is too low
	final int TATVIK_ERR_MOSAIC_SmallVar                 =	(TATVIK_MOSAIC_ERR-24);    ///< If you have placed your finger quite hard on the sensor, then the contrast of the sensor is too low
	final int TATVIK_ERR_MOSAIC_TooFast                  =	(TATVIK_MOSAIC_ERR-25);    ///< Your rolling speed is too fast, please try pressing harder and rolling a little more slowly
	final int TATVIK_ERR_MOSAIC_TooSlow                  =	(TATVIK_MOSAIC_ERR-26);    ///< Your rolling speed is too slow, please try rolling a little more faster
	final int TATVIK_ERR_MOSAIC_RollBack                 =	(TATVIK_MOSAIC_ERR-27);    ///< Don't leave or roll backwards until you finish rolling all area of your finger
	final int TATVIK_ERR_MOSAIC_Sliding                  =	(TATVIK_MOSAIC_ERR-28);    ///< Don't slide your finger during rolling
	final int TATVIK_ERR_MOSAIC_MosaickedImageForeGoundTooSmall              =	(TATVIK_MOSAIC_ERR-29);     ///< Mosaicked image foreGound too small
	final int TATVIK_ERR_MOSAIC_MosaickedForeGoundWidthTooSmall              =	(TATVIK_MOSAIC_ERR-30);     ///< Mosaicked Foreground Width Too Small, please roll a little faster
	final int TATVIK_ERR_MOSAIC_MosaickedForeGoundHeightTooSmall             =	(TATVIK_MOSAIC_ERR-31);     ///< Mosaicked Foreground Height Too Small
	final int TATVIK_ERR_MOSAIC_MosaickedForeGoundWidthTooSmallEarlyAbort    =	(TATVIK_MOSAIC_ERR-32);     ///< Mosaicked ForeGound Width Too Small because your finger left, rolled backwards or slide before complete fingerprint is sampled
	final int TATVIK_ERR_MOSAIC_MosaickedImageCenterTooLow                   =	(TATVIK_MOSAIC_ERR-33);     ///< Mosaicked Image Center Too Low,please place your finger a little higher
	final int TATVIK_ERR_MOSAIC_MosaickedImageCenterTooHigh                  =	(TATVIK_MOSAIC_ERR-34);     ///< Mosaicked Image Center Too High, please place your finger a little Lower
	final int TATVIK_ERR_MOSAIC_MosaickedImageQualityTooLow                  =	(TATVIK_MOSAIC_ERR-35);     ///< Mosaicked Image Quality Too Low
	final int TATVIK_ERR_MOSAIC_DryFinger                                    =	(TATVIK_MOSAIC_ERR-36);     ///< Dry finger Please press harder or Calibrate the sensor with Lower sensor brightness and higher contrast
	final int TATVIK_ERR_MOSAIC_WetFinger                                    =	(TATVIK_MOSAIC_ERR-37);     ///< Wet finger Please press slighter or Calibrate the sensor Higher sensor brightness and higher contrast
	final int TATVIK_ERR_MOSAIC_DirtySensorWindow1                           =	(TATVIK_MOSAIC_ERR-38);     ///< If your finger is on the sensor, please remove it from the sensor
	final int TATVIK_ERR_MOSAIC_FingerIsLeaving                              =	(TATVIK_MOSAIC_ERR-39);     ///< Your finger is leaving
	final int TATVIK_ERR_MOSAIC_Saturated                                    =	(TATVIK_MOSAIC_ERR-40);     ///< Saturated
	final int TATVIK_ERR_MOSAIC_TooManyDefectedLines                         =	(TATVIK_MOSAIC_ERR-41);     ///< Too Many Defected Lines

	final int TATVIK_ERR_MOSAIC_MosaickedImageCenterTooLeft					 =	(TATVIK_MOSAIC_ERR-42);     ///< Mosaicked Image Center Too Left, please place your finger a little right
	final int TATVIK_ERR_MOSAIC_MosaickedImageCenterTooRight				 =	(TATVIK_MOSAIC_ERR-43);     ///< Mosaicked Image Center Too Right, please place your finger a little left

        //Arun Biswas
//	enum TATVIKImageType
//	{
//		TATVIK_ROLL_SINGLE_FINGER(0),                         ///< Rolled finger print image 
//		TATVIK_FLAT_SINGLE_FINGER(1),                         ///< Flat single finger
//		TATVIK_FLAT_RIGHT_FINGERS(2),                         ///< Right flat fingers
//		TATVIK_FLAT_LEFT_FINGERS(3),                          ///< Left flat fingers
//		TATVIK_FLAT_TWO_THUMBS(4),                            ///< Two flat thumbs
//		TATVIK_ROLL_RIGHT_THUMB(5),                           ///< Right thumb Rolled finger print image
//		TATVIK_ROLL_LEFT_THUMB(6),                            ///< Left thumb Rolled finger print image
//
//		TATVIK_PALM_RIGHT_FULL(7),                            ///< Full right palm
//		TATVIK_PALM_RIGHT_WRITERS(8),                         ///< Right writers palm
//		TATVIK_PALM_RIGHT_LOWER(9),                           ///< Right lower palm
//		TATVIK_PALM_RIGHT_UPPER(10),                           ///< Right upper palm
//		TATVIK_PALM_LEFT_FULL(11),                             ///< Full left palm
//		TATVIK_PALM_LEFT_WRITERS(12),                          ///< Left writers palm
//		TATVIK_PALM_LEFT_LOWER(13),                            ///< Left lower palm
//		TATVIK_PALM_LEFT_UPPER(14),                            ///< Left upper palm
//
//		TATVIK_ROLL_RIGHT_INDEX(15),                           ///< Right index Rolled finger print image
//		TATVIK_ROLL_RIGHT_MIDDLE(16),                          ///< Right middle Rolled finger print image
//		TATVIK_ROLL_RIGHT_RING(17),                            ///< Right ring Rolled finger print image
//		TATVIK_ROLL_RIGHT_SMALL(18),                           ///< Right small Rolled finger print image
//		TATVIK_ROLL_LEFT_INDEX(18),                            ///< Left index Rolled finger print image
//		TATVIK_ROLL_LEFT_MIDDLE(19),                           ///< Left middle Rolled finger print image
//		TATVIK_ROLL_LEFT_RING(20),                             ///< Left ring Rolled finger print image
//		TATVIK_ROLL_LEFT_SMALL(21),                            ///< Left small Rolled finger print image
//
//		TATVIK_FLAT_RIGHT_THUMB(22),                           ///< Right flat thumb
//		TATVIK_FLAT_RIGHT_INDEX(23),                           ///< Right flat index
//		TATVIK_FLAT_RIGHT_MIDDLE(24),                          ///< Right flat middle
//		TATVIK_FLAT_RIGHT_RING(25),                            ///< Right flat ring
//		TATVIK_FLAT_RIGHT_SMALL(26),                           ///< Right flat small
//		TATVIK_FLAT_LEFT_THUMB(27),                            ///< Left flat thumb
//		TATVIK_FLAT_LEFT_INDEX(28),                            ///< Left flat index
//		TATVIK_FLAT_LEFT_MIDDLE(29),                           ///< Left flat middle
//		TATVIK_FLAT_LEFT_RING(30),                             ///< Left flat ring
//		TATVIK_FLAT_LEFT_SMALL(31),                            ///< Left flat small
//
//		TATVIK_TYPE_NONE(32);                                  ///< No image (default after initialization)
//	 
//		private final int value;
//	    private TATVIKImageType(int value) {
//	        this.value = value;
//	    }
//
//	    public int getValue() {
//	        return value;
//	    }
//	}	

    // struct & enum
    public class TatvikImageFingerQT extends Structure{
    	
    	public static class ByReference extends TatvikImageFingerQT implements Structure.ByReference{}
    	
    	public int position;                         ///< finger position
    	public int QT;                               ///< finger quality
    	public int fpEdgeDetect;
		
		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[]{"position","QT","fpEdgeDetect"});
		} 
    }

	public class TBSCaptureQuality extends Structure{
		
		public static class ByReference extends TBSCaptureQuality implements Structure.ByReference{}
		
		public int inProcessFingerCount;
		public TatvikImageFingerQT []fpQT = new TatvikImageFingerQT[4]; //Finger Quality
		public int handType; 
		public boolean isDirtyBackGround;
		
		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[]{"inProcessFingerCount","fpQT","handType","isDirtyBackGround"});
		} 
	}


	
	public class TBSCaptureData extends Structure {

		public static class ByReference extends TBSCaptureData implements Structure.ByReference{}

		public byte[] rawImageBytes = new byte[IMAGE_MAX_SIZE]; // Raw Image in bytes
		public int imageWidth;     //To store image width
		public int imageHeight;    //To store image height
		public int fingerCount;		//Fingercount
		public int resX; 
		public int resY; 		
		public byte bitsPerPixel;
		public int fingerPosition; //Handposition
		
		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[]{"rawImageBytes","imageWidth","imageHeight","fingerCount","resX","resY","bitsPerPixel","fingerPosition"});
		}
	}

	public static final String name = "TatvikFpNative";
	TatvikFpNative myinstance = (TatvikFpNative) Native.loadLibrary(
			Platform.isWindows() ? name : "TatvikFpNative", TatvikFpNative.class);

	//------------------------------------------------------------------- 
	// Function name: TATVIK_CallbackResultImage
	// Parameter:
	//    input:  TATVIK_CallbackResultImage  - Callback to Result Image
	//			  pContext - caller defined context (e.g. handler object instance pointer)
	//            tbsCaptureData - TBSCaptureData to hold captured Frame)
	//    output: 
	//    in/out:
	// Return:
	//    TATVIK_STATUS_OK
	//    TATVIK_ERR_NOT_INITIALIZED
	//    TATVIK_ERR_INVALID_PARAM_VALUE
	// Description: Preview image available notification.
	//------------------------------------------------------------------- 	
	// define an interface that wraps the callback code
	public interface ResultImageCallbackInterface extends Callback {
		void invoke(int pContext, TBSCaptureData tbsCaptureData);
	}
	
	public void tbsCallbackResultImage(ResultImageCallbackInterface callback);

	//------------------------------------------------------------------- 
	// Function name: TATVIK_CallbackPreviewImage
	// Parameter:
	//    input:  TATVIK_CallbackPreviewImage - Callback to preview Image
	//			  pContext - caller defined context (e.g. handler object instance pointer)
	//            tbsCaptureData - TBSCaptureData to hold incoming frames)
	//    output: 
	//    in/out:
	// Return:
	//    TATVIK_STATUS_OK
	//    TATVIK_ERR_NOT_INITIALIZED
	//    TATVIK_ERR_INVALID_PARAM_VALUE
	// Description: Preview image available notification.
	//------------------------------------------------------------------- 
	// define an interface that wraps the callback code
	public interface PreviewImageCallbackInterface extends Callback {
		void invoke(int pContext, TBSCaptureData tbsCaptureData, TBSCaptureQuality tbsCaptureQuality);
	}
	
/*	public static class PreviewImageCallbackImplementation implements PreviewImageCallbackInterface {
		@Override
		public void invoke(int pContext, TBSCaptureData tbsCaptureData) {
			System.out.println("context address in preview : " + pContext);
			System.out.println("TBSCaptureData height in preview : " + tbsCaptureData.imageWidth);
		}
	}
	
	public static class ResultImageCallbackImplementation implements ResultImageCallbackInterface {
		@Override
		public void invoke(int pContext, TBSCaptureData tbsCaptureData) {
			System.out.println("context address in Result: " + pContext);
			System.out.println("TBSCaptureData height in result : " + tbsCaptureData.imageWidth);			
		}
	}*/	
	
	public void tbsCallbackPreviewImage(PreviewImageCallbackInterface callback);
	
	//------------------------------------------------------------------- 
	// Function name: TatvikSetCaptureCallbackFunctions
	// Parameter:
	//    input:  TATVIK_CallbackPreviewImage - Callback to preview Image
	//			  TATVIK_CallbackResultImage  - Callback to Result Image
	//			  pContext - caller defined context (e.g. handler object instance pointer)
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Preview image available notification.
	//------------------------------------------------------------------- 
	int tbsSetCaptureCallbackFunctions(PreviewImageCallbackInterface previewCallback, 
			ResultImageCallbackInterface resultCallback, int pContext);
	
	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintInit
	// Parameter:
	//    input:  
	//
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Initialize Fingerprint Scanner.
	//------------------------------------------------------------------- 
	int tbsFingerPrintInit();


	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintRelease
	// Parameter:
	//    input:  
	//
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: De-Initialize Fingerprint Scanner.
	//------------------------------------------------------------------- 
	int tbsFingerPrintRelease();
	

	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintCaptureStart
	// Parameter:
	//    input:  fingerCount - number of Finger(s) to be captured
	//			  imageType  - Type of Image to be captured. Image Type : TATVIKImageType
	//			  bAutoCapture - true to enable autoCapture
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: To Start fingerprint capture
	//------------------------------------------------------------------- 
	int tbsFingerPrintCaptureStart(int fingerCount, int imageType, boolean bAutoCapture);


	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintCaptureAbort
	// Parameter:
	//    input:  
	//			  
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: To Stop capture process
	//------------------------------------------------------------------- 
	int tbsFingerPrintCaptureAbort();


	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintForceCapture
	// Parameter:
	//    input:  
	//			  
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Forcefully complete process
	//------------------------------------------------------------------- 
	int tbsFingerPrintForceCapture();

	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintCalibrate
	// Parameter:
	//    input:  
	//			  
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Will be used for Calibration of fingerprint scanner
	//------------------------------------------------------------------- 
	int tbsFingerPrintCalibrate();

	//------------------------------------------------------------------- 
	// Function name: TatvikFingerPrintGetLastErrorCode
	// Parameter:
	//    input:  
	//			  
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Returns last error Code
	//------------------------------------------------------------------- 
	int tbsFingerPrintGetLastErrorCode();


	//------------------------------------------------------------------- 
	// Function name: TatvikIsScannerConnected
	// Parameter:
	//    input:  
	//			  
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Checks whether scanner is connected or not
	//------------------------------------------------------------------- 
	int tbsIsScannerConnected(); 
	
	//------------------------------------------------------------------- 
	// Function name: TatvikGetDeviceSerialNumber
	// Parameter:
	//    input: 
	//	  in/out:    serialNumber - SerialNumber of device
	//			  
	// Return:
	//    Success : TATVIK_STATUS_OK
	//	  Failure : Non Zero Value
	// Description: Retrieves the serial number of Device
	//------------------------------------------------------------------- 
	int tbsGetDeviceSerialNumber(byte[] serialNumber);	
}

