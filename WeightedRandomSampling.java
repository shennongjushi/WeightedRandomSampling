import java.util.*;
public class WeightedRandomSampling {
	private Random random = new Random();
	public void getRandom(double[] weights, int sample){
		double totalWeight = 0;
		double[] sum = new double[weights.length + 1];
		int idx = 0;
		for (double weight : weights) {
			totalWeight += weight;
			sum[idx + 1] += sum[idx] + weight;
			idx++;
		}
		for (int i = 0; i < sample; i++) {
			int sampleIdx = getItem(totalWeight, sum);
			System.out.printf("sample %d, the item with weight %g\n", i, weights[sampleIdx]);
		}
	}

	private int getItem(double totalWeight, double[] sum) {
		double next = random.nextDouble() * totalWeight;
		int idx = Arrays.binarySearch(sum, next);
		if (idx < 0) {
			idx = -(idx + 1);
		}
		return idx == 0 ? 0 : idx - 1;
	}

	public static void main(String[] args){
		WeightedRandomSampling weightedRandomSampling = new WeightedRandomSampling();
		//Test
		double[] inputs = {0.1, 0.23, 0.51, 0.4, 1.2, 0.9};
		weightedRandomSampling.getRandom(inputs, 100);
	}
}
