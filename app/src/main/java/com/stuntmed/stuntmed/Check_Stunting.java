package com.stuntmed.stuntmed;


public class Check_Stunting {
    private String tinggiBadan;
    private String beratBadan;
    private String lingkarKepala;

    public Check_Stunting(String tinggiBadan, String beratBadan, String lingkarKepala) {
        this.tinggiBadan = tinggiBadan;
        this.beratBadan = beratBadan;
        this.lingkarKepala = lingkarKepala;
    }

    public String cekStunting() {
        //AVERAGE
        //tinggi badan -> Average + berat badan + normal
        if (tinggiBadan.equals("Average") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Normal") && lingkarKepala.equals("Normal")) {
            return "Tidak Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Overweight") && lingkarKepala.equals("Normal")) {
            return "Tidak Stunting";
            // tinggi badan -> Average + berat badan + mikrosefalus
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Normal") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Overweight") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
            // tinggi badan -> Average + berat badan + Makrosefalus
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Normal") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Average") && beratBadan.equals("Overweight") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
            //NORMAL
            //tinggi badan -> Normal + berat badan + normal
        }else if (tinggiBadan.equals("Normal") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Normal") && lingkarKepala.equals("Normal")) {
            return "Tidak Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Overweight") && lingkarKepala.equals("Normal")) {
            return "Tidak Stunting";
            // tinggi badan -> Normal + berat badan + mikrosefalus
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Normal") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Overweight") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
            // tinggi badan -> Average + berat badan + Makrosefalus
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Normal") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Normal") && beratBadan.equals("Overweight") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
            //SHORT
            //tinggi badan -> Short + berat badan + normal
        }else if (tinggiBadan.equals("Short") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Normal") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Overweight") && lingkarKepala.equals("Normal")) {
            return "Stunting";
            // tinggi badan -> Short + berat badan + mikrosefalus
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Normal") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Overweight") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
            // tinggi badan -> Short + berat badan + Makrosefalus
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Normal") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Short") && beratBadan.equals("Overweight") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
            //TALL
            //tinggi badan -> Tall + berat badan + normal
        }else if (tinggiBadan.equals("Tall") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Normal")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Normal") && lingkarKepala.equals("Normal")) {
            return "Tidak Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Overweight") && lingkarKepala.equals("Normal")) {
            return "Tidak Stunting";
            // tinggi badan -> Tall + berat badan + mikrosefalus
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Normal") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Overweight") && lingkarKepala.equals("Mikrosefalus")) {
            return "Stunting";
            // tinggi badan -> Tall + berat badan + Makrosefalus
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Gizi Buruk") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Gizi Kurang") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Normal") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else if (tinggiBadan.equals("Tall") && beratBadan.equals("Overweight") && lingkarKepala.equals("Makrosefalus")) {
            return "Stunting";
        } else {
            return "Perlu pengecekan lebih lanjut dengan dokter";
        }
    }

    public static void main(String[] args) {
        Check_Stunting bayi1 = new Check_Stunting("Normal", "Normal", "Normal");
        Check_Stunting bayi2 = new Check_Stunting("Normal", "Gizi Buruk", "Mikrosefalus");
        Check_Stunting bayi3 = new Check_Stunting("Tall", "Overweight", "Makrosefalus");
        Check_Stunting bayi4 = new Check_Stunting("Short", "Normal", "Normal");
        Check_Stunting bayi5 = new Check_Stunting("Normal", "Overweight", "Normal");
        Check_Stunting bayi6 = new Check_Stunting("Tall", "Gizi Buruk", "Normal");

        System.out.println("Bayi 1: " + bayi1.cekStunting());
        System.out.println("Bayi 2: " + bayi2.cekStunting());
        System.out.println("Bayi 3: " + bayi3.cekStunting());
        System.out.println("Bayi 4: " + bayi4.cekStunting());
        System.out.println("Bayi 5: " + bayi5.cekStunting());
        System.out.println("Bayi 6: " + bayi6.cekStunting());
    }
}
