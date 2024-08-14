import java.io.*;

public class SpeedingTicket {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("speeding.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("speeding.out"));

		String[] line = r.readLine().split(" ");
		int numRoads = Integer.parseInt(line[0]);
		int numSpeeds = Integer.parseInt(line[1]);

		int[] roadLengths = new int[numRoads];
		int[] roadSpeeds = new int[numRoads];

		int roadSum = 0;
		for (int i = 0; i < numRoads; i++) {
			line = r.readLine().split(" ");
			roadSum += Integer.parseInt(line[0]);
			roadLengths[i] = roadSum;
			roadSpeeds[i] = Integer.parseInt(line[1]);
		}

		int[] bessieIntervals = new int[numSpeeds];
		int[] bessieSpeeds = new int[numSpeeds];

		int bessieSum = 0;
		for (int i = 0; i < numSpeeds; i++) {
			line = r.readLine().split(" ");
			bessieSum += Integer.parseInt(line[0]);
			bessieIntervals[i] = bessieSum;
			bessieSpeeds[i] = Integer.parseInt(line[1]);
		}

		int roadCounter = 0;
		int bessieCounter = 0;
		int maxOverLimit = 0;

		while (roadCounter < numRoads && bessieCounter < numSpeeds) {
			if (bessieSpeeds[bessieCounter] - roadSpeeds[roadCounter] > maxOverLimit) {
				maxOverLimit = bessieSpeeds[bessieCounter] - roadSpeeds[roadCounter];
			}

			if (roadLengths[roadCounter] > bessieIntervals[bessieCounter]) {
				bessieCounter++;
			} else if (roadLengths[roadCounter] < bessieIntervals[bessieCounter]) {
				roadCounter++;
			} else {
				bessieCounter++;
				roadCounter++;
			}
		}

		while (roadCounter < numRoads) {
			if (bessieSpeeds[numSpeeds-1] - roadSpeeds[roadCounter] > maxOverLimit) {
				maxOverLimit = bessieSpeeds[bessieCounter] - roadSpeeds[roadCounter];
			}
			roadCounter++;
		}

		while (bessieCounter < numSpeeds) {
			if (bessieSpeeds[bessieCounter] - roadSpeeds[numSpeeds] > maxOverLimit) {
				maxOverLimit = bessieSpeeds[bessieCounter] - roadSpeeds[roadCounter];
			}
			bessieCounter++;
		}

		pw.println(maxOverLimit);

		r.close();
		pw.close();
	}
}
