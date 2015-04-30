package distance;


/**
 * 
 * @author Francesco Gaetano - email: f.gaetano90@gmail.com
 * @author Luigi Lomasto - email: luigilomasto@gmail.com 
 * 
 * @version 1.1
 * 
 * Date: February, 15 2015
 */

public class KLDLogN implements DistanceMeasure  {

	@Override
	public boolean hasInternalProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	public double DKL(double p, double q) {
		double dis = 0.0;
		if (p != 0 && q!=0)
		     dis= p*(Math.log(p / q));
		return dis;
	}
	
	@Override
	public double computePartialDistance(Parameters param) {
		
		int c1 = param.getC1();
		int c2 = param.getC2();
		
		double dkl1 = DKL(c1, c2);
		
		return dkl1;
	}

	@Override
	public double distanceOperator(double partialDist, double currDist) {
		return partialDist + currDist;
	}

	@Override
	public double initDistance() {
		return 0;
	}

	@Override
	public double finalizeDistance(double dist, int numEl) {
		return dist;
	}

	@Override
	public boolean isSymmetricMeasure() {
		return false;
	}

	@Override
	public String getName() {
			return "KLDLogN";
	}
	
	public boolean isCompatibile(String pattern){
	
			return true;
		
	}

}
