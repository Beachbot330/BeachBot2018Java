package org.usfirst.frc330.wpilibj;

public interface Filter {

	double updateFilteredValue(double measurement);

	double getFilteredValue();

}