package com.diegomtzg.lineareqnsolver;

/**
 * Created by diegomtzg on 8/5/17.
 */

public class Solver
{
    private final String eqn;

    Solver(String input)
    {
        eqn = input;
    }

    public String solveForX()
    {
        // First we need to get the index of the equals sign
        int i = 0;
        while (eqn.charAt(i) != '=') i++;
        int equalsIdx = i;

        // Now we get the totals from each side and subtract the right Xs from the left and the left individual numbers from the right
        double totX = getXs(eqn.substring(0, equalsIdx)) - getXs(eqn.substring(equalsIdx + 1, eqn.length()));
        double totInd = getSingles(eqn.substring(equalsIdx + 1, eqn.length())) - getSingles(eqn.substring(0, equalsIdx));

        if (totX == 0)
        {
            if (totInd == 0) return "Infinite Solutions"; // If both the X total and the Individual number total are equal, there are infinite solutions
            else return "No Solution"; // If there are only no Xs, then there is no solution
        }
        else
        {
            return "X is " + Double.toString(totInd / totX);
        }
    }

    private int getXs(String eqnSide)
    {
        int result = 0;
        for (int i = 0; i < eqnSide.length(); i++)
        {
            if (eqnSide.charAt(i) == 'x')
            {
                if(i == 0) result++; // If the first character is an X, add 1 to the running total of Xs

                    // These next two are the cases for one digit numbers before X
                else if (eqnSide.charAt(i - 1) == '+') result++;
                else if (eqnSide.charAt(i - 1) == '-') result--;

                    // Now for multiple digit numbers before X
                else
                {
                    result += getNumBckwd(eqnSide, i - 1, false); // Method takes care of negative numbers
                }
            }
        }
        return result;
    }

    private int getSingles(String eqnSide)
    {
        int result = 0;
        int i = 0;
        if(eqnSide.charAt(0) == '-') i++;
        for (; i < eqnSide.length(); i++)
        {
            if (eqnSide.charAt(i) == '+' || eqnSide.charAt(i) == '-')
            {
                result += getNumBckwd(eqnSide, i - 1, true); // Get number before plus or minus sign
            }
        }
        result += getNumBckwd(eqnSide, i - 1, true);

        return result;
    }

    private int getNumBckwd(String eqnSide, int startIdx, boolean noX)
    {
        if (noX && eqnSide.charAt(startIdx) == 'x') return 0;
        int currIdx = startIdx;
        StringBuilder number = new StringBuilder();
        while (currIdx >= 0 && (eqnSide.charAt(currIdx) >= '0' && eqnSide.charAt(currIdx) <= '9')) // Only accept digits 0 through 9
        {
            number.append(eqnSide.substring(currIdx, currIdx + 1));
            currIdx--;
        }
        number.reverse();
        int result = Integer.parseInt(number.toString());
        if (currIdx >= 0 && eqnSide.charAt(currIdx) == '-') result *= -1; // Account for negative numbers
        return result;
    }
}

