/**
 *  @author Rowena
 *  This class will have an instance variable cityMap
 *  This variable references the object representing the city map where the program 
 *  will try to find a path from the starting cell to the destination cell.
 */

public class Path {
	
	Map cityMap;

	/** This is the constructor for the class. It receives as input a reference 
	 * to a Map object representing the city map.
	 */
	public Path(Map theMap) {
		cityMap = theMap;
	}

	/** This method will look for a path from the starting cell to the destination
	 * cell. If a path is found, this method must print a message indicating how
	 * many cells there were in the path. If no path was found, this method prints
	 * an appropriate message.
	 */
	public void findPath() {
		try {
			ArrayStackADT<MapCell> cells = new ArrayStack<MapCell>(5, 5, 5); // creates an empty stack
			cells.push(cityMap.getStart()); // gets starting cell

			while (!cells.isEmpty()) { // while stack is not empty
				MapCell current = cells.peek(); // Peeks at the top of the stack to get the current cell
				System.out.println("current: " + current.toString());
				if (current.isDestination()) { // If the current cell is the destination, then the algorithm exits the loop
					break;
				}

				MapCell temp = nextCell(current); // Find the best unmarked neighboring cell
				if (temp != null && !temp.isBlock()) {
					cells.push(temp);
					temp.markInStack();

				} else {
					current.markOutStack();
					cells.pop();
				}
				System.out.println("xxx");
			}
		} catch (EmptyStackException e) {
			System.out.println(e);
		}
	}

	/**
	 * The parameter of this method is the current map cell.
	 * This method returns the best map cell to continue the path
	 * from the current one.
	 */
	private MapCell nextCell(MapCell cell) {
		MapCell priority = null;
		for (int i = 0; i < 4; i++) {
			MapCell temp = cell.getNeighbour(i);
			if (temp != null && !temp.isMarked()) { // valid cell
				
				if (temp.isDestination()) {
					if (isCellAligned(cell, i)) {
						priority = temp;
						break;
					}
				}
				if (temp.isIntersection()) {
					if (isCellAligned(cell, i) && (priority == null || (priority != null && !priority.isIntersection()))) {
						priority = temp;
					}
				} else if (isRoad(temp)) {
					
					if (isCellAligned(cell, i) && isCellAligned(temp, i) && (priority == null || (priority != null && !isRoad(priority)))) {
						priority = temp;
						System.out.println("entered");

					}
				}
			}

		}

		return priority;
	}

	/**
	 * Returns true if the cell is a road.
	 */
	private boolean isRoad(MapCell cell) {
		return (cell.isNorthRoad() || cell.isEastRoad() || cell.isSouthRoad() || cell.isWestRoad());
	}

	/**
	 * Returns true if the cell is aligned. Compares current cell to neighboring index.
	 */
	private boolean isCellAligned(MapCell cell, int index) {
		return ((cell.isNorthRoad() && index == 0) || (cell.isEastRoad() && index == 1)
				|| (cell.isSouthRoad() && index == 2) || (cell.isWestRoad() && index == 3) || (cell.isIntersection())
				|| (cell.isStart()));
	}
}
