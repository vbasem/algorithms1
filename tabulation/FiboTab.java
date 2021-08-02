package tabulation;

public class FiboTab {

    public static void main(String[] args)  {
        FiboTab solver = new FiboTab();

        System.out.println("fibo : " + solver.getFibo(100));
    }

	public double getFibo(int n) {

		double[] tab =  new double[n+1];
		tab[1] = 1;

		for (int i = 0 ; i < n - 1; i++)  {
			tab[i+2] += tab[i];
			tab[i +1] += tab[i];
		}

		tab[tab.length-1] += 		tab[tab.length-2];

		return tab[tab.length -1];

	}
}
