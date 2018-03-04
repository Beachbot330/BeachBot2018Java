package org.usfirst.frc330.wpilibj;

import java.util.Arrays;

public class MedianFilter implements Filter {
	double samples[];
	int counter = 0;
	double filteredValue;
	int numberOfSamples;
	
	/**
	 * Constructs a median filter with the given number of samples. 
	 * @param numberOfSamples
	 */
	public MedianFilter(int numberOfSamples) {
		if (numberOfSamples % 2 != 1)
			throw new IllegalArgumentException("Samples must be odd");
		
		samples = new double[numberOfSamples];
		this.numberOfSamples = numberOfSamples;
	}

	@Override
	public double updateFilteredValue(double measurement) {
		samples[counter] = measurement;
		double samp[] = samples.clone();
		Arrays.sort(samp);
		filteredValue = samp[numberOfSamples/2];
		counter++;
		if (counter > samples.length - 1) {
			counter = 0;
		}
		
		return filteredValue;
	}

	@Override
	public double getFilteredValue() {
		return filteredValue;
	}
	
	
}
