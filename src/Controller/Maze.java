package Controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import os.CheckOs;
import resource.Dimension;

/**
 * Created by theblackcat on 11/5/17.
 */
public class Maze {

    public static Pane getOverlay() {
        StackPane p = new StackPane();

        return p;
    }

    public static void initDraw(GraphicsContext gc) {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        String path = CheckOs.getImagePath();
        Image hardRock = new Image("file:" + path + "hard-rock.png");
        Image stone = new Image("file:" + path + "stone.png");
        Image sand = new Image("file:" + path + "sand.png");

        int map[][] = generateMap();
        System.out.println("Maze Size :" + map.length + "," + map[0].length);
        int height = Dimension.height / Dimension.brickSize;
        int width = Dimension.width / Dimension.brickSize;
        int midHeight = height / 2;
        int midWidth = width / 2;
        for(int i=-3;i<6;i++ ){
            for(int j=-5;j<10;j++){
                map[midHeight + i][midWidth + j] = 3;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    gc.drawImage(sand, j * Dimension.brickSize, i * Dimension.brickSize);
                } else if (map[i][j] == 0) {
                    gc.drawImage(stone, j * Dimension.brickSize, i * Dimension.brickSize);
                }
            }
        }

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(0);
        gc.fill();
        gc.drawImage(hardRock, Dimension.brickSize, Dimension.brickSize);
        gc.drawImage(sand, Dimension.brickSize, Dimension.brickSize);
        gc.drawImage(stone, Dimension.brickSize, Dimension.brickSize);
    }


    public static int[][] initialiseMap(int[][] map){
        for(int x=0; x<map.length; x++){
            for(int y=0; y<map[x].length; y++){
                if(Math.random() < 0.35f){
                    map[x][y] = 1;
                }
            }
        }
        return map;
    }
    public static int[][] generateMap () {
        //Create a new map
        int height = Dimension.height / Dimension.brickSize;
        int width = Dimension.width / Dimension.brickSize;
        int[][] cellmap = new int[height][width];
        //Set up the map with random values
        cellmap = initialiseMap(cellmap);
        int numberOfSteps = 10;
        //And now run the simulation for a set number of steps
        for (int i = 0; i < numberOfSteps; i++) {
            cellmap = doSimulationStep(cellmap);
        }
        return cellmap;
    }
    public static int countAliveNeighbours(int[][] map, int x, int y) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if (i == 0 && j == 0) {
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length) {
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y] == 1) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public static int[][] doSimulationStep(int[][] oldMap) {
        int height = Dimension.height / Dimension.brickSize;
        int width = Dimension.width / Dimension.brickSize;
        int deathLimit = Dimension.deathLimit;
        int birthLimit = Dimension.birthLimit;
        int[][] newMap = new int[height][ width];
        //Loop over each row and column of the map
        for (int x = 0; x < oldMap.length; x++) {
            for (int y = 0; y < oldMap[0].length; y++) {
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y] == 1) {
                    if (nbs < deathLimit) {
                        newMap[x][y] = 0;
                    } else {
                        newMap[x][y] = 1;
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else {
                    if (nbs > birthLimit) {
                        newMap[x][y] = 1;
                    } else {
                        newMap[x][y] = 0;
                    }
                }
            }
        }
        return newMap;
    }
}