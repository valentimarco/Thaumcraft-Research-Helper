package de.promolitor.tchelper.types;

import lombok.NonNull;
import lombok.ToString;
import thaumcraft.api.aspects.Aspect;

public class Hexagon {

    public static final int[][] NEIGHBOURS = {{1, 0}, {1, -1}, {0, -1}, {
        -1, 0}, {-1, 1}, {0, 1}};

	public int q;
	public int r;
	public Aspect aspect;
	public boolean visited = false;
	public boolean notDeletable;

	public Hexagon(int q, int r, Aspect aspect) {
		this(q, r, aspect, false);
	}

	public Hexagon(int q, int r, Aspect aspect, boolean notDeletable) {
		this.q = q;
		this.r = r;
		this.aspect = aspect;
		this.notDeletable = notDeletable;
	}

//	public Hexagon getNeighbour(int direction) {
//		int[] d = NEIGHBOURS[direction];
//		return new Hexagon(this.q + d[0], this.r + d[1], "");
//	}

    public int compareDistance(@NonNull Hexagon other) {
        return (Math.abs(this.q - other.q) + Math.abs(this.r - other.r) + Math.abs(this.q + this.r
            - other.q - other.r)) / 2;
    }

    @Override
    public String toString() {
        return "Hexagon{" +
            "q=" + q +
            ", r=" + r +
            ", aspect=" + aspect.getTag() +
            ", visited=" + visited +
            ", notDeletable=" + notDeletable +
            '}';
    }
}
