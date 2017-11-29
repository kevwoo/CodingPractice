class Solution {
    // N gas stations along a circular route
    // amount = gas[i]
    // cost = cost[i] to travel from i to i+1
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int index = 0;
        int totalRoute = 0;

        for (int i = 0; i<gas.length; i++){
            totalGas += (gas[i] - cost[i]);
            totalRoute += (gas[i] - cost[i]);
            if (totalGas < 0){
                index = i+1;
                totalGas = 0;
            }
        }

        return (totalRoute >= 0) ? index : -1;
    }
}
