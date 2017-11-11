// Given a list of student test scores, find the best average grade. Each student may have more than one test score in the list,
// and the best average grade is the averae of all test scores for that student. 
// Complete the bestAveraeGrade function in the editor below.
//
// It has one parameter, scores, which is an array of student test scores. Each element in the array is a two-element array of the form 
// [student name, test score] e.g. ["Bobby", "87"].
// More points will be awarded for solutions that can handle larger inputs within a set period of time i.e. code witha faster run-time complexity.
//
// Input Format
// The input parameter scores is an array of arrays, where each sub-array contains two strings: the student name followed by a test score as a String.
// You must also include the number of entries and the size of each entry (this will always be 2). 
//
// Output Format
// Your function must return a single integer representing the best average grade. If you end up with an average grade that is not an integer,
// you should use a floor function to return the largest integer less than or equal to the average. 
// Return 0 for an empty input. 
//
// Sample Input 0
// [["Bobby", "87], ["Charles", "100"], ["Eric", "64"], ["Charles", "22"]]
// entered as
// 4
// 2
// Bobby 87
// Charles 100
// Eric 64
// Charles 22
//
// Sample output 
// 87
//
import java.util.HashMap;
class AverageScore{

    static int bestAverageGrade(String[][] input){
        HashMap<String, Node> aMap = new HashMap<>();
        for (int i=0; i<input.length; i++){
            if (aMap.containsKey(input[i][0])){
                aMap.get(input[i][0]).addScore(input[i][1]);
            }
            else
                aMap.put(input[i][0], new Node(input[i][0], input[i][1]));
        }
        int maxScore = 0;
        for (Node valueNode : aMap.values()){
            if (valueNode.getAvg() > maxScore)
                maxScore = valueNode.getAvg();
        }

        return maxScore;
    }
    

    public static void main(String[] ars){
        String[][] input = {{"Bobby", "87"}, {"Charles", "100"}, {"Eric", "64"}, {"Charles", "88"}};
        System.out.println(bestAverageGrade(input));
    }

    static class Node{
        String name;
        int score;
        int count;
        int avg;

        Node(String name, String score){
            this.name = name;
            this.score = Integer.parseInt(score);
            count = 1;
            avg = this.score / count;
        }

        void addScore(String score){
            this.score += Integer.parseInt(score);
            count++;
            avg = this.score / count;
        }

        int getAvg(){
            return avg;
        }
    }
}
