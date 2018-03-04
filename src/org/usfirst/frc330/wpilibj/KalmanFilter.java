package org.usfirst.frc330.wpilibj;

/**
 * A simplified one dimensional Kalman filter implementation - actually a single variable low pass filter ;-)
 * Based on: http://interactive-matter.eu/blog/2009/12/18/filtering-sensor-data-with-a-kalman-filter/ and
 * https://github.com/bachagas/Kalman/blob/master/Kalman.h
 * @author Joe
 *
 */
public class KalmanFilter implements Filter {

	//Kalman filter variables
	private  double processNoise; //process noise covariance
	private  double measurementNoise; //measurement noise covariance
	private  double estimatedMeasurement; //value
	private  double estimatedError; //estimation error covariance
	private  double kalmanGain; //kalman gain

	public  KalmanFilter(double process_noise, double sensor_noise, double estimated_error, double intial_value) {
		/* The variables are x for the filtered value, q for the process noise, 
	         r for the sensor noise, p for the estimated error and k for the Kalman Gain. 
	         The state of the filter is defined by the values of these variables.

	         The initial values for p is not very important since it is adjusted
	         during the process. It must be just high enough to narrow down.
	         The initial value for the readout is also not very important, since
	         it is updated during the process.
	         But tweaking the values for the process noise and sensor noise
	         is essential to get clear readouts.

	         For large noise reduction, you can try to start from: (see http://interactive-matter.eu/blog/2009/12/18/filtering-sensor-data-with-a-kalman-filter/ )
	         q = 0.125
	         r = 32
	         p = 1023 //"large enough to narrow down"
	         e.g.
	         myVar = Kalman(0.125,32,1023,0);
		 */
		this.processNoise = process_noise;
		this.measurementNoise = sensor_noise;
		this.estimatedError = estimated_error;
		this.estimatedMeasurement = intial_value; //x will hold the iterated filtered value
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc330.wpilibj.Filter#updateFilteredValue(double)
	 */
	@Override
	public double updateFilteredValue(double measurement) {
		/* Updates and gets the current measurement value */
		//prediction update
		//omit x = x
		estimatedError = estimatedError + processNoise;

		//measurement update
		kalmanGain = estimatedError / (estimatedError + measurementNoise);
		estimatedMeasurement = estimatedMeasurement + kalmanGain * (measurement - estimatedMeasurement);
		estimatedError = (1 - kalmanGain) * estimatedError;

		return estimatedMeasurement;
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc330.wpilibj.Filter#getFilteredValue()
	 */
	@Override
	public double getFilteredValue() {
		return estimatedMeasurement;
	}

	public void setParameters(double process_noise, double sensor_noise, double estimated_error) {
		processNoise = process_noise;
		measurementNoise = sensor_noise;
		estimatedError = estimated_error;
	}

	public void setParameters(double process_noise, double sensor_noise) {
		processNoise = process_noise;
		measurementNoise = sensor_noise;
	}
	
	public void setParameters(double process_noise) {
		processNoise = process_noise;
	}

	public double getProcessNoise() {
		return processNoise;
	}

	public double getSensorNoise() {
		return measurementNoise;
	}

	public double getEstimatedError() {
		return estimatedError;
	}

}
