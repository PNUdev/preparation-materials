package org.pnu;

public class Main {
    public static void main(String[] args) {
        MaskService maskService = new MaskServiceImpl();

        System.out.println(maskService.algorithm());
        System.out.println(maskService.maskValue("Hello from Pre-Mask Service", 5));
    }
}
