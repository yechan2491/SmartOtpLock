package com.cookandroid.smartotplock;

import java.util.Random;

public class otpGenerate {
    private int certNumLength = 6;

    public String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());

        int range = (int)Math.pow(10, certNumLength);
        int trim = (int)Math.pow(10, certNumLength-1);
        int result = random.nextInt(range)+trim;

        if(result>range) {
            result = result-trim;
        }

        return String.valueOf(result);
    }

    public int getCertNumLength() {
        return certNumLength;
    }

    public void setCertNumLength(int certNumLength) {
        this.certNumLength = certNumLength;
    }

    public static void main(String[] args) {
        otpGenerate ge = new otpGenerate();
        ge.setCertNumLength(5);
        System.out.println(ge.excuteGenerate());
    }
}
