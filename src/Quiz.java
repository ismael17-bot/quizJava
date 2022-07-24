import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{
	
	String[] questions = 	{
								"No corpo humano, o timo é...",
								"Fotossíntese é o processo...",
								"Autótrofos são os seres vivos que...",
								"Os ovários são...",
								" É uma síndrome que afeta apenas indivíduos do sexo masculino. Pode causar ginecomastia."
							};
	String[][] options = 	{
								{"um osso que constitui o esqueleto dos dedos","uma cavidade que contém as cordas vocais","uma glândula situada no tórax","a parte posterior da articulação entre o braço e o antebraço"},
								{"pelo qual as células englobam partículas relativamente grandes","realizado pelos seres vivos clorofilados, que utilizam dióxido de carbono e água, para obter glicose através da energia da luz solar","de estudo das características gênicas de uma determinada família"," de revelação de fotos digitais, a partir de um pen drive ou HD externo"},
								{"auxiliam na fabricação de automóveis","possuem autonomia para andar","são capazes de produzir seu próprio alimento","se alimentam de outros seres da mesma espécie"},
								{"repositórios de ovos de diversas espécies de animais","animais cujo desenvolvimento embrionário ocorre dentro de um ovo","os ossos mais longos do corpo humano","duas glândulas do aparelho genital feminino"},
								{"síndrome de Down","síndrome de Klinefelter","síndrome do pânico","síndrome de Turner"}
							};
	char[] answers = 		{
								'A',
								'B',
								'C',
								'C',
								'D'
							};
	char guess;
	char answer;
	int index;
	int correct_guesses =0;
	int total_questions = questions.length;
	int result;
	int seconds=10;
	
	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textarea = new JTextArea();
	JButton botaoA = new JButton();
	JButton botaoB = new JButton();
	JButton botaoC = new JButton();
	JButton botaoD = new JButton();
	JLabel reposta_A = new JLabel();
	JLabel reposta_B = new JLabel();
	JLabel resposta_C = new JLabel();
	JLabel resposta_D = new JLabel();
	JLabel relogio = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			if(seconds<=0) {
				displayAnswer();
			}
			}
		});
	
	public Quiz() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,800);
		frame.getContentPane().setBackground(new Color(128,128,128));
		frame.setLayout(null);
		frame.setResizable(false);
		
		textfield.setBounds(0,0,1500,50);
		textfield.setFont(new Font("Ink Free",Font.BOLD,30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);
		
		textarea.setBounds(0,50,1500,50);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		//textarea.setBackground(new Color(128,128,128));
		textarea.setFont(new Font("MV Boli",Font.BOLD,28));
		//textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);
		
		/*ser da direção dos botoes */
		botaoA.setBounds(15,200,100,100);
		botaoA.setFont(new Font("MV Boli",Font.BOLD,20));
		botaoA.setSize(50,50);
		botaoA.setFocusable(false);
		botaoA.addActionListener(this);
		botaoA.setText("A");
		
		botaoB.setBounds(15,300,100,100);
		botaoB.setFont(new Font("MV Boli",Font.BOLD,20));
		botaoB.setSize(50,50);
		botaoB.setFocusable(false);
		botaoB.addActionListener(this);
		botaoB.setText("B");
		
		botaoC.setBounds(15,400,100,100);
		botaoC.setFont(new Font("MV Boli",Font.BOLD,20));
		botaoC.setSize(50,50);
		botaoC.setFocusable(false);
		botaoC.addActionListener(this);
		botaoC.setText("C");
		
		botaoD.setBounds(15,500,100,100);
		botaoD.setFont(new Font("MV Boli",Font.BOLD,20));
		botaoD.setSize(50,50);
		botaoD.setFocusable(false);
		botaoD.addActionListener(this);
		botaoD.setText("D");
		
		/* set da posição dos texto das respostas */
		reposta_A.setBounds(75,170,1700,100);
		reposta_A.setBackground(new Color(50,50,50));
		reposta_A.setForeground(new Color(25,255,0));
		reposta_A.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		reposta_B.setBounds(75,270,1700,100);
		reposta_B.setBackground(new Color(50,50,50));
		reposta_B.setForeground(new Color(25,255,0));
		reposta_B.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		resposta_C.setBounds(75,370,1700,100);
		resposta_C.setBackground(new Color(50,50,50));
		resposta_C.setForeground(new Color(25,255,0));
		resposta_C.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		resposta_D.setBounds(75,470,1700,100);
		resposta_D.setBackground(new Color(50,50,50));
		resposta_D.setForeground(new Color(25,255,0));
		resposta_D.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		seconds_left.setBounds(1390,670,100,100);
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		
		
		number_right.setBounds(600,225,200,100);
		number_right.setFont(new Font("Ink Free",Font.BOLD,50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
		percentage.setBounds(600,325,200,100);
		percentage.setFont(new Font("Ink Free",Font.BOLD,50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		frame.add(relogio);
		frame.add(seconds_left);
		frame.add(reposta_A);
		frame.add(reposta_B);
		frame.add(resposta_C);
		frame.add(resposta_D);
		frame.add(botaoA);
		frame.add(botaoB);
		frame.add(botaoC);
		frame.add(botaoD);
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);
		
		proximaQuestao();
	}

	public void proximaQuestao() {
		
		if(index>=total_questions) {
			results();
		}
		else {
			textfield.setText("Questão "+(index+1));
			textarea.setText(questions[index]);
			reposta_A.setText(options[index][0]);
			reposta_B.setText(options[index][1]);
			resposta_C.setText(options[index][2]);
			resposta_D.setText(options[index][3]);
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			botaoA.setEnabled(false);
			botaoB.setEnabled(false);
			botaoC.setEnabled(false);
			botaoD.setEnabled(false);
			
			if(e.getSource()==botaoA) {
				answer= 'A';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			if(e.getSource()==botaoB) {
				answer= 'B';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			if(e.getSource()==botaoC) {
				answer= 'C';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			if(e.getSource()==botaoD) {
				answer= 'D';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			displayAnswer();
	}

	public void displayAnswer() {
		
		timer.stop();
		
		botaoA.setEnabled(false);
		botaoB.setEnabled(false);
		botaoC.setEnabled(false);
		botaoD.setEnabled(false);
		
		if(answers[index] != 'A')
			reposta_A.setForeground(new Color(255,0,0));
		if(answers[index] != 'B')
			reposta_B.setForeground(new Color(255,0,0));
		if(answers[index] != 'C')
			resposta_C.setForeground(new Color(255,0,0));
		if(answers[index] != 'D')
			resposta_D.setForeground(new Color(255,0,0));
		
		Timer pause = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				reposta_A.setForeground(new Color(25,255,0));
				reposta_B.setForeground(new Color(25,255,0));
				resposta_C.setForeground(new Color(25,255,0));
				resposta_D.setForeground(new Color(25,255,0));
				
				answer = ' ';
				seconds=10;
				seconds_left.setText(String.valueOf(seconds));
				botaoA.setEnabled(true);
				botaoB.setEnabled(true);
				botaoC.setEnabled(true);
				botaoD.setEnabled(true);
				index++;
				proximaQuestao();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}
	
	public void results(){
		
		botaoA.setEnabled(false);
		botaoB.setEnabled(false);
		botaoC.setEnabled(false);
		botaoD.setEnabled(false);
		
		result = (int)((correct_guesses/(double)total_questions)*100);
		
		textfield.setText("RESULTADO FINAL!");
		textarea.setText("");
		reposta_A.setText("");
		reposta_B.setText("");
		resposta_C.setText("");
		resposta_D.setText("");
		
		number_right.setText("("+correct_guesses+"/"+total_questions+")");
		percentage.setText(result+"%");
		
		frame.add(number_right);
		frame.add(percentage);
		
	}
}