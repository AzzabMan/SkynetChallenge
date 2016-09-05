import java.util.*;
import java.io.*;
import java.math.*;
import java.util.concurrent.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // the total number of nodes in the level,
								// including the gateways
		int L = in.nextInt(); // the number of links
		int E = in.nextInt(); // the number of exit gateways
		Set<Integer> gateways = new HashSet<>();
		List<String> allLinks = new CopyOnWriteArrayList<>();
		for (int i = 0; i < L; i++) {
			int N1 = in.nextInt(); // N1 and N2 defines a link between these
									// nodes
			int N2 = in.nextInt();
			allLinks.add(N1 + "#" + N2);
		}
		for (int i = 0; i < E; i++) {
			int EI = in.nextInt(); // the index of a gateway node
			gateways.add(EI);
		}

		// game loop
		while (true) {
			int SI = in.nextInt(); // The index of the node on which the Skynet
									// agent is positioned this turn
			boolean cut = false; // this will allow to know whether a link was cut
			System.err.println("Debug messages..." + SI);
			
			// loop on gateways, 
			// if there is a link between a gateway and the position of the agent then we cut the link
			for (Integer gw : gateways) {
				if (allLinks.contains(gw + "#" + SI) || allLinks.contains(SI + "#" + gw)) { // we test on booth ways
				    // cut link
					System.out.println(gw + " " + SI);
					// remove link for optimisation
					allLinks.remove(gw+"#"+SI);
					allLinks.remove(SI+"#"+gw);
					cut = true;
					break;
				}
			}
			
			// if we where not able to cut a link before, then we cut a link for a gateway
			if (!cut) {
			    System.err.println("Debug messages..." + allLinks);
			    int q = 0;
			    boolean stop = false;
			    while(q < allLinks.size() && !stop){
				    String link = allLinks.get(q);
					for (Integer gw : gateways) {
					        System.err.println("Debug messages..." + link);
					    
						    System.err.println('#' + String.valueOf(gw));
						    System.err.println(String.valueOf(gw)+'#');
						    System.err.println("Debug messages..." + gw);
						    
						if (link.contains('#' + String.valueOf(gw)) || link.contains(String.valueOf(gw)+'#') ) {
						    // cut link
							System.out.println(link.replace('#', ' '));
							// remove link for optimisation
							allLinks.remove(link);
							stop = true;
							break;
						}
					}
					q++;
				}
			}
		}
	}
}