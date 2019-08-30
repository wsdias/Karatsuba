public class Karatsuba{

    public Karatsuba(){

    }

    public static String Multiplicar(String a, String b){

        //System.out.println(a.length());
        //System.out.println(b.length());
        return KMult(a, b);
    }

    private static String KMult(String a, String b){

        if (a.length() == 1 && b.length() == 1)
            return String.valueOf(Integer.valueOf(a) * Integer.valueOf(b));
        else{

            String a1 = a.substring(0, a.length()/2);
            String a2 = a.substring(a.length()/2, a.length());
            String b1 = b.substring(0, b.length()/2);
            String b2 = b.substring(b.length()/2, b.length());

            //System.out.println(a1+" ; "+a2+" | "+b1+" ; "+b2);

            return KAdd(KMult(a1, b1), KMult(a1, b2), KMult(a2, b1), KMult(a2, b2));
        }
    }

    private static String KAdd(String a, String b, String c, String d){

        int i, limit, aux, carry;
        char[] x, y, z, w;

        limit = 2 * a.length(); for (i = 0; i < limit; i++) a += "0";
        limit = b.length(); for (i = 0; i < limit; i++) b += "0";
        limit = c.length(); for (i = 0; i < limit; i++) c += "0";

        limit = a.length() - b.length(); for (i = 0; i < limit; i++) b = "0" + b;
        limit = a.length() - c.length(); for (i = 0; i < limit; i++) c = "0" + c;
        limit = a.length() - d.length(); for (i = 0; i < limit; i++) d = "0" + d;

        //System.out.println("a: "+a);
        //System.out.println("b: "+b);
        //System.out.println("c: "+c);
        //System.out.println("d: "+d);

        x = a.toCharArray();
        y = b.toCharArray();
        z = c.toCharArray();
        w = d.toCharArray();

        for (i = x.length - 1; i >= 0; i--){
            aux = (x[i]-48) + (y[i]-48) + (z[i]-48) + (w[i]-48);
			carry = aux > 9 ? 1 : 0;
			x[i] = (char)(carry == 0 ? aux : );
        }

        return String.valueOf(x);
    }
}
