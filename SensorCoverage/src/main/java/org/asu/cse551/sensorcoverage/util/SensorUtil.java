package org.asu.cse551.sensorcoverage.util;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;

public class SensorUtil {

	public static double calculateEuclidianDistance(int x1, int x2, int y1, int y2, int range) {
		int xDiff = x2 - x1;
		int yDiff = y2 - y1;

		int xDiffSqr = (int) Math.pow(xDiff, 2);
		int yDiffSqr = (int) Math.pow(yDiff, 2);

		double distance = Math.sqrt(xDiffSqr+yDiffSqr);
		distance = formatNumber(distance);
		distance = (distance /range) - 1;
		return distance;

	}
	
	public static double formatNumber (double number) {
		DecimalFormat df = new DecimalFormat("#.##");
		String num = df.format(number);
		return Double.parseDouble(num);
	}
	
	public static File getFileFromResources(String fileName) {
		ClassLoader classLoader = SensorUtil.class.getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}
	}
	
	
}

