package view;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        

        String s = "24T23";
        ArrayList<String> combinacoes;

        ArrayList<Integer> dias = new ArrayList<>();
        ArrayList<String> turno = new ArrayList<>();
        ArrayList<Integer> horarios = new ArrayList<>();
        boolean ehDia = true;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            System.out.println(c);
            if(ehDia){
                System.out.println("lkjlk");
                if((int)c >= 0 && (int)c <= 9){
                    System.out.println("lkj");
                    dias.add((int)c);
                }
                else{
                    turno.add(Character.toString(c));
                    ehDia = false;
                }
            }
            else{
                horarios.add((int) c);
            }
        }


        for(Integer i: dias){
            System.out.println(i);
        }

        
    }

}
