package com.prituladima.trees.util;

import com.prituladima.trees.BIT2D;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestGenerator {
    public static void main(String[] args) throws IOException {

        int arraySizeX = 2_000;
        int arraySizeY = 2_000;
        int queryAmount = 100_000;
        int bound = 10;
        int delta = 10;


        PrintWriter writer = new PrintWriter(new FileWriter("Arrays.txt"));

        Random rand = new Random();

        writer.print(arraySizeX);
        writer.print(' ');
        writer.print(arraySizeY);
        writer.print('\n');

        BIT2D bit = new BIT2D(arraySizeX, arraySizeY);

        for (int i = 0; i < arraySizeX; i++) {
            for (int j = 0; j < arraySizeY; j++) {
                int val = rand.nextInt(bound);
                writer.print(val);
                writer.print(' ');
                bit.add(i, j, val);
            }
            writer.print('\n');
        }

        writer.print('\n');
        writer.print('\n');
        writer.print('\n');

        writer.print(queryAmount);
        writer.print('\n');
        writer.print('\n');

        for (int i = 0; i < queryAmount; i++) {
            if (rand.nextBoolean()) {

                int X1 = rand.nextInt(arraySizeX);
                int X2 = rand.nextInt(arraySizeX);

                int Y1 = rand.nextInt(arraySizeY);
                int Y2 = rand.nextInt(arraySizeY);

                if (X2 < X1) {
                    int buf = X1;
                    X1 = X2;
                    X2 = buf;
                }

                if (Y2 < Y1) {
                    int buf = Y1;
                    Y1 = Y2;
                    Y2 = buf;
                }

                writer.print('S');

                writer.print(' ');

                writer.print(X1);

                writer.print(' ');

                writer.print(X2);

                writer.print(' ');

                writer.print(Y1);

                writer.print(' ');

                writer.print(Y2);

                writer.print(' ');

                writer.print(bit.get(X1, X2, Y1, Y2));

                writer.print('\n');
            } else {
                int I = rand.nextInt(arraySizeX);
                int J = rand.nextInt(arraySizeY);
                int V = rand.nextInt(delta);

                writer.print('M');

                writer.print(' ');

                writer.print(I);

                writer.print(' ');

                writer.print(J);

                writer.print(' ');

                writer.print(V);

//                writer.print(' ');

//                writer.print(bit.get(L, R));
                bit.add(I, J, V);

                writer.print('\n');

            }
        }

        writer.close();

    }
}
