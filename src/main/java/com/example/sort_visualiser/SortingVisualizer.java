package com.example.sort_visualiser;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SortingVisualizer extends Application{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int NUM_BARS = 10;
    private static final int BAR_WIDTH = WIDTH / (NUM_BARS * 2);
    private static final int MAX_BAR_HEIGHT = HEIGHT - 20;

    private int[] data; // Array to be sorted
    private Canvas canvas;
    private GraphicsContext gc;

    private Timeline timeline;
    private int sortingIndex = 0;
    private boolean isSortingComplete = false;
    private int currentStep = 0;
    private int count =0;


    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        data = new int[NUM_BARS];

        // Initialize the data array with random values
        for (int i = 0; i < NUM_BARS; i++) {
            data[i] = (int) (Math.random() * MAX_BAR_HEIGHT);
            // data[i] = 10+i;
        }

        VBox root = new VBox();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
        drawData();


        System.out.println(currentStep);
        timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> {
                    if (currentStep < data.length - 1) {
                        bubbleSortStep(data);
                        currentStep++;
                        drawData();


                    } else {
                        // Sorting is complete, stop the timeline
                        timeline.stop();
                        drawData();

                    }


                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run the timeline indefinitely

        // Start the timeline
        timeline.play();

    }

    private void drawData() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < NUM_BARS; i++) {
            int height = data[i];
            double barHeight = (double) height / MAX_BAR_HEIGHT * (HEIGHT - 20);
            double x = i * (BAR_WIDTH * 2); // Adjust the positioning to create spacing
            double y = HEIGHT - barHeight;


//            if (i == currentStep) {
//                gc.setFill(Color.RED);
//
//
//            } else {
//                gc.setFill(Color.BLACK);
//            }

            // gc.setFill(Color.BLACK);
            gc.fillRect(x, y, BAR_WIDTH, barHeight);
            gc.setFill(Color.BLACK);
            gc.fillText(Integer.toString(height), x + BAR_WIDTH / 2, y - 5);

        }
    }

    private void bubbleSortStep(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap elements at positions j and j+1
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                count = j;
            }
        }
    }


    // Add other sorting algorithms as separate methods here
    public static void main(String[] args) {
        launch(args);

    }

}

