package com.company;

class base {
    private int[] winnumber;
    private String getnumber;
    public int winmoney[];

    public base() {
    }

    public base(int[] a, String b, int[] c) {
        winnumber = a;
        getnumber = b;
        winmoney = c;
    }

    public int[] getWinnumber() {
        return winnumber;
    }

    public String getnumber() {
        return getnumber;
    }

    public int money() {
        return 0;
    }

}

class single extends base {
    public single(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 3 || !getnumber().matches("^[0-9]*$"))
            return -1;
        for (int i = 0; i < 3; i++)
            if (getnumber().charAt(i) - 48 != getWinnumber()[i])
                return 0;
        return winmoney[0];
    }
}

class group extends base {
    public group(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 3 || !getnumber().matches("^[0-9]*$"))
            return -1;
        int[] check = new int[10];
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            check[getWinnumber()[i]]++;
            if (check[getWinnumber()[i]] == 2)
                flag = true;
        }
        for (int i = 0; i < 3; i++) {
            check[getnumber().charAt(i) - 48]--;
            if (check[getnumber().charAt(i) - 48] < 0)
                return 0;
        }
        if (flag) return winmoney[0];
        else return winmoney[1];
    }
}

class one1 extends base {
    public one1(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 3)
            return -1;
        int numstar = 0, index = 0;
        for (int i = 0; i < 3; i++) {
            if (getnumber().charAt(i) == '*')
                numstar++;
            else if (getnumber().charAt(i) <= '9' && getnumber().charAt(i) >= '0')
                index = i;
            else return -1;
        }
        if (numstar != 2) return -1;
        else {
            return getnumber().charAt(index) - 48 == getWinnumber()[index] ? winmoney[0] : 0;
        }
    }
}

class towd extends base {
    public towd(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 3)
            return -1;
        int nums = 0, index = 0;
        for (int i = 0; i < 3; i++) {
            if (getnumber().charAt(i) == '*')
                index = i;
            else if (getnumber().charAt(i) <= '9' && getnumber().charAt(i) >= '0')
                nums++;
            else return -1;
        }
        if (nums != 2) return -1;
        else {
            for (int i = 0; i < 3; i++)
                if (i != index && getnumber().charAt(i) - 48 != getWinnumber()[i])
                    return 0;
            return winmoney[0];
        }
    }
}

class guess1d extends base {
    public guess1d(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 1 || getnumber().charAt(0) < '0' || getnumber().charAt(0) > '9')
            return -1;
        int guessnum = 0;
        for (int i = 0; i < 3; i++)
            if (getWinnumber()[i] == getnumber().charAt(0) - 48)
                guessnum++;
        switch (guessnum) {
            case 0:
                return 0;
            default:
                return getWinnumber()[3 - guessnum];
        }
    }
}

class guesssum extends base {
    public guesssum(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().equals("") || !getnumber().matches("^[0-9]*$"))
            return -1;
        int num = Integer.parseInt(getnumber());
        if (num < 0 || num > 27)
            return -1;
        int getsum = 0;
        for (int i = 0; i < 3; i++)
            getsum += getWinnumber()[i];
        return getsum == num ? winmoney[num < 27 - num ? num : 27 - num] : 0;
    }
}

class guess2d extends base {
    public guess2d(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 2 || !getnumber().matches("^[0-9]*$"))
            return -1;
        int[] check = new int[10];
        boolean flag = getnumber().charAt(0) == getnumber().charAt(1);
        for (int i = 0; i < 3; i++) {
            check[getWinnumber()[i]]++;
        }
        for (int i = 0; i < 2; i++) {
            check[getnumber().charAt(i) - 48]--;
            if (check[getnumber().charAt(i) - 48] < 0)
                return 0;
        }
        if (flag) return winmoney[0];
        else return winmoney[1];
    }
}

class general extends base {
    public general(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (getnumber().length() != 3 || !getnumber().matches("^[0-9]*$"))
            return -1;
        int num = 0;
        for (int i = 0; i < 3; i++) {
            if (getnumber().charAt(i) - 48 == getWinnumber()[i])
                num++;
        }
        if (num <= 1)
            return 0;
        else return num == 3 ? winmoney[0] : winmoney[1];
    }
}

class bigorsmall extends base {
    public bigorsmall(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        int sum = 0;
        for (int i = 0; i < 3; i++)
            sum += getWinnumber()[i];
        if (getnumber().equals("大"))
            return sum >= 19 ? winmoney[0] : 0;
        else if (getnumber().equals("小"))
            return sum <= 8 ? winmoney[0] : 0;
        else return -1;
    }
}

class guesssame extends base {
    public guesssame(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        for (int i = 0; i < 2; i++)
            if (getWinnumber()[i] != getWinnumber()[i + 1])
                return 0;
        return winmoney[0];
    }
}

class guesseven extends base {
    public guesseven(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (!getnumber().equals("奇") && !getnumber().equals("偶"))
            return -1;
        for (int i = 0; i < 2; i++) {
            if (getWinnumber()[i] % 2 != getWinnumber()[i + 1] % 2)
                return 0;
        }
        return getnumber().equals("奇") ? (getWinnumber()[0] % 2 == 1 ? winmoney[0] : 0) : (getWinnumber()[0] % 2 == 0 ? winmoney[0] : 0);
    }
}

public class lottery extends base {
    public lottery(int[] a, String b, int[] c) {
        super(a, b, c);
    }

    public int money() {
        if (2 * getWinnumber()[1] == getWinnumber()[0] + getWinnumber()[2] && Math.abs(getWinnumber()[0] - getWinnumber()[1]) == 1)
            return winmoney[0];
        return 0;
    }
}
