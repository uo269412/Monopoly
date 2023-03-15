package uo.mp.project.monopoly.check;

public class Arguments {

	public static void check(boolean condition) {
		if ( condition == true ) return;
		throw new IllegalArgumentException("The value must be positive");
	}
}
