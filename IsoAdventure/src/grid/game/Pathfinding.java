package grid.game;

import java.util.ArrayList;

public class Pathfinding {

	private MapStructure[][] map;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private AStarNode node;
	private ArrayList<AStarNode> openNodes;
	private ArrayList<AStarNode> closedNodes;
	private ArrayList<AStarNode> neighbourNodes;
	private ArrayList<AStarNode> pathNodes;
	private AStarNode activeNode;

	public Pathfinding() {
		openNodes = new ArrayList<AStarNode>();
		closedNodes = new ArrayList<AStarNode>();
		neighbourNodes = new ArrayList<AStarNode>();
		pathNodes = new ArrayList<AStarNode>();
	}

	protected void findPath(int startX, int startY, int endX, int endY,
			MapStructure[][] map) {

		activeNode = null;
		node = null;

		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.map = map;
		createNodeLists();
		processPath();
	}

	private void createNodeLists() {
		for (AStarNode pathNode : pathNodes) {
			pathNode.mapPiece.togglePath();
		}
		pathNodes.clear();
		openNodes.clear();
		node = new AStarNode(startX, startY, map[startX][startY]);
		node.setParent(new AStarNode(startX, startY, map[startX][startY]));
		openNodes.add(node);

		activeNode = node;

		closedNodes.clear();
		neighbourNodes.clear();
	}

	protected void processPath() {

		while (activeNode.getxCoord() != endX || activeNode.getyCoord() != endY
				&& !openNodes.isEmpty()) {
			checkOpenNodes();
			activeNode.mapPiece.togglePath();
			pathNodes.add(activeNode);
			if (activeNode.getxCoord() == endX
					&& activeNode.getyCoord() == endY) {
			} else {
				openNodes.remove(activeNode);
				closedNodes.add(activeNode);
				addNodesNeighbours();
				for (AStarNode neighbourNode : neighbourNodes) {
					if (closedNodes.contains(neighbourNode)) {
						calculateG(neighbourNode);
						neighbourNode.setParent(activeNode);
					} else if (openNodes.contains(neighbourNode)) {
						calculateG(neighbourNode);
						neighbourNode.setParent(activeNode);
					} else {
						openNodes.add(neighbourNode);
						calculateG(neighbourNode);
					}
				}
			}

		}
	}

	private void checkOpenNodes() {
		int currentBestF = 1337;
		if (!openNodes.isEmpty()) {
			for (AStarNode checkNode : openNodes) {
				calculateG(checkNode);
				calculateH(checkNode);
				checkNode.setF(checkNode.getG() + checkNode.getH());
				if (checkNode.getF() < currentBestF) {
					currentBestF = checkNode.getF();
					activeNode = checkNode;
				}
			}
		}
	}

	private void calculateG(AStarNode checkNode) {
		int parentX = checkNode.getParent().getxCoord();
		int parentY = checkNode.getParent().getyCoord();
		int xCoord = checkNode.getxCoord();
		int yCoord = checkNode.getyCoord();

		if ((yCoord - 1 == parentY && xCoord == parentX)
				|| (yCoord + 1 == parentY && xCoord == parentX)
				|| (xCoord - 1 == parentX && yCoord == parentY)
				|| (xCoord + 1 == parentX && yCoord == parentY)) {
			checkNode.setG(10);
		} else {
			checkNode.setG(14);
		}
	}

	private void calculateH(AStarNode checkNode) {
		checkNode.setH((Math.abs((checkNode.getxCoord() - endX)) + Math
				.abs((checkNode.getyCoord() - endY))) * 10);
	}

	private void addNodesNeighbours() {
		if (activeNode.getxCoord() > 0 && activeNode.getyCoord() > 0
				&& activeNode.getxCoord() < map.length - 1
				&& activeNode.getyCoord() < map.length - 1) {

			if (map[activeNode.getxCoord() - 1][activeNode.getyCoord()]
					.getNum_tiles() > 1) {
				node = new AStarNode(activeNode.getxCoord() - 1,
						activeNode.getyCoord(),
						map[activeNode.getxCoord() - 1][activeNode.getyCoord()]);
				node.setParent(activeNode);
				closedNodes.add(node);
				neighbourNodes.add(node);
			} else {
				node = new AStarNode(activeNode.getxCoord() - 1,
						activeNode.getyCoord(),
						map[activeNode.getxCoord() - 1][activeNode.getyCoord()]);
				node.setParent(activeNode);
				neighbourNodes.add(node);
			}

			if (map[activeNode.getxCoord() + 1][activeNode.getyCoord()]
					.getNum_tiles() > 1) {
				node = new AStarNode(activeNode.getxCoord() + 1,
						activeNode.getyCoord(),
						map[activeNode.getxCoord() + 1][activeNode.getyCoord()]);
				node.setParent(activeNode);
				closedNodes.add(node);
				neighbourNodes.add(node);
			} else {
				node = new AStarNode(activeNode.getxCoord() + 1,
						activeNode.getyCoord(),
						map[activeNode.getxCoord() + 1][activeNode.getyCoord()]);
				node.setParent(activeNode);
				neighbourNodes.add(node);
			}

			if (map[activeNode.getxCoord()][activeNode.getyCoord() - 1]
					.getNum_tiles() > 1) {
				node = new AStarNode(activeNode.getxCoord(),
						activeNode.getyCoord() - 1,
						map[activeNode.getxCoord()][activeNode.getyCoord() - 1]);
				node.setParent(activeNode);
				closedNodes.add(node);
				neighbourNodes.add(node);
			} else {
				node = new AStarNode(activeNode.getxCoord(),
						activeNode.getyCoord() - 1,
						map[activeNode.getxCoord()][activeNode.getyCoord() - 1]);
				node.setParent(activeNode);
				neighbourNodes.add(node);
			}

			if (map[activeNode.getxCoord()][activeNode.getyCoord() + 1]
					.getNum_tiles() > 1) {
				node = new AStarNode(activeNode.getxCoord(),
						activeNode.getyCoord() + 1,
						map[activeNode.getxCoord()][activeNode.getyCoord() + 1]);
				node.setParent(activeNode);
				closedNodes.add(node);
				neighbourNodes.add(node);
			} else {
				node = new AStarNode(activeNode.getxCoord(),
						activeNode.getyCoord() + 1,
						map[activeNode.getxCoord()][activeNode.getyCoord() + 1]);
				node.setParent(activeNode);
				neighbourNodes.add(node);
			}

		}
	}

}
