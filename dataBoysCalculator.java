import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import javax.swing.*;

class ImageTextField extends JTextField {
    private Icon icon;
    private final int iconWidth = 65; 
    private final int iconHeight = 55;

    public ImageTextField() {
        super();
        setFocusable(true); 
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE || (e.getKeyCode() == KeyEvent.VK_A && e.isControlDown())) {
                    setText("");
                    setIcon(null);
                }
            }
        });
    }

    public void setIcon(Icon icon) {
        if (icon != null) {
            // Scale the icon to the desired width and height
            Image img = ((ImageIcon) icon).getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            this.icon = new ImageIcon(img);
        } else {
            this.icon = null; // Reset the icon
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (icon != null) {
            int iconX = 10; // Position the icon on the left side with some padding
            int iconY = (getHeight() - icon.getIconHeight()) / 2; // Center the icon vertically
            icon.paintIcon(this, g, iconX, iconY);

            // Set the text position to the right of the icon
            int textX = iconX + icon.getIconWidth() + 10; // Space between icon and text
            g.drawString(getText(), textX, (getHeight() + g.getFontMetrics().getAscent()) / 2); // Center the text vertically
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        if (icon != null) {
            d.width += icon.getIconWidth() + 20; // Adjust width to accommodate the icon and padding
        }
        return d;
    }
}


// Rest of the code remains the same

class RoundedButton extends JButton {
    private final int cornerRadius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }
}

public class dataBoysCalculator {
    private double currentValue = 0;
    private boolean isNewOperation = false;
    private ImageTextField display;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new dataBoysCalculator().createAndShowGUI()); 
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Data Boys Advance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(550, 625);
        frame.getContentPane().setBackground(Color.black);

        display = new ImageTextField();
        display.setBounds(25, 25, 485, 100);
        display.setEditable(false);
        display.setBackground(Color.white);
        display.setForeground(Color.BLACK);
        display.setFont(new Font("SansSerif", Font.BOLD, 25));
        frame.add(display);

        ActionListener listener = e -> ButtonClick(e, display);
        // Button creation 
        createRoundedButton("DEL", 25, 140, listener, frame, 65, new Color(204, 0, 0), Color.WHITE);
        createRoundedButton("AC", 95, 140, listener, frame, 65, new Color(204, 0, 0), Color.WHITE);   
        createRoundedButton("+/-", 165, 140, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("+", 235, 140, listener, frame, 65, new Color(255, 153, 0), Color.WHITE);
        createRoundedButton("FLR", 305, 140, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("CEIL", 375, 140, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("INT", 445, 140, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("7", 25, 195, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton("8", 95, 195, listener, frame, 65,Color.white, Color.BLACK);
        createRoundedButton("9", 165, 195, listener, frame, 65,Color.white, Color.BLACK);
        createRoundedButton("-", 235, 195, listener, frame, 65, new Color(255, 153, 0), Color.white);
        createRoundedButton("//", 305, 195, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("%", 375, 195, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("N!", 445, 195, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("4", 25, 250, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton("5", 95, 250, listener, frame, 65,Color.white, Color.BLACK);
        createRoundedButton("6", 165, 250, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton("×", 235, 250, listener, frame, 65, new Color(250, 153, 0), Color.WHITE);      
        createRoundedImageButton("equationimages/squareroot.png", 305, 250, listener, frame, 40,40,"√");
        createRoundedImageButton("equationimages/cuberoot.png", 375, 250, listener, frame, 40,40,"∛");
        createRoundedImageButton("equationimages/summation.png", 445, 250, listener, frame, 40, 40, "Σ");
        createRoundedButton("1", 25, 305, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton("2", 95, 305, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton("3", 165, 305, listener, frame, 65, Color.white, Color.BLACK); 
        createRoundedButton("÷", 235, 305, listener, frame, 65, new Color(250, 153, 0), Color.white);       
        createRoundedImageButton("equationimages/xy.png", 305, 305, listener, frame, 40, 40, "xʸ");
        createRoundedImageButton("equationimages/xyz.png", 375, 305, listener, frame, 40, 40,"xʸᶻ");
        createRoundedImageButton("equationimages/Pi.png", 445, 305, listener, frame, 40, 40, "∏");
        createRoundedButton("0", 25, 360, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton(".", 95, 360, listener, frame, 65, Color.white, Color.BLACK);
        createRoundedButton("=", 165, 360, listener, frame, 135, new Color(0, 153, 0), Color.white);
        createRoundedImageButton("equationimages/dualsummation.png", 305, 360, listener, frame, 80, 40, "∑∑");
        createRoundedImageButton("equationimages/dualpi.png", 415, 360, listener, frame, 70, 40,"∏∏");
        createRoundedButton("SET", 25, 415, listener, frame, 275, new Color(102,102,102), Color.WHITE);
        createRoundedImageButton("equationimages/log2x.png", 305, 415, listener, frame, 80, 40, "log₂ x");
        createRoundedImageButton("equationimages/logx.png", 415, 415, listener, frame, 70, 40, "log x");
        createRoundedButton("A", 25, 470, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("B", 95, 470, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("C", 165, 470, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedButton("D", 235, 470, listener, frame, 65, new Color(102,102,102), Color.WHITE);
        createRoundedImageButton("equationimages/asumb.png", 305, 470, listener, frame, 80, 40, "a!+b!");
        createRoundedImageButton("equationimages/adivb.png", 415, 470, listener, frame, 70, 40, "a/b");       
        createRoundedImageButton("equationimages/x.png", 25, 525, listener, frame, 50, 40, "xy");        
        createRoundedImageButton("equationimages/xsumy.png", 105, 525, listener, frame, 50, 40, "x+y");        
        createRoundedImageButton("equationimages/xy.png", 185, 525, listener, frame, 50, 40,"xʸ");        
        createRoundedImageButton("equationimages/c.png", 265, 525, listener, frame, 50, 40,"Cx");        
        createRoundedImageButton("equationimages/xsumc.png", 345, 525, listener, frame, 55, 40,"x+C");        
        createRoundedImageButton("equationimages/xc.png", 430, 525, listener, frame, 55, 40,"x^c");
        frame.setVisible(true);
    }
     
    //for RoundedButtons
    private void createRoundedButton(String text, int x, int y, ActionListener listener, JFrame frame, int width, Color backgroundColor, Color foreGroundColor) {
        RoundedButton button = new RoundedButton(text, 20);
        button.setBounds(x, y, width, 50);
        button.setForeground(foreGroundColor); 
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(backgroundColor); 
        button.addActionListener(listener);
        frame.add(button);
    }

    //for RoundedImageButtons
    private void createRoundedImageButton(String imageName, int x, int y, ActionListener listener, JFrame frame, int imageWidth, int imageHeight, String actionCommand) {
        ImageIcon originalIcon = new ImageIcon(imageName);
        Image resizedImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
    
        RoundedButton button = new RoundedButton("", 20); // No text on button
        button.setIcon(new ImageIcon(resizedImage)); // Set the resized image as the button icon
        button.setBounds(x, y, imageWidth + 25, imageHeight + 10); 
        button.setBackground(new Color(204, 204, 204));
        button.setActionCommand(actionCommand); // Set the action command
        button.addActionListener((ActionEvent e) -> {
            ButtonClick(e, display);
            

        });

        frame.add(button); // Add the button to the frame
    }

    private String lastCommand = "";

private void ButtonClick(ActionEvent e, ImageTextField display) {
    String command = e.getActionCommand();
    System.out.println("Button Clicked: " + command);

    switch (command) {
        case "FLR":
        case "CEIL":
            lastCommand = command;
            performFloorOrCeil(display);
            break;

        case "AC":
            clearAllEntry(display);
            break;

        case "DEL":
            clearLastEntry(display);
            break;

        case "INT":
            convertToInteger(display);
            break;

        case "N!":
            calculateFactorial(display);
            break;

        case "=":
            calculate(display);
            isNewOperation = true;
            break;

        case "+/-":
            toggleSign(display);
            break;

        case "√": 
            calculateSquareRoot(display);
            break;

        case "∛": 
            calculateCubeRoot(display);
            break;
        case "Σ":
            handleIconCommand(command, display);
            break;
        case"∑∑":
        performDualSummation(display);
        break;
        case "log x": 
            calculateLog(display);
            break;
        case "log₂ x":
            calculateLogBase2(display);
            break;
        case "xʸᶻ":
            requestBaseAndTwoExponents(display);
            break;
        case "∏":
            requestProductInput(display);
            break;
        case "∏∏":
            requestDoubleProductInput(display);
        break;
        case "a!+b!":
                calculateFactorialSum(display);
            break;
        default:
            handleDefaultCommand(command, display);
            break;
    }
}
// for a!+b!
private void calculateFactorialSum(ImageTextField display) {
    String aInput = JOptionPane.showInputDialog("Enter the first number (a) for a!:");
    if (aInput == null || aInput.trim().isEmpty()) {
        display.setText("Error: No input for a!");
        return; 
    }

    String bInput = JOptionPane.showInputDialog("Enter the second number (b) for b!:");
    if (bInput == null || bInput.trim().isEmpty()) {
        display.setText("Error: No input for b!");
        return; 
    }

    try {
        int a = Integer.parseInt(aInput.trim());
        int b = Integer.parseInt(bInput.trim());

        if (a < 0 || b < 0) {
            display.setText("Error: Factorial is not defined for negative numbers.");
            return; 
        }

        BigInteger factorialA = factorial(a);
        BigInteger factorialB = factorial(b);
        BigInteger result = factorialA.add(factorialB);

        display.setText("" + result.toString());
    } catch (NumberFormatException ex) {
        display.setText("Error: Invalid input. Please enter integers.");
    } catch (Exception e) {
        display.setText("Error: " + e.getMessage());
    }
}

// for Summation
private void performSummation(ImageTextField display) {
    String limits = JOptionPane.showInputDialog("Enter lower and upper limits separated by a comma (e.g., 1, 10):");
    
    if (limits == null || limits.trim().isEmpty()) {
        display.setText("Error: Please enter both lower and upper limits");
        return; 
    }

    String[] parts = limits.split(",");
    if (parts.length != 2) {
        display.setText("Error: Please enter both lower and upper limits");
        return; 
    }

    try {
        int lower = Integer.parseInt(parts[0].trim());
        int upper = Integer.parseInt(parts[1].trim());
        if (lower > upper) {
            display.setText("Error: Lower limit must be less than or equal to upper limit.");
            return; 
        }

        String operation = JOptionPane.showInputDialog("Enter an expression using 'i' (e.g., 'i * 3'):");

        if (operation == null || operation.trim().isEmpty()) {
            display.setText("Error: Please enter a valid operation");
            return; 
        }

        System.out.println("Lower: " + lower + ", Upper: " + upper + ", Operation: " + operation);

        double result = complexSummation(lower, upper, operation);
        display.setText(String.valueOf(result));
    } catch (NumberFormatException ex) {
        display.setText("Error: Invalid number format");
    } catch (Exception e) {
        display.setText("Error: " + e.getMessage());
    }
}

private double complexSummation(int lower, int upper, String expression) {
    double sum = 0.0;

    for (int i = lower; i <= upper; i++) {
        double value = evaluateExpression(expression, i);
        sum += value; 
    }

    return sum; 
}
// for DualSummation
private void performDualSummation(ImageTextField display) {
    String outerLimits = JOptionPane.showInputDialog("Enter outer lower and upper limits separated by a comma (e.g., 1, 10):");

    if (outerLimits == null || outerLimits.trim().isEmpty()) {
        display.setText("Error: Please enter both outer lower and upper limits.");
        return; 
    }

    String[] outerParts = outerLimits.split(",");
    if (outerParts.length != 2) {
        display.setText("Error: Please enter both outer lower and upper limits.");
        return; 
    }

    try {
        int outerLower = Integer.parseInt(outerParts[0].trim());
        int outerUpper = Integer.parseInt(outerParts[1].trim());
        if (outerLower > outerUpper) {
            display.setText("Error: Outer lower limit must be less than or equal to outer upper limit.");
            return; 
        }

        String innerLimits = JOptionPane.showInputDialog("Enter inner lower and upper limits separated by a comma (e.g., 1, 10):");
        if (innerLimits == null || innerLimits.trim().isEmpty()) {
            display.setText("Error: Please enter both inner lower and upper limits.");
            return; 
        }

        String[] innerParts = innerLimits.split(",");
        if (innerParts.length != 2) {
            display.setText("Error: Please enter both inner lower and upper limits.");
            return; 
        }

        int innerLower = Integer.parseInt(innerParts[0].trim());
        int innerUpper = Integer.parseInt(innerParts[1].trim());
        if (innerLower > innerUpper) {
            display.setText("Error: Inner lower limit must be less than or equal to inner upper limit.");
            return; 
        }

        String operation = JOptionPane.showInputDialog("Enter an expression using 'i' and 'j' (e.g., 'i * j + j'):");
        if (operation == null || operation.trim().isEmpty()) {
            display.setText("Error: Please enter a valid operation.");
            return; 
        }

        System.out.println("Outer Lower: " + outerLower + ", Outer Upper: " + outerUpper +
                           ", Inner Lower: " + innerLower + ", Inner Upper: " + innerUpper + 
                           ", Operation: " + operation);

        double result = complexDualSummation(outerLower, outerUpper, innerLower, innerUpper, operation);
        display.setText(String.valueOf(result));
    } catch (NumberFormatException ex) {
        display.setText("Error: Invalid number format.");
    } catch (Exception e) {
        display.setText("Error: " + e.getMessage());
    }
}

private double complexDualSummation(int outerLower, int outerUpper, int innerLower, int innerUpper, String expression) {
    double sum = 0.0;
    for (int i = outerLower; i <= outerUpper; i++) {
        for (int j = innerLower; j <= innerUpper; j++) {
            double value = evaluateExpression(expression, i, j);
            sum += value; 
        }
    }
    return sum; 
}


// Request Product Input Method
private void requestProductInput(ImageTextField display) {
    try {
        String lowerInput = JOptionPane.showInputDialog("Enter the lower limit:");
        if (lowerInput == null || lowerInput.trim().isEmpty()) {
            display.setText("Error: No lower limit entered.");
            return;
        }
        int lower = Integer.parseInt(lowerInput.trim());

        String upperInput = JOptionPane.showInputDialog("Enter the upper limit:");
        if (upperInput == null || upperInput.trim().isEmpty()) {
            display.setText("Error: No upper limit entered.");
            return;
        }
        int upper = Integer.parseInt(upperInput.trim());

        String operation = JOptionPane.showInputDialog("Enter the expression (use 'i' for the iterator):");
        if (operation == null || operation.trim().isEmpty()) {
            display.setText("Error: No operation entered.");
            return;
        }

        double result = complexProduct(lower, upper, operation);
        display.setText(String.valueOf(result));
    } catch (NumberFormatException ex) {
        display.setText("Error: Invalid input.");
    }
}

// Request Double Product Input Method
private void requestDoubleProductInput(ImageTextField display) {
    try {
        String lower1Input = JOptionPane.showInputDialog("Enter the first lower limit:");
        if (lower1Input == null || lower1Input.trim().isEmpty()) {
            display.setText("Error: No first lower limit entered.");
            return;
        }
        int lower1 = Integer.parseInt(lower1Input.trim());

        String upper1Input = JOptionPane.showInputDialog("Enter the first upper limit:");
        if (upper1Input == null || upper1Input.trim().isEmpty()) {
            display.setText("Error: No first upper limit entered.");
            return;
        }
        int upper1 = Integer.parseInt(upper1Input.trim());

        String lower2Input = JOptionPane.showInputDialog("Enter the second lower limit:");
        if (lower2Input == null || lower2Input.trim().isEmpty()) {
            display.setText("Error: No second lower limit entered.");
            return;
        }
        int lower2 = Integer.parseInt(lower2Input.trim());

        String upper2Input = JOptionPane.showInputDialog("Enter the second upper limit:");
        if (upper2Input == null || upper2Input.trim().isEmpty()) {
            display.setText("Error: No second upper limit entered.");
            return;
        }
        int upper2 = Integer.parseInt(upper2Input.trim());

        String operation = JOptionPane.showInputDialog("Enter the expression (use 'i' for the first iterator and 'j' for the second):");
        if (operation == null || operation.trim().isEmpty()) {
            display.setText("Error: No operation entered.");
            return;
        }

        double result = complexDoubleProduct(lower1, upper1, lower2, upper2, operation);
        display.setText(String.valueOf(result));
    } catch (NumberFormatException ex) {
        display.setText("Error: Invalid input.");
    }
}

public static double complexProduct(int lower, int upper, String operation) {
    double product = 1;
    for (int i = lower; i <= upper; i++) {
        product *= evaluateExpression(operation, i);
    }
    return product;
}

public static double complexDoubleProduct(int lower1, int upper1, int lower2, int upper2, String operation) {
    double product = 1;
    for (int i = lower1; i <= upper1; i++) {
        for (int j = lower2; j <= upper2; j++) {
            product *= evaluateExpression(operation, i, j);
        }
    }
    return product;
}

private static double evaluateExpression(String operation, int value) {
    String expr = operation.replace("i", String.valueOf(value));
    return eval(expr); // Assuming eval() method correctly evaluates the expression
}

private static double evaluateExpression(String operation, int value1, int value2) {
    String expr = operation.replace("i", String.valueOf(value1)).replace("j", String.valueOf(value2));
    return eval(expr); // Assuming eval() method correctly evaluates the expression
}

private static double eval(String expression) {
    try {
        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");
            return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        } else if (expression.contains("-")) {
            String[] parts = expression.split("-");
            return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        } else if (expression.contains("*")) {
            String[] parts = expression.split("\\*");
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        } else if (expression.contains("/")) {
            String[] parts = expression.split("/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        } else {
            return Double.parseDouble(expression);
        }
    } catch (Exception e) {
        return 0; 
    }
}

private void requestBaseAndTwoExponents(ImageTextField display) {
    try {
        String baseInput = JOptionPane.showInputDialog(null, "Enter the base (x):", "Base Input", JOptionPane.PLAIN_MESSAGE);
        if (baseInput == null || baseInput.trim().isEmpty()) {
            display.setText("Error: No base entered");
            return;
        }
        
        double base = Double.parseDouble(baseInput.trim());

        String exponentInput = JOptionPane.showInputDialog(null, "Enter the first exponent (y):", "Exponent Input", JOptionPane.PLAIN_MESSAGE);
        if (exponentInput == null || exponentInput.trim().isEmpty()) {
            display.setText("Error: No exponent (y) entered");
            return;
        }

        double exponent = Double.parseDouble(exponentInput.trim());

        String secondExponentInput = JOptionPane.showInputDialog(null, "Enter the second exponent (z):", "Second Exponent Input", JOptionPane.PLAIN_MESSAGE);
        if (secondExponentInput == null || secondExponentInput.trim().isEmpty()) {
            display.setText("Error: No exponent (z) entered");
            return;
        }

        double secondExponent = Double.parseDouble(secondExponentInput.trim());
        calculatePowerForTwoExponents(base, exponent, secondExponent, display);

    } catch (NumberFormatException ex) {
        display.setText("Error: Invalid input");
    }
}

private void calculatePowerForTwoExponents(double base, double exponent, double secondExponent, ImageTextField display) {
    double result = Math.pow(base, Math.pow(exponent, secondExponent));
    display.setText(String.valueOf(result));
}

private void calculateLogBase2(ImageTextField display) {
    String input = display.getText().trim();
    if (!input.isEmpty()) {
        try {
            double value = Double.parseDouble(input);
            if (value <= 0) {
                display.setText("Error: Input must be > 0");
            } else {
                double result = Math.log(value) / Math.log(2); 
                display.setText(String.valueOf(result));
            }
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    } else {
        display.setText("Error: No Input");
    }
}


private void calculateLog(ImageTextField display) {
    String input = display.getText().trim();
    if (!input.isEmpty()) {
        try {
            double value = Double.parseDouble(input);
            if (value <= 0) {
                display.setText("Error: Non-positive Input");
            } else {
                double result = Math.log(value);
                display.setText(String.valueOf(result));
            }
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    }
}

private void calculateSquareRoot(ImageTextField display) {
    String input = display.getText().trim();
    if (!input.isEmpty()) {
        try {
            double value = Double.parseDouble(input);
            if (value < 0) {
                display.setText("Error: Negative Input");
            } else {
                double result = Math.sqrt(value);
                display.setText(String.valueOf(result));
            }
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    }
}


private void calculateCubeRoot(ImageTextField display) {
    String input = display.getText().trim();
    if (!input.isEmpty()) {
        try {
            double value = Double.parseDouble(input);
            double result = Math.cbrt(value);
            display.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    }
}

private void handleIconCommand(String command, ImageTextField display) {
    switch (command) {
        case "Σ": 
        performSummation(display);
        break;
        
        default:
            break;
    }
}

private void appendToDisplay(ImageTextField display, String command) {
    String currentText = display.getText();
    if (isOperator(command)) {
        // Add space before and after the operator
        display.setText(currentText + " " + command + " ");
    } else {
        // Append the command directly if it's not an operator
        display.setText(currentText + command);
    }
}

// Method to check if a command is an operator
private boolean isOperator(String command) {
    return command.equals("+") || command.equals("-") || command.equals("×") || command.equals("÷") || command.equals("xʸ") || command.equals("%");
}

private void performFloorOrCeil(ImageTextField display) {
    String input = display.getText().trim();
    if (!input.isEmpty()) {
        calculate(display);
    } else {
        display.setText("Error: Invalid Input");
    }
}


private void convertToInteger(ImageTextField display) {
    String currentText = display.getText().trim();
    if (!currentText.isEmpty()) {
        try {
            double value = Double.parseDouble(currentText);
            int intValue = (int) value;
            display.setText(String.valueOf(intValue));
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    }
}

private void calculateFactorial(ImageTextField display) {
    String factorialInput = display.getText().trim();
    if (!factorialInput.isEmpty()) {
        try {
            int value = Integer.parseInt(factorialInput);
            if (value < 0) {
                display.setText("Error: Negative Input");
            } else {
                display.setText(String.valueOf(factorial(value)));
            }
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    }
}



private void toggleSign(ImageTextField display) {
    String currentTextSignToggle = display.getText().trim();
    if (!currentTextSignToggle.isEmpty()) {
        try {
            double currentValue = Double.parseDouble(currentTextSignToggle);
            currentValue *= -1;
            display.setText(Double.toString(currentValue));
        } catch (NumberFormatException ex) {
            display.setText("Error: Invalid Input");
        }
    }
}

private void handleDefaultCommand(String command, ImageTextField display) {
    
    if (isNewOperation) {
        display.setText("");
        isNewOperation = false;
    }
    handleIconCommand(command, display);
    appendToDisplay(display, command);
}


private void clearLastEntry(ImageTextField display) {
    String currentText = display.getText();
    if (!currentText.isEmpty()) {
        display.setText(currentText.substring(0, currentText.length() - 1));
    }
    // If the text is now empty after deletion, clear the icon as well
    if (display.getText().isEmpty()) {
        display.setIcon(null); // Clear the icon
    }
}

private void clearAllEntry(ImageTextField display) {
    display.setText("");
    display.setIcon(null); // Clear the icon
    currentValue = 0; // Reset current value if needed
}

private void calculate(ImageTextField display) {
    String input = display.getText().trim();
    if (input.isEmpty()) return;

    String[] tokens = input.split("\\s+");
    double currentValue = 0;

    switch (tokens.length) {
        case 1:
            handleSingleToken(display, tokens[0]);
            break;

        case 3:
            handleTwoOperands(display, tokens);
            break;

        default:
            display.setText("Error: Invalid Format");
            break;
    }
}

private void handleSingleToken(ImageTextField display, String token) {
    try {
        // Attempt to parse the token as a double
        double firstValue = Double.parseDouble(token);

        // Handle floor and ceiling based on the last command
        if ("FLR".equals(lastCommand)) {
            display.setText(String.valueOf(Math.floor(firstValue))); // Floor operation
        } else if ("CEIL".equals(lastCommand)) {
            display.setText(String.valueOf(Math.ceil(firstValue))); // Ceiling operation
        } else {
            // If no last command, just display the original number
            display.setText(String.valueOf(firstValue)); 
        }
    } catch (NumberFormatException e) {
        display.setText("Error: Invalid Input"); // Catch invalid number format
    }
}


private BigInteger factorial(int value) {
    if (value < 0) {
        throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
    }
    BigInteger result = BigInteger.ONE; // Initialize result as BigInteger.ONE
    for (int i = 1; i <= value; i++) {
        result = result.multiply(BigInteger.valueOf(i)); // Use BigInteger for multiplication
    }
    return result; // Return the result as BigInteger
}

private void handleTwoOperands(ImageTextField display, String[] tokens) {
    try {
        double firstValue = Double.parseDouble(tokens[0].trim());
        String operator = tokens[1].trim();
        double secondValue = Double.parseDouble(tokens[2].trim());
        double result;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "×":
                result = firstValue * secondValue;
                break;
            case "÷":
                if (secondValue == 0) {
                    display.setText("Error: Div by 0");
                    return; 
                }
                result = firstValue / secondValue;
                break;
            case "xʸ": 
                result = Math.pow(firstValue, secondValue);
                break;
            case "%":
                result = firstValue % secondValue;
                break;
            default:
                display.setText("Error: Invalid Operation");
                return;
             
        }
        

        // Handle floor and ceiling based on last command
        if ("FLR".equals(lastCommand)) {
            display.setText(String.valueOf(Math.floor(result))); // Apply floor
        } else if ("CEIL".equals(lastCommand)) {
            display.setText(String.valueOf(Math.ceil(result))); // Apply ceiling
        } else {
            display.setText(String.valueOf(result)); // Show result
        }
    } catch (NumberFormatException e) {
        display.setText("Error: Invalid Input");
    }
 }
}
