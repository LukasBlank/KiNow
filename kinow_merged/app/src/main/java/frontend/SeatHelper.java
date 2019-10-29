package frontend;

public class SeatHelper {

    public static void main(String[] args) {

        // Generate Output for Booleans
        System.out.println("Boolean ");
        for(int i = 97;i<=103;i++){
            for(int o = 1;o<=7;o++){
                System.out.print("clicked_"+((char)i)+o+" = false, ");
            }
        }

        System.out.println();

        // Generate Output for Button findViewById
        for(int i = 97;i<=103;i++){
            for(int o = 1;o<=7;o++){
                System.out.println("Button btn_"+((char)i)+o+" = findViewById(R.id."+((char)i)+o+");");
            }
        }

        System.out.println();

        // Generate Output for Switch Cases
        for(int i = 97;i<=103;i++){
            for(int o = 1;o<=7;o++){
                System.out.println("case R.id."+((char)i)+o+":");
                System.out.println("if(!clicked_"+((char)i)+o+"){");
                System.out.println("btn_"+((char)i)+o+".setBackgroundResource(R.drawable.seat_violet);");
                System.out.println("clicked_"+((char)i)+o+" = true;");
                System.out.println("} else {");
                System.out.println("btn_"+((char)i)+o+".setBackgroundResource(R.drawable.seat_grey);");
                System.out.println("clicked_"+((char)i)+o+" = false;");
                System.out.println("}");
                System.out.println("break;");
                System.out.println();
            }
        }

    }
}
