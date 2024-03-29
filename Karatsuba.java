/*
 *  T1 - Projeto e Otimização de Algoritmos
 *
 *  Willian Schmiele Dias
 *
 *  2019/2
 *
 *  Baseado em: https://en.wikipedia.org/wiki/Karatsuba_algorithm#Pseudocode    
 *
 */

import java.util.Arrays;

public class Karatsuba{

    public static void main (String[] args){

        if (args.length == 2)
        {
            String signal = "";
            if (args[0].charAt(0) == '-' && args[1].charAt(0) != '-') signal = "-";
            else if (args[0].charAt(0) != '-' && args[1].charAt(0) == '-') signal = "-";
            System.out.println(signal + Karatsuba.KMult(args[0].replace("-", ""), args[1].replace("-", "")));
        }
        else System.out.println("Inválido! Formato: java Karatsuba arg1 arg2");
    }

    private static String KMult(String a, String b){
    
        if (a.length() == 1 && b.length() == 1)
            return String.valueOf(Integer.valueOf(a) * Integer.valueOf(b));
        else
        {
            if (a.length() > b.length())
            {
                while (a.length() % 2 != 0) a = "0" + a;
                while (b.length() < a.length()) b = "0" + b;
            }
            else
            {
                while (b.length() % 2 != 0) b = "0" + b;
                while (a.length() < b.length()) a = "0" + a;
            }

            String a1 = a.substring(0, a.length()/2);
            String a2 = a.substring(a.length()/2, a.length());
            String b1 = b.substring(0, b.length()/2);
            String b2 = b.substring(b.length()/2, b.length());

            String shift = "";
            while (shift.length() < a.length()/2) shift += "0";

            String c2 = KMult(a1, b1);
            String c1 = KMult(KAdd(a1, a2), KAdd(b1, b2));
            String c0 = KMult(a2, b2);

            return KAdd(c2 + shift + shift, KAdd(KSub(KSub(c1, c2), c0) + shift, c0));
        }
    }

    private static String KAdd(String a, String b){

        int aux;

        if (a.length() > b.length())
            while (b.length() < a.length()) b = "0" + b;
        else
            while (a.length() < b.length()) a = "0" + a;

        char[] aChar = a.toCharArray(), bChar = b.toCharArray(), res = new char[a.length()+1];
        Arrays.fill(res, '0');

        for (int index = a.length()-1; index >= 0; index--)
        {
            aux = (aChar[index]-48) + (bChar[index]-48) + (res[index+1]-48);

            if (aux < 10) res[index+1] = (char)(aux + 48);
            else
            {
                res[index+1] = (char)(aux - 10 + 48);
                res[index] = '1';
            }
        }
        String tmp = String.valueOf(res);
        while (tmp.length() > 1 && tmp.charAt(0) == '0') tmp = tmp.substring(1);
        return tmp;
    }

    private static String KSub(String a, String b){

        if (a.length() > b.length())
            while (b.length() < a.length()) b = "0" + b;
        else
            while (a.length() < b.length()) a = "0" + a;

        char[] aChar = a.toCharArray(), bChar = b.toCharArray(), res = new char[a.length()];

        for (int index = a.length()-1; index >= 0; index--)
        {
            if (aChar[index] < bChar[index] && index > 0)
            {
                aChar[index-1] = (char)(aChar[index-1] - 1);
                res[index] = (char)((10 + aChar[index] - 48) - (bChar[index] - 48) + 48);
            }
            else res[index] = (char)((aChar[index] - 48) - (bChar[index] - 48) + 48);
        }
        return String.valueOf(res);
    }
}


