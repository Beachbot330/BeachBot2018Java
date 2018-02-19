package org.usfirst.frc330.wpilibj;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

/**
 * Class for communicating with ST Microelectronics V16180X laser range finder
 * sensor. https://www.pololu.com/product/2489
 * @author Joe
 *
 */
public class STvl6180x {

	/**
	 * RANGE_SCALER values for 1x, 2x, 3x scaling - see STSW-IMG003 core/src/vl6180x_api.c (ScalerLookUP[])
	 */
	public static final short ScalerValues[] = {0, 253, 127, 84};
	public static final short IDENTIFICATION__MODEL_ID              = 0x000;
	public static final short IDENTIFICATION__MODEL_REV_MAJOR       = 0x001;
	public static final short IDENTIFICATION__MODEL_REV_MINOR       = 0x002;
	public static final short IDENTIFICATION__MODULE_REV_MAJOR      = 0x003;
	public static final short IDENTIFICATION__MODULE_REV_MINOR      = 0x004;
	public static final short IDENTIFICATION__DATE_HI               = 0x006;
	public static final short IDENTIFICATION__DATE_LO               = 0x007;
	public static final short IDENTIFICATION__TIME                  = 0x008; // 16-bit

	public static final short SYSTEM__MODE_GPIO0                    = 0x010;
	public static final short SYSTEM__MODE_GPIO1                    = 0x011;
	public static final short SYSTEM__HISTORY_CTRL                  = 0x012;
	public static final short SYSTEM__INTERRUPT_CONFIG_GPIO         = 0x014;
	public static final short SYSTEM__INTERRUPT_CLEAR               = 0x015;
	public static final short SYSTEM__FRESH_OUT_OF_RESET            = 0x016;
	public static final short SYSTEM__GROUPED_PARAMETER_HOLD        = 0x017;

	public static final short SYSRANGE__START                       = 0x018;
	public static final short SYSRANGE__THRESH_HIGH                 = 0x019;
	public static final short SYSRANGE__THRESH_LOW                  = 0x01A;
	public static final short SYSRANGE__INTERMEASUREMENT_PERIOD     = 0x01B;
	public static final short SYSRANGE__MAX_CONVERGENCE_TIME        = 0x01C;
	public static final short SYSRANGE__CROSSTALK_COMPENSATION_RATE = 0x01E; // 16-bit
	public static final short SYSRANGE__CROSSTALK_VALID_HEIGHT      = 0x021;
	public static final short SYSRANGE__EARLY_CONVERGENCE_ESTIMATE  = 0x022; // 16-bit
	public static final short SYSRANGE__PART_TO_PART_RANGE_OFFSET   = 0x024;
	public static final short SYSRANGE__RANGE_IGNORE_VALID_HEIGHT   = 0x025;
	public static final short SYSRANGE__RANGE_IGNORE_THRESHOLD      = 0x026; // 16-bit
	public static final short SYSRANGE__MAX_AMBIENT_LEVEL_MULT      = 0x02C;
	public static final short SYSRANGE__RANGE_CHECK_ENABLES         = 0x02D;
	public static final short SYSRANGE__VHV_RECALIBRATE             = 0x02E;
	public static final short SYSRANGE__VHV_REPEAT_RATE             = 0x031;

	public static final short SYSALS__START                         = 0x038;
	public static final short SYSALS__THRESH_HIGH                   = 0x03A;
	public static final short SYSALS__THRESH_LOW                    = 0x03C;
	public static final short SYSALS__INTERMEASUREMENT_PERIOD       = 0x03E;
	public static final short SYSALS__ANALOGUE_GAIN                 = 0x03F;
	public static final short SYSALS__INTEGRATION_PERIOD            = 0x040;

	public static final short RESULT__RANGE_STATUS                  = 0x04D;
	public static final short RESULT__ALS_STATUS                    = 0x04E;
	public static final short RESULT__INTERRUPT_STATUS_GPIO         = 0x04F;
	public static final short RESULT__ALS_VAL                       = 0x050; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_0              = 0x052; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_1              = 0x054; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_2              = 0x056; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_3              = 0x058; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_4              = 0x05A; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_5              = 0x05C; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_6              = 0x05E; // 16-bit
	public static final short RESULT__HISTORY_BUFFER_7              = 0x060; // 16-bit
	public static final short RESULT__RANGE_VAL                     = 0x062;
	public static final short RESULT__RANGE_RAW                     = 0x064;
	public static final short RESULT__RANGE_RETURN_RATE             = 0x066; // 16-bit
	public static final short RESULT__RANGE_REFERENCE_RATE          = 0x068; // 16-bit
	public static final short RESULT__RANGE_RETURN_SIGNAL_COUNT     = 0x06C; // 32-bit
	public static final short RESULT__RANGE_REFERENCE_SIGNAL_COUNT  = 0x070; // 32-bit
	public static final short RESULT__RANGE_RETURN_AMB_COUNT        = 0x074; // 32-bit
	public static final short RESULT__RANGE_REFERENCE_AMB_COUNT     = 0x078; // 32-bit
	public static final short RESULT__RANGE_RETURN_CONV_TIME        = 0x07C; // 32-bit
	public static final short RESULT__RANGE_REFERENCE_CONV_TIME     = 0x080; // 32-bit

	public static final short RANGE_SCALER                          = 0x096; // 16-bit - see STSW-IMG003 core/inc/vl6180x_def.h

	public static final short READOUT__AVERAGING_SAMPLE_PERIOD      = 0x10A;
	public static final short FIRMWARE__BOOTUP                      = 0x119;
	public static final short FIRMWARE__RESULT_SCALER               = 0x120;
	public static final short I2C_SLAVE__DEVICE_ADDRESS             = 0x212;
	public static final short INTERLEAVED_MODE__ENABLE              = 0x2A3;

	public static final byte  DEFAULT_I2C_ADDRESS                   = 0x29;

	private I2C i2c;

	public STvl6180x() {
		this(I2C.Port.kMXP, DEFAULT_I2C_ADDRESS);
	}

	public STvl6180x(I2C.Port port, int address) {
		i2c = new I2C(port, address);
		this.address = (byte) address;
		init();
	}

	byte address = DEFAULT_I2C_ADDRESS;
	byte scaling = 0;
	byte ptp_offset = 0;
	double io_timeout = 0;
	boolean did_timeout = false;

	/**
	 * Initialize sensor with settings from ST application note AN4545, section 9 -
	 * "Mandatory : private registers"
	 */
	public void init()
	{
		// Store part-to-part range offset so it can be adjusted if scaling is changed
		ptp_offset = readReg(SYSRANGE__PART_TO_PART_RANGE_OFFSET);

		if (readReg(SYSTEM__FRESH_OUT_OF_RESET) == 1)
		{
			scaling = 1;

			writeReg(0x207, 0x01);
			writeReg(0x208, 0x01);
			writeReg(0x096, 0x00);
			writeReg(0x097, 0xFD); // RANGE_SCALER = 253
			writeReg(0x0E3, 0x00);
			writeReg(0x0E4, 0x04);
			writeReg(0x0E5, 0x02);
			writeReg(0x0E6, 0x01);
			writeReg(0x0E7, 0x03);
			writeReg(0x0F5, 0x02);
			writeReg(0x0D9, 0x05);
			writeReg(0x0DB, 0xCE);
			writeReg(0x0DC, 0x03);
			writeReg(0x0DD, 0xF8);
			writeReg(0x09F, 0x00);
			writeReg(0x0A3, 0x3C);
			writeReg(0x0B7, 0x00);
			writeReg(0x0BB, 0x3C);
			writeReg(0x0B2, 0x09);
			writeReg(0x0CA, 0x09);
			writeReg(0x198, 0x01);
			writeReg(0x1B0, 0x17);
			writeReg(0x1AD, 0x00);
			writeReg(0x0FF, 0x05);
			writeReg(0x100, 0x05);
			writeReg(0x199, 0x05);
			writeReg(0x1A6, 0x1B);
			writeReg(0x1AC, 0x3E);
			writeReg(0x1A7, 0x1F);
			writeReg(0x030, 0x00);

			writeReg(SYSTEM__FRESH_OUT_OF_RESET, 0);
		}
		else
		{
			// Sensor has already been initialized, so try to get scaling settings by
			// reading registers.

			short s = readReg16Bit(RANGE_SCALER);

			if      (s == ScalerValues[3]) { scaling = 3; }
			else if (s == ScalerValues[2]) { scaling = 2; }
			else                           { scaling = 1; }

			// Adjust the part-to-part range offset value read earlier to account for
			// existing scaling. If the sensor was already in 2x or 3x scaling mode,
			// precision will be lost calculating the original (1x) offset, but this can
			// be resolved by resetting the sensor and Arduino again.
			ptp_offset *= scaling;
		}
	}

	public void setAddress(byte new_addr)
	{
		writeReg(I2C_SLAVE__DEVICE_ADDRESS, new_addr & 0x7F);
		address = new_addr;
	}

	/**
	 * Configure some settings for the sensor's default behavior from AN4545 -
	 * "Recommended : Public registers" and "Optional: Public registers"
	 *
	 * Note that this function does not set up GPIO1 as an interrupt output as
	 * suggested, though you can do so by calling:
	 * writeReg(SYSTEM__MODE_GPIO1, 0x10);
	 */
	public void configureDefault()
	{
		// "Recommended : Public registers"

		// readout__averaging_sample_period = 48
		writeReg(READOUT__AVERAGING_SAMPLE_PERIOD, 0x30);

		// sysals__analogue_gain_light = 6 (ALS gain = 1 nominal, actually 1.01 according to Table 14 in datasheet)
		writeReg(SYSALS__ANALOGUE_GAIN, 0x46);

		// sysrange__vhv_repeat_rate = 255 (auto Very High Voltage temperature recalibration after every 255 range measurements)
		writeReg(SYSRANGE__VHV_REPEAT_RATE, 0xFF);

		// sysals__integration_period = 99 (100 ms)
		// AN4545 incorrectly recommends writing to register 0x040; 0x63 should go in the lower byte, which is register 0x041.
		writeReg16Bit(SYSALS__INTEGRATION_PERIOD, 0x0063);

		// sysrange__vhv_recalibrate = 1 (manually trigger a VHV recalibration)
		writeReg(SYSRANGE__VHV_RECALIBRATE, 0x01);


		// "Optional: Public registers"

		// sysrange__intermeasurement_period = 9 (100 ms)
		writeReg(SYSRANGE__INTERMEASUREMENT_PERIOD, 0x09);

		// sysals__intermeasurement_period = 49 (500 ms)
		writeReg(SYSALS__INTERMEASUREMENT_PERIOD, 0x31);

		// als_int_mode = 4 (ALS new sample ready interrupt); range_int_mode = 4 (range new sample ready interrupt)
		writeReg(SYSTEM__INTERRUPT_CONFIG_GPIO, 0x24);


		// Reset other settings to power-on defaults

		// sysrange__max_convergence_time = 49 (49 ms)
		writeReg(SYSRANGE__MAX_CONVERGENCE_TIME, 0x31);

		// disable interleaved mode
		writeReg(INTERLEAVED_MODE__ENABLE, 0);

		// reset range scaling factor to 1x
		setScaling((byte) 1);
	}

	/**
	 * Set range scaling factor. The sensor uses 1x scaling by default, giving range
	 * measurements in units of mm. Increasing the scaling to 2x or 3x makes it give
	 * raw values in units of 2 mm or 3 mm instead. In other words, a bigger scaling
	 * factor increases the sensor's potential maximum range but reduces its
	 * resolution.
     *
	 * Implemented using ST's VL6180X API as a reference (STSW-IMG003); see
	 * VL6180x_UpscaleSetScaling() in vl6180x_api.c.
	 * 
	 * @param new_scaling 1-3 (representing 1x through 3x).
	 */
	public void setScaling(byte new_scaling)
	{
		final byte DefaultCrosstalkValidHeight = 20; // default value of SYSRANGE__CROSSTALK_VALID_HEIGHT

		// do nothing if scaling value is invalid
		if (new_scaling < 1 || new_scaling > 3) { return; }

		scaling = new_scaling;
		writeReg16Bit(RANGE_SCALER, ScalerValues[scaling]);

		// apply scaling on part-to-part offset
		writeReg(SYSRANGE__PART_TO_PART_RANGE_OFFSET, ptp_offset / scaling);

		// apply scaling on CrossTalkValidHeight
		writeReg(SYSRANGE__CROSSTALK_VALID_HEIGHT, DefaultCrosstalkValidHeight / scaling);

		// This function does not apply scaling to RANGE_IGNORE_VALID_HEIGHT.

		// enable early convergence estimate only at 1x scaling
		byte rce = readReg(SYSRANGE__RANGE_CHECK_ENABLES);
		rce &= 0xFE;
		if (scaling == 1)
			rce |= 0x01;
		writeReg(SYSRANGE__RANGE_CHECK_ENABLES, rce);
	}

	/**
	 * Performs a single-shot ranging measurement. This method blocks until the reading
	 * is complete and as such should be used carefully.
	 * @return the distance in mm
	 */
	byte readRangeSingle()
	{
		writeReg(SYSRANGE__START, 0x01);
		double millis_start = Timer.getFPGATimestamp();
		while ((readReg(RESULT__INTERRUPT_STATUS_GPIO) & 0x04) == 0)
		{
			if (io_timeout > 0 && (Timer.getFPGATimestamp() - millis_start) > io_timeout)
			{
				did_timeout = true;
				return (byte) 255;
			}
		}

		byte range = readReg(RESULT__RANGE_VAL);
		writeReg(SYSTEM__INTERRUPT_CLEAR, 0x01);

		return range;
	}

	/**
	 * Performs a single-shot ambient light measurement. This method blocks until
	 * the reading is complete and as such should be used carefully
	 * @return ???
	 */
	short readAmbientSingle()
	{
		writeReg(SYSALS__START, 0x01);
		double millis_start = Timer.getFPGATimestamp();
		if ((readReg(RESULT__INTERRUPT_STATUS_GPIO) & 0x20) == 0)
		{
			if (io_timeout > 0 && (Timer.getFPGATimestamp() - millis_start) > io_timeout)
			{
				did_timeout = true;
				return 0;
			}
		}

		short ambient = readReg16Bit(RESULT__ALS_VAL);
		writeReg(SYSTEM__INTERRUPT_CLEAR, 0x02);

		return ambient;
	}

	/**
	 * Starts continuous ranging measurements with the given period in ms
	 * (10 ms resolution; defaults to 100 ms if not specified).
	 *
	 * The period must be greater than the time it takes to perform a
	 * measurement. See section 2.4.4 ("Continuous mode limits") in the datasheet
	 * for details.
	 * @param period in ms
	 */
	void startRangeContinuous(short period)
	{
		short period_reg = (short) ((period / 10) - 1);
		period_reg = constrain(period_reg, (short)0, (short)254);

		writeReg(SYSRANGE__INTERMEASUREMENT_PERIOD, period_reg);
		writeReg(SYSRANGE__START, 0x03);
	}

	
	/**
	 * Starts continuous ambient light measurements with the given period in ms
	 * (10 ms resolution; defaults to 500 ms if not specified).
	 *
	 * The period must be greater than the time it takes to perform a
	 * measurement. See section 2.4.4 ("Continuous mode limits") in the datasheet
	 * for details.
	 * @param period in ms
	 */
	void startAmbientContinuous(short period)
	{
		short period_reg = (short)((period / 10) - 1);
		period_reg = constrain(period_reg, (short)0, (short)254);

		writeReg(SYSALS__INTERMEASUREMENT_PERIOD, period_reg);
		writeReg(SYSALS__START, 0x03);
	}

	/**
	 * Starts continuous interleaved measurements with the given period in ms
	 * (10 ms resolution; defaults to 500 ms if not specified). In this mode, each
	 * ambient light measurement is immediately followed by a range measurement.
	 *
	 * The datasheet recommends using this mode instead of running "range and ALS
	 * continuous modes simultaneously (i.e. asynchronously)".
	 *
	 * The period must be greater than the time it takes to perform both
	 * measurements. See section 2.4.4 ("Continuous mode limits") in the datasheet
	 * for details.
	 * @param period in ms
	 */
	void startInterleavedContinuous(short period)
	{
		short period_reg = (short)((period / 10) - 1);
		period_reg = constrain(period_reg, (short)0, (short)254);

		writeReg(INTERLEAVED_MODE__ENABLE, 1);
		writeReg(SYSALS__INTERMEASUREMENT_PERIOD, period_reg);
		writeReg(SYSALS__START, 0x03);
	}

	// 
	/**
	 * Stops continuous mode. This will actually start a single measurement of range
	 * and/or ambient light if continuous mode is not active, so it's a good idea to
	 * wait a few hundred ms after calling this function to let that complete
	 * before starting continuous mode again or taking a reading.
	 */
	void stopContinuous()
	{
		writeReg(SYSRANGE__START, 0x01);
		writeReg(SYSALS__START, 0x01);

		writeReg(INTERLEAVED_MODE__ENABLE, 0);
	}

	byte prevRangeReading = Byte.MAX_VALUE;
	// Returns a range reading when continuous mode is activated
	// (readRangeSingle() also calls this function after starting a single-shot
	// range measurement)
	/**
	 * Returns a range reading when continuous mode is activated
	 * (readRangeSingle() also calls this function after starting a single-shot
	 * range measurement)
	 * @return range in mm
	 */
	byte readRangeContinuous()
	{
		if ((readReg(RESULT__INTERRUPT_STATUS_GPIO) & 0x04) == 0)
		{
			return prevRangeReading;
		}

		byte range = readReg(RESULT__RANGE_VAL);
		writeReg(SYSTEM__INTERRUPT_CLEAR, 0x01);

		return range;
	}

	short prevAmbientReading = Short.MAX_VALUE;
	/**
	 * Returns an ambient light reading when continuous mode is activated
	 * @return ???
	 */
	short readAmbientContinuous()
	{
		if ((readReg(RESULT__INTERRUPT_STATUS_GPIO) & 0x20) == 0)
		{
			return prevAmbientReading;
		}

		short ambient = readReg16Bit(RESULT__ALS_VAL);
		writeReg(SYSTEM__INTERRUPT_CLEAR, 0x02);

		return ambient;
	}

	// Did a timeout occur in one of the read functions since the last call to
	// timeoutOccurred()?
	/**
	 * Did a timeout occur in one of the read functions since the last call to
	 * timeoutOccurred()?
	 * @return whether a timeout occured. 
	 */
	boolean timeoutOccurred()
	{
		boolean tmp = did_timeout;
		did_timeout = false;
		return tmp;
	}

	private byte[] toWrite = new byte[6];
	private byte[] toRead = new byte[4];

	private boolean writeReg(int register, int data) {
		return writeReg((short)register, (byte)data);
	}

	private boolean writeReg(short register, byte data) {
		toWrite[0] = (byte)((register & 0xFF00) >> 8);
		toWrite[1] = (byte)((register & 0x00FF));
		toWrite[2] = data;
		return i2c.writeBulk(toWrite, 3);
	}

	private boolean writeReg16Bit(int register, int data) {
		return writeReg16Bit((short)register, (short)data);
	}

	private boolean writeReg16Bit(short register, short data) {
		toWrite[0] = (byte)((register & 0xFF00) >> 8);
		toWrite[1] = (byte)((register & 0x00FF));
		toWrite[2] = (byte)((data & 0xFF00) >> 8);
		toWrite[3] = (byte)((data & 0x00FF));
		return i2c.writeBulk(toWrite, 4); 
	}

	private boolean writeReg32(int register, int data) {
		return writeReg32((short)register, data);
	}

	private boolean writeReg32(short register, int data) {
		toWrite[0] = (byte)((register & 0xFF00) >> 8);
		toWrite[1] = (byte)((register & 0x00FF));
		toWrite[2] = (byte)((data & 0xFF000000) >> 24);
		toWrite[3] = (byte)((data & 0x00FF0000) >> 16);
		toWrite[4] = (byte)((data & 0x0000FF00) >> 8);
		toWrite[5] = (byte)((data & 0x000000FF));
		return i2c.writeBulk(toWrite, 6); 
	}

	private byte readReg(short register) {
		toWrite[0] = (byte)((register & 0xFF00) >> 8);
		toWrite[1] = (byte)((register & 0x00FF));
		i2c.transaction(toWrite, 2, toRead, 1);
		return toRead[0];
	}

	private short readReg16Bit(short register) {
		short value;
		toWrite[0] = (byte)((register & 0xFF00) >> 8);
		toWrite[1] = (byte)((register & 0x00FF));
		i2c.transaction(toWrite, 2, toRead, 2);
		value = (short)(toRead[0] << 8);
		value |= toRead[1];
		return value;
	}

	private int readReg32(short register) {
		int value;
		toWrite[0] = (byte)((register & 0xFF00) >> 8);
		toWrite[1] = (byte)((register & 0x00FF));
		i2c.transaction(toWrite, 2, toRead, 2);
		value = (int)(toRead[0] << 24);
		value |= (int)(toRead[1] << 16);
		value |= (int)(toRead[2] << 8);
		value |= toRead[3];
		return value;
	}

	/**
	 * Constrains a number to be within a range. This is an implementation of
	 * the arduino method of the same name.
	 * @param x the number to constrain
	 * @param a the lower end of the range
	 * @param b the upper end of the range
	 * @return x: if x is between a and b; a: if x is less than a; b: if x is greater than b
	 */
	private short constrain(short x, short a, short b) {
		if (a > b) throw new IllegalArgumentException("a needs to be < b. A = " + a + " B = " + b);
		if (x<a) return a;
		else if (x>b) return b;
		else return x;
	}

}
