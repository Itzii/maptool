/*
 *  This software copyright by various authors including the RPTools.net
 *  development team, and licensed under the LGPL Version 3 or, at your
 *  option, any later version.
 *
 *  Portions of this software were originally covered under the Apache
 *  Software License, Version 1.1 or Version 2.0.
 *
 *  See the file LICENSE elsewhere in this distribution for license details.
 */

package net.rptools.maptool.model;

/**
 * Given a string describing the type of desired grid, this factory
 * creates and returns an object of the appropriate type.
 * <p>
 * (Ugh.  This really should use an SPI-like factory interface.)
 */
public class GridFactory {
	public static final String HEX_VERT = "Vertical Hex";
	public static final String HEX_HORI = "Horizontal Hex";
	public static final String SQUARE = "Square";
	public static final String NONE = "None";

	public static Grid createGrid(String type) {
		return createGrid(type,true,false);
	}

	public static Grid createGrid(String type, boolean faceEdges, boolean faceVertices) {
		if (isHexVertical(type)) {
			return new HexGridVertical(faceEdges, faceVertices);
		}
		if (isHexHorizontal(type)) {
			return new HexGridHorizontal(faceEdges, faceVertices);
		}
		if (isSquare(type)) {
			return new SquareGrid(faceEdges, faceVertices);
		}
		if (isNone(type)) {
			return new GridlessGrid();
		}
		throw new IllegalArgumentException("Unknown grid type: " + type);
	}
	
	public static String getGridType(Grid grid) {
		if (grid instanceof HexGridVertical) {
			return HEX_VERT;
		}
		if (grid instanceof HexGridHorizontal) {
			return HEX_HORI;
		}
		if (grid instanceof SquareGrid) {
			return SQUARE;
		}
		if (grid instanceof GridlessGrid) {
			return NONE;
		}
		throw new IllegalArgumentException("Don't know type of grid: " + grid.getClass().getName());
	}

	public static boolean isSquare(String gridType) {
		return SQUARE.equals(gridType);
	}
	
	public static boolean isNone(String gridType) {
		return NONE.equals(gridType);
	}
	
	public static boolean isHexVertical(String gridType) {
		return HEX_VERT.equals(gridType);
	}
	
	public static boolean isHexHorizontal(String gridType) {
		return HEX_HORI.equals(gridType);
	}
}
