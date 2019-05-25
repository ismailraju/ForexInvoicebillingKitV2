package com.istl.iris.controller;


import com.istl.iris.controller.MainController;
import com.istlbd.iris.cmi.CMILibrary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class OptionTabController {
	
	private MainController _main;
	
	@FXML private CheckBox OptionTabLowCpuCbx;
	@FXML private CheckBox OptionTabAutoSaveCbx;
	@FXML private CheckBox OptionTabGazeCbx;
	@FXML private CheckBox OptionTabDisplayCbx;
	@FXML private CheckBox OptionTabDisplayBoundaryCbx;	
	@FXML private CheckBox OptionTabLeftEyeCaptureCbx;
	@FXML private CheckBox OptionTabRightEyeCaptureCbx;
	@FXML private Spinner CaptureTabLRMarginSpi;
	@FXML private Spinner CaptureTabTBMarginSpi;
	@FXML private Spinner CaptureTabContinuousTimeIntervalSpi;
	@FXML private Spinner CaptureTabXYMovementSpi;
	@FXML private Spinner CaptureTabDisplayFrameRateSpi;
	//@FXML private TextField OptionTabLeftIntensityTxf;
	//@FXML private TextField OptionTabRightIntensityTxf;
	//@FXML private TextField OptionTabLeftExposureTxf;
	//@FXML private TextField OptionTabRightExposureTxf;
	
	public void init(MainController mainController) {
		_main = mainController;
		
		OptionTabAutoSaveCbx.setSelected(false);
		OptionTabGazeCbx.setSelected(true);
		OptionTabLeftEyeCaptureCbx.setSelected(true);
		OptionTabRightEyeCaptureCbx.setSelected(true);
		OptionTabLowCpuCbx.setSelected(false);
		OptionTabDisplayCbx.setSelected(true);
		OptionTabDisplayBoundaryCbx.setSelected(true);
		
		CaptureTabLRMarginSpi.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.6, 0.1));
		CaptureTabTBMarginSpi.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.2, 0.1));
		CaptureTabXYMovementSpi.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-1.0, 20.0, 1.0, 0.1));
		CaptureTabContinuousTimeIntervalSpi.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 5, 1));
		CaptureTabDisplayFrameRateSpi.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 30, 1, 1));
                

		CaptureTabLRMarginSpi.getEditor().textProperty().addListener((obs, oldValue, newValue) -> { 
			System.out.println("CaptureTabLRMarginSpi : " + newValue);
			_main.g_CaptureLRIrisMargin = Double.parseDouble(newValue);
		});

		CaptureTabTBMarginSpi.getEditor().textProperty().addListener((obs, oldValue, newValue) -> { 
			System.out.println("CaptureTabTBMarginSpi : " + newValue);
			_main.g_CaptureTBIrisMargin = Double.parseDouble(newValue);
		});
		
		CaptureTabXYMovementSpi.getEditor().textProperty().addListener((obs, oldValue, newValue) -> { 
			System.out.println("CaptureTabXYMovementSpi : " + newValue);
			_main.g_ImageXYMovement = Double.parseDouble(newValue);
		});
		
		CaptureTabContinuousTimeIntervalSpi.getEditor().textProperty().addListener((obs, oldValue, newValue) -> { 
			System.out.println("CaptureTabContinuousTimeIntervalSpi : " + newValue);
			_main.g_ContinuosCaptureTimeInterval = Integer.parseInt(newValue);
		});
		
		CaptureTabDisplayFrameRateSpi.getEditor().textProperty().addListener((obs, oldValue, newValue) -> { 
			System.out.println("CaptureTabDisplayFrameRateSpi : " + newValue);
			_main.g_DisplayFrameRate = Integer.parseInt(newValue);
		});
	}
	
	@FXML private void OptionTabBtnLowBandwidthCpuClicked(ActionEvent event) {
		if(OptionTabLowCpuCbx.isSelected())
			_main.g_CaptureLowBandwidth = true;
		else
			_main.g_CaptureLowBandwidth = false;
	}
	
	@FXML private void OptionTabBtnAutosaveClicked(ActionEvent event) {
		if(OptionTabAutoSaveCbx.isSelected())
			_main.g_CaptureAutoSave = true;
		else
			_main.g_CaptureAutoSave = false;
	}
	
	@FXML private void OptionTabBtnGazedetectionClicked(ActionEvent event) {
		if(OptionTabGazeCbx.isSelected())
			_main.g_CaptureGazeDetection = true;
		else
			_main.g_CaptureGazeDetection = false;
	}

	@FXML private void OptionTabBtnDisplayClicked(ActionEvent event) {
		if(OptionTabDisplayCbx.isSelected())
			_main.g_DisplayFrame = true;
		else
			_main.g_DisplayFrame = false;
		
		if(_main.g_DisplayFrame == false)
		{
			OptionTabDisplayBoundaryCbx.setSelected(false);
			_main.g_DisplayIrisBoundaryInPreview = false;
		}
		else
		{
			OptionTabDisplayBoundaryCbx.setSelected(true);
			_main.g_DisplayIrisBoundaryInPreview = true;			
		}
	}

	@FXML private void OptionTabBtnDisplayIrisBoundaryClicked(ActionEvent event) {
		if(OptionTabDisplayBoundaryCbx.isSelected())
			_main.g_DisplayIrisBoundaryInPreview = true;
		else
			_main.g_DisplayIrisBoundaryInPreview = false;
	}

	@FXML private void OptionTabBtnCaptureEyesClicked(ActionEvent event) {
		System.out.println("OptionTabBtnCaptureEyesClicked");
		
		
		
		if(OptionTabLeftEyeCaptureCbx.isSelected() && OptionTabRightEyeCaptureCbx.isSelected())
			_main.g_CaptureEyes = CMILibrary.CMI_BOTH_EYES;
		else if (OptionTabLeftEyeCaptureCbx.isSelected() && !OptionTabRightEyeCaptureCbx.isSelected())
			_main.g_CaptureEyes = CMILibrary.CMI_LEFT_EYE;
		else if (!OptionTabLeftEyeCaptureCbx.isSelected() && OptionTabRightEyeCaptureCbx.isSelected())
			_main.g_CaptureEyes = CMILibrary.CMI_RIGHT_EYE;
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("CMITECH");
			alert.setHeaderText("Error!");
			alert.setContentText("please select one or two items");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			    	OptionTabLeftEyeCaptureCbx.setSelected(true);
			    	OptionTabRightEyeCaptureCbx.setSelected(true);
			    	
			    	_main.g_CaptureEyes = CMILibrary.CMI_BOTH_EYES;
			    }
			});
		}
	}
}
