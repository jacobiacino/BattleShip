import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;

import acm.graphics.GImage;

import acm.breadboards.BreadboardComboBoxModel;
import acm.breadboards.ComboBoxWithButtonsBreadboard;


public class PhotoProcessor extends ComboBoxWithButtonsBreadboard {
	
	public final int NO_FILTER = 0;
	public final int GRAYSCALE = 1;
	public final int NEGATIVE = 2;
	public final int BLURRED = 3;
	
	GImage originalImage_;
	GImage currentImage_;

	public void run() {
		
		//set up the breadboard with instructions, labels, etc...
		this.getLabel().setText("Filters:");
		
		this.getTextArea().setText("This program will apply one of four filters to an image. " +
		                           "Click 'Load' and select an image file with which to work. " +
				                   "Then select an appropriate filter, and click 'Apply' to see the result.");
		
		String[] options = {"No Filter", "Grayscale", "Negative", "Blurred"};
		BreadboardComboBoxModel model = new BreadboardComboBoxModel(options);
		this.getComboBox().setModel(model);
		
		this.getButton2().setText("Load...");
		
		this.getButton1().setText("Apply Filter");
	}
	
	public void onButton1Click() { //Apply Filter button was clicked...
		
		//remove the old image
		this.remove(currentImage_);
		
		//add the newly filtered image
		switch (this.getComboBox().getSelectedIndex()) {
		case NO_FILTER: this.add(originalImage_);
						currentImage_ = originalImage_;
						break;
		case GRAYSCALE: currentImage_ = makeGrayscale(currentImage_);
						this.add(currentImage_);
						break;
		case NEGATIVE:  currentImage_ = makeNegative(currentImage_);
						this.add(currentImage_);
						break;
		case BLURRED: 	currentImage_ = makeBlurred(currentImage_);
						this.add(currentImage_);
						break;
		}
		
	}
	
	public void onButton2Click() { //Load button was clicked
		
		//get the name of an image file the user has selected
		final JFileChooser fileChooser = new JFileChooser();
		int fileChooserAction = fileChooser.showOpenDialog(this);
		
		if (fileChooserAction == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String fileName = file.getPath();
			
			//construct GImages for the original picture, and the grayscale, negative, and blurred versions
			originalImage_ = new GImage(fileName);
			currentImage_ = originalImage_;
			this.add(originalImage_);
			
			//adjust the window size so that the central canvas area fits the picture
			int northHeight = this.getRegionPanel(NORTH).getHeight();
			int southHeight = this.getRegionPanel(SOUTH).getHeight();
			this.setSize((int) originalImage_.getWidth(), (int) originalImage_.getHeight() + northHeight + southHeight);
		}
	}
	
	public GImage makeGrayscale(GImage image) {
		int[][] pixelArray = image.getPixelArray();
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[i].length; j++) {
				int redVal = GImage.getRed(pixelArray[i][j]);
				int greenVal = GImage.getGreen(pixelArray[i][j]);
				int blueVal = GImage.getBlue(pixelArray[i][j]);
				int newVal = (int)((.3 * redVal) + (.59 * greenVal) + (.11 * blueVal));
				pixelArray[i][j] = GImage.createRGBPixel(newVal, newVal, newVal);
			}
		}
		return new GImage(pixelArray);
	}
	
	public GImage makeNegative(GImage image) {
		int[][] pixelArray = image.getPixelArray();
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[i].length; j++) {
				int redVal = GImage.getRed(pixelArray[i][j]);
				int greenVal = GImage.getGreen(pixelArray[i][j]);
				int blueVal = GImage.getBlue(pixelArray[i][j]);
				pixelArray[i][j] = GImage.createRGBPixel(255 - redVal, 255 - greenVal, 255 - blueVal);
			}
		}
		return new GImage(pixelArray);
	}
	
	public GImage makeBlurred(GImage image) {
		int[][] pixelArray = image.getPixelArray();
		int[][] newPixelArray = new int[pixelArray.length][pixelArray[0].length];
		for (int i = 4; i < pixelArray.length - 4; i++) {
			for (int j = 4; j < pixelArray[i].length - 4; j++) {
				newPixelArray[i][j] = GImage.createRGBPixel(getAvgOfNearby(1, i, j, pixelArray),
						getAvgOfNearby(2, i, j, pixelArray),
						getAvgOfNearby(3, i, j, pixelArray));
			}
		}
		return new GImage(newPixelArray);
	}
	
	public int getAvgOfNearby(int color, int pixelRow, int pixelColumn, int[][] pixelArray) {
		if (color == 1) {
			return (GImage.getRed(pixelArray[pixelRow - 4][pixelColumn - 4])
					+ GImage.getRed(pixelArray[pixelRow - 3][pixelColumn - 4])
					+ GImage.getRed(pixelArray[pixelRow - 4][pixelColumn - 3])
					+ GImage.getRed(pixelArray[pixelRow - 3][pixelColumn - 3])
					+ GImage.getRed(pixelArray[pixelRow - 3][pixelColumn - 2])
					+ GImage.getRed(pixelArray[pixelRow - 2][pixelColumn - 3])
					+ GImage.getRed(pixelArray[pixelRow - 2][pixelColumn - 2])
					+ GImage.getRed(pixelArray[pixelRow - 2][pixelColumn - 1])
					+ GImage.getRed(pixelArray[pixelRow - 1][pixelColumn - 2])
					+ GImage.getRed(pixelArray[pixelRow - 1][pixelColumn - 1])
					+ GImage.getRed(pixelArray[pixelRow - 1][pixelColumn])
					+ GImage.getRed(pixelArray[pixelRow - 1][pixelColumn + 1])
					+ GImage.getRed(pixelArray[pixelRow][pixelColumn - 1]) 
					+ GImage.getRed(pixelArray[pixelRow][pixelColumn])
					+ GImage.getRed(pixelArray[pixelRow][pixelColumn + 1])
					+ GImage.getRed(pixelArray[pixelRow + 1][pixelColumn - 1])
					+ GImage.getRed(pixelArray[pixelRow + 1][pixelColumn])
					+ GImage.getRed(pixelArray[pixelRow + 1][pixelColumn + 1])
					+ GImage.getRed(pixelArray[pixelRow + 1][pixelColumn + 2])
					+ GImage.getRed(pixelArray[pixelRow + 2][pixelColumn + 1])
					+ GImage.getRed(pixelArray[pixelRow + 2][pixelColumn + 2])
					+ GImage.getRed(pixelArray[pixelRow + 3][pixelColumn + 2])
					+ GImage.getRed(pixelArray[pixelRow + 2][pixelColumn + 3])
					+ GImage.getRed(pixelArray[pixelRow + 3][pixelColumn + 3])
					+ GImage.getRed(pixelArray[pixelRow + 4][pixelColumn + 3])
					+ GImage.getRed(pixelArray[pixelRow + 3][pixelColumn + 4])
					+ GImage.getRed(pixelArray[pixelRow + 4][pixelColumn + 4])) / 27;
		} else if (color == 2) {
			return (GImage.getGreen(pixelArray[pixelRow - 4][pixelColumn - 4])
					+ GImage.getGreen(pixelArray[pixelRow - 3][pixelColumn - 4])
					+ GImage.getGreen(pixelArray[pixelRow - 4][pixelColumn - 3])
					+ GImage.getGreen(pixelArray[pixelRow - 3][pixelColumn - 3])
					+ GImage.getGreen(pixelArray[pixelRow - 3][pixelColumn - 2])
					+ GImage.getGreen(pixelArray[pixelRow - 2][pixelColumn - 3])
					+ GImage.getGreen(pixelArray[pixelRow - 2][pixelColumn - 2])
					+ GImage.getGreen(pixelArray[pixelRow - 2][pixelColumn - 1])
					+ GImage.getGreen(pixelArray[pixelRow - 1][pixelColumn - 2])
					+ GImage.getGreen(pixelArray[pixelRow - 1][pixelColumn - 1])
					+ GImage.getGreen(pixelArray[pixelRow - 1][pixelColumn])
					+ GImage.getGreen(pixelArray[pixelRow - 1][pixelColumn + 1])
					+ GImage.getGreen(pixelArray[pixelRow][pixelColumn - 1]) 
					+ GImage.getGreen(pixelArray[pixelRow][pixelColumn])
					+ GImage.getGreen(pixelArray[pixelRow][pixelColumn + 1])
					+ GImage.getGreen(pixelArray[pixelRow + 1][pixelColumn - 1])
					+ GImage.getGreen(pixelArray[pixelRow + 1][pixelColumn])
					+ GImage.getGreen(pixelArray[pixelRow + 1][pixelColumn + 1])
					+ GImage.getGreen(pixelArray[pixelRow + 1][pixelColumn + 2])
					+ GImage.getGreen(pixelArray[pixelRow + 2][pixelColumn + 1])
					+ GImage.getGreen(pixelArray[pixelRow + 2][pixelColumn + 2])
					+ GImage.getGreen(pixelArray[pixelRow + 3][pixelColumn + 2])
					+ GImage.getGreen(pixelArray[pixelRow + 2][pixelColumn + 3])
					+ GImage.getGreen(pixelArray[pixelRow + 3][pixelColumn + 3])
					+ GImage.getGreen(pixelArray[pixelRow + 4][pixelColumn + 3])
					+ GImage.getGreen(pixelArray[pixelRow + 3][pixelColumn + 4])
					+ GImage.getGreen(pixelArray[pixelRow + 4][pixelColumn + 4])) / 27;
		} else if (color == 3) {
			return (GImage.getBlue(pixelArray[pixelRow - 4][pixelColumn - 4])
					+ GImage.getBlue(pixelArray[pixelRow - 3][pixelColumn - 4])
					+ GImage.getBlue(pixelArray[pixelRow - 4][pixelColumn - 3])
					+ GImage.getBlue(pixelArray[pixelRow - 3][pixelColumn - 3])
					+ GImage.getBlue(pixelArray[pixelRow - 3][pixelColumn - 2])
					+ GImage.getBlue(pixelArray[pixelRow - 2][pixelColumn - 3])
					+ GImage.getBlue(pixelArray[pixelRow - 2][pixelColumn - 2])
					+ GImage.getBlue(pixelArray[pixelRow - 2][pixelColumn - 1])
					+ GImage.getBlue(pixelArray[pixelRow - 1][pixelColumn - 2])
					+ GImage.getBlue(pixelArray[pixelRow - 1][pixelColumn - 1])
					+ GImage.getBlue(pixelArray[pixelRow - 1][pixelColumn])
					+ GImage.getBlue(pixelArray[pixelRow - 1][pixelColumn + 1])
					+ GImage.getBlue(pixelArray[pixelRow][pixelColumn - 1]) 
					+ GImage.getBlue(pixelArray[pixelRow][pixelColumn])
					+ GImage.getBlue(pixelArray[pixelRow][pixelColumn + 1])
					+ GImage.getBlue(pixelArray[pixelRow + 1][pixelColumn - 1])
					+ GImage.getBlue(pixelArray[pixelRow + 1][pixelColumn])
					+ GImage.getBlue(pixelArray[pixelRow + 1][pixelColumn + 1])
					+ GImage.getBlue(pixelArray[pixelRow + 1][pixelColumn + 2])
					+ GImage.getBlue(pixelArray[pixelRow + 2][pixelColumn + 1])
					+ GImage.getBlue(pixelArray[pixelRow + 2][pixelColumn + 2])
					+ GImage.getBlue(pixelArray[pixelRow + 3][pixelColumn + 2])
					+ GImage.getBlue(pixelArray[pixelRow + 2][pixelColumn + 3])
					+ GImage.getBlue(pixelArray[pixelRow + 3][pixelColumn + 3])
					+ GImage.getBlue(pixelArray[pixelRow + 4][pixelColumn + 3])
					+ GImage.getBlue(pixelArray[pixelRow + 3][pixelColumn + 4])
					+ GImage.getBlue(pixelArray[pixelRow + 4][pixelColumn + 4])) / 27;
		}
		return 0;
	}
}
