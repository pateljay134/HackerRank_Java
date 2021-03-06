/*******************************************************************************
 * Copyright 2018 Francesco Cina'
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ufo.codility.challenges;

import java.util.*;

//queue node used in BFS
class Node {
	// (x, y) represents chess board coordinates
	// dist represent its minimum distance from the source
	int x, y, dist;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	// As we are using class object as a key in a HashMap
	// we need to implement hashCode() and equals()

	// -- below methods are generated by IntelliJ IDEA (Alt + Insert) --
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final Node node = (Node) o;

		if (x != node.x)
			return false;
		if (y != node.y)
			return false;
		return dist == node.dist;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		result = 31 * result + dist;
		return result;
	}
};

public class ChessKnight {
	// Below arrays details all 8 possible movements
	// for a knight
	private static int row[] = { 2, 2, -2, -2, 1, 1, -1, -1 };
	private static int col[] = { -1, 1, 1, -1, 2, -2, 2, -2 };

	// Check if (x, y) is valid chess board coordinates
	// Note that a knight cannot go out of the chessboard
	private static boolean valid(int x, int y, int N) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;

		return true;
	}

	// Find minimum number of steps taken by the knight
	// from source to reach destination using BFS
	public static int BFS(Node src, Node dest, int N) {
		// map to check if matrix cell is visited before or not
		final Map<Node, Boolean> visited = new HashMap<>();

		// create a queue and enqueue first node
		final Queue<Node> q = new ArrayDeque<>();
		q.add(src);

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and process it
			final Node node = q.poll();

			final int x = node.x;
			final int y = node.y;
			final int dist = node.dist;

			// if destination is reached, return distance
			if (x == dest.x && y == dest.y)
				return dist;

			// Skip if location is visited before
			if (visited.get(node) == null) {
				// mark current node as visited
				visited.put(node, true);

				// check for all 8 possible movements for a knight
				// and enqueue each valid movement into the queue
				for (int i = 0; i < 8; ++i) {
					// Get the new valid position of Knight from current
					// position on chessboard and enqueue it in the
					// queue with +1 distance
					final int x1 = x + row[i];
					final int y1 = y + col[i];

					if (valid(x1, y1, N))
						q.add(new Node(x1, y1, dist + 1));
				}
			}
		}

		// return INFINITY if path is not possible
		return Integer.MAX_VALUE;
	}

}
