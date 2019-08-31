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

public class KaratsubaB{

    public static void main (String[] args){

        if (args.length == 2)
        {
            //System.out.println(args[0]+" "+args[1]);
            String res = KaratsubaB.KMult(args[0], args[1]);
            //String res = KSub(args[0], args[1]);
            //while (res.charAt(0) == '0') res = res.substring(1);
            System.out.println(res);
        }
        else System.out.println("Inválido! Formato: java KaratsubaB arg1 arg2");
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

            //System.out.println(a+" ; "+b);
            // 0 ; 1 | 0 ; 9
            //System.out.println(a1+" ; "+a2+" | "+b1+" ; "+b2);    

            String c2 = KMult(a1, b1);
            String c1 = KMult(KAdd(a1, a2), KAdd(b1, b2));
            String c0 = KMult(a2, b2);

            /*String resA = KAdd(a1, a2);
            while (resA.charAt(0) == '0') resA = resA.substring(1);
            String resB = KAdd(b1, b2);
            while (resB.charAt(0) == '0') resB = resB.substring(1);
            System.out.println(a1+" + "+a2+" = "+resA+"    "+b1+" + "+b2+" = "+resB);*/

            System.out.println("c2: "+c2);
            System.out.println("c1: "+c1);
            System.out.println("c0: "+c0);

            System.out.println(KSub(KSub(c1, c2), c0)+"__SUB");
            System.out.println(KAdd(KSub(KSub(c1, c2), c0) + shift, c0)+"__ADD");

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
        System.out.println("@__ADD "+String.valueOf(res)+" !!!");
        String tmp = String.valueOf(res);
        while (tmp.length() > 1 && tmp.charAt(0) == '0') tmp = tmp.substring(1);
        System.out.println("#__ADD "+String.valueOf(tmp)+" !!!");
        return tmp;
    }

    private static String KSub(String a, String b){

        int aux, x, y;

        if (a.length() > b.length())
            while (b.length() < a.length()) b = "0" + b;
        else
            while (a.length() < b.length()) a = "0" + a;

        //System.out.println(a+" ; "+b);

        char[] aChar = a.toCharArray(), bChar = b.toCharArray(), res = new char[a.length()+1];
        Arrays.fill(res, '0');

        for (int index = a.length()-1; index >= 0; index--)
        {
            if (aChar[index] < bChar[index] && index > 0)
            {
                aChar[index-1] = (char)(aChar[index-1] - 1);
                res[index+1] = (char)((10 + aChar[index] - 48) - (bChar[index] - 48) + 48);
            }
            else
            {
                res[index+1] = (char)((aChar[index] - 48) - (bChar[index] - 48) + 48);
            }
        }
        //System.out.println("Chegou aqui!");
        return String.valueOf(res);
    }
}


