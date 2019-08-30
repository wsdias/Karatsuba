/*
 *  T1 - Projeto e Otimização de Algoritmos
 *
 *  Willian Schmiele Dias
 *  2019/2
 *
 */

import java.util.Arrays;

public class Karatsuba{

    public Karatsuba(){}

    public static String KMult(String a, String b){
    
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
            System.out.println(a+" ; "+b);
            System.out.println(a1+" ; "+a2+" | "+b1+" ; "+b2);
            //System.out.println(shift);

            //return KAdd (KMult(a1, b1) + shift + shift, KAdd (KAdd (KMult(a1, b2), KMult(a2, b1)) + shift, KMult(a2, b2)));
        }
    }

    public static String KAdd(String a, String b){

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
        return String.valueOf(res);
    }
}
