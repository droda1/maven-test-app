package de.hfu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * In this Command line program an input is read in and returned in Upper case
     *
     * @param args - the input string
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String line = bufferedReader.readLine();
            while(line != null) {
                String input = bufferedReader.readLine();
                System.out.println(input.toUpperCase());
            }
        }
    }
}
