public class App{
    public static void main (String[] args){

        if (args.length == 2){
            //System.out.println(args[0]+" ; "+args[1]);
            System.out.println(Karatsuba.Multiplicar(args[0], args[1]));
        }
        else System.out.println("Inválido! Formato: java App arg1 arg2");
    }
}