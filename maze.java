import java.util.*;
public class maze{

  public static int[] getStartPlace(){
    Random rand = new Random();
    int x,y;
    int m = rand.nextInt(10);
    int n = rand.nextInt(2);
    int o = rand.nextInt(2);
    if(o == 1) {
      if (n == 1){x = 0; y = m;}
      else{ x = m; y = 0;}
    }else{
      if (n == 0){x = 4; y = m;}
      else { x = m; y = 4;}
    }
    System.out.println("x: "+x+" y: "+y);
    int[] start = { x , y };
    return start;
  }

  public static char[][] nextMove(char[][] maze, int[] currentPosition){
    int x = currentPosition[0];
    int y = currentPosition[1];
    boolean inBoundsTop = (x > 0) && maze[x-1][y] == ' ';
    boolean inBoundsRight = (y < 9) && maze[x][y+1] == ' ';
    boolean inBoundsLeft = (y > 0) && maze[x][y-1] == ' ';
    boolean inBoundsBottom = (x < 9) && maze[x+1][y] == ' ';
    //base case
    if (!inBoundsBottom && !inBoundsLeft && !inBoundsRight && !inBoundsTop){
      maze[x][y] = 'O';
      System.out.println("No other routes are available.");
      return maze;
    }
    ArrayList<String> isTrue = new ArrayList<String>();
    if (inBoundsTop) isTrue.add("top");
    if (inBoundsBottom) isTrue.add("bottom");
    if (inBoundsRight) isTrue.add("right");
    if (inBoundsLeft) isTrue.add("left");

    Random rand = new Random();
    int m = rand.nextInt(isTrue.size());
    String nextMove = isTrue.get(m);
    int[] top = { x-1 , y};
    int[] bottom = { x+1 , y};
    int[] right = { x , y+1};
    int[] left = { x , y-1};
    switch(nextMove){
      case "top":
        maze[x-1][y] = '↑';
        displayMaze(maze);
         nextMove(maze, top);
        break;
      case "bottom":
        maze[x+1][y] = '↓';
        displayMaze(maze);
        nextMove(maze, bottom);
        break;
      case "right":
        maze[x][y+1] = '→';
        displayMaze(maze);
        nextMove(maze, right);
        break;
      case "left":
        maze[x][y-1] = '←';
        displayMaze(maze);
        nextMove(maze, left);
        break;
    }
    return maze;
  }

  public static char[][] createArray(){
    char[][] maze = new char[10][10];
    for (int i = 0; i < 10; i++){
      for (int j = 0; j < 10; j++){
        maze[i][j] = ' ';
      }
    }
    return maze;
  }

  public static char[][] createMaze(){
    char[][] maze = createArray();
    int[] start = getStartPlace();
    int x = start[0];
    int y = start[1];
    maze[x][y] = 'X';
    displayMaze(maze);
    nextMove(maze, start);
    displayMaze(maze);
    return maze;
    }

    public static void displayMaze(char[][] maze){
      for(int i = 0; i < maze.length; i++){
        System.out.println(' ');
        for(int j = 0; j < maze[1].length; j++){
          System.out.print(maze[i][j]+" ");
        }
      }
      System.out.println(' ');
    }


  public static void main(String[] args){
    createMaze();
  }
}
