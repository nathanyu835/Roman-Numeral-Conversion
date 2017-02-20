
import java.util.LinkedHashMap;
public class PegaGUI extends javax.swing.JFrame {

    private javax.swing.JPanel Panel;
    private javax.swing.JButton convert;
    private javax.swing.ButtonGroup group;
    private javax.swing.JRadioButton i2r;
    private javax.swing.JTextField input;
    private javax.swing.JScrollPane jScroll;
    private javax.swing.JRadioButton r2i;
    private javax.swing.JTextArea textBox;  
    
    private static final LinkedHashMap<String, Integer> R2I = new LinkedHashMap<String, Integer>()
    {{ 
        put("M", 1000); put("CM", 900); put("D", 500); put("CD", 400); put("C", 100); put("XC", 90);
        put("L", 50); put("XL", 40); put("X", 10); put("IX", 9); put("V", 5); put("IV", 4); put("I", 1);
    }};

    public PegaGUI()
    {
        initComponents();
    }
                          
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        Panel = new javax.swing.JPanel();
        convert = new javax.swing.JButton();
        input = new javax.swing.JTextField();
        r2i = new javax.swing.JRadioButton();
        i2r = new javax.swing.JRadioButton();
        jScroll = new javax.swing.JScrollPane();
        textBox = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        convert.setText("Convert!");
        convert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertActionPerformed(evt);
            }
        });

        input.setText("MCMLXXIV");

        group.add(r2i);
        r2i.setText("Roman to Decimal");

        group.add(i2r);
        i2r.setText("Decimal to Roman");

        textBox.setEditable(false);
        textBox.setColumns(20);
        textBox.setRows(5);
        jScroll.setViewportView(textBox);

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(input)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(convert))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelLayout.createSequentialGroup()
                        .addComponent(i2r)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(r2i))
                    .addComponent(jScroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(i2r)
                    .addComponent(r2i))
                .addGap(9, 9, 9)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(convert))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                     

    private void convertActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(i2r.isSelected())
        {
            try {
                textBox.append(input.getText() + " in its Roman Numeral form is: " + i2r(Integer.parseInt(input.getText())) + "\n\r");
            }
            catch (NumberFormatException e) {
                textBox.append("Invalid integer input.\n\r");
            }
            
        }
        else textBox.append(input.getText() + " in its integer form is: " + r2i(input.getText()) + "\n\r");
    }                                       

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PegaGUI().setVisible(true);
            }
        });
    }
    
    /**
     * This method will convert a properly formatted roman numeral to decimal.
     * @param roman numeral to be converted
     * @return decimal representation of the roman numeral
     */
    private int r2i(String roman)
    {
        int decimal = 0; //To store converted decimal
        char[] array =  roman.toCharArray(); 
        for(int i = 0; i < array.length; i++) //Looping through each individual character
        {
            if(i != array.length-1 && R2I.containsKey("" + array[i] + array[i+1])) //Two character numeral
            {
                decimal += R2I.get("" + array[i] + array[i+1]);
                i++; //To account for second character
            }
            else if(R2I.containsKey("" + array[i])) //One character numeral
            {
                decimal += R2I.get("" + array[i]);  
            }
            else //OMIT condition to simply ingore invalid characters
            {
                textBox.append("Invalid roman numeral input.\n\r");
                throw new NumberFormatException("Invalid roman numeral input.");
            }
        }
        return decimal;
    }
    
    /**
     * This method will convert a properly formatted decimal integer to a roman numeral.
     * @param i integer to be converted
     * @return roman numeral representation of the decimal integer
     */
    private String i2r(int i)
    {
        String roman = ""; //To store converted roman numeral
        int remainder = i;
        for(Integer val : R2I.values()) //Ordered list of values from LinkedHashMap implementation
        {
            int j = remainder/val; //Number of the current roman numeral symbol to be concatenated
            remainder %= val; //Value remaining to be converted
            for(int k = 0; k < j; k++)
            {
                roman += getKey(val);
            }
        }
        return roman;
    }
    
    //Helper function for i2r conversion, returns the roman numeral representation of an integer
    static String getKey(int i)
    {
        for(String str : R2I.keySet())
        {
            if(R2I.get(str) == i)
                return str;
        }
        return "";
    }            
             
}
