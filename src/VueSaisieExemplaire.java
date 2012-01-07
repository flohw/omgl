import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class VueSaisieExemplaire extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitre;
	private JTextField textFieldDateReception;
	private JTextField textFieldNbExemplaires;
	
	private JList listExemplaires;
	private DefaultListModel modeleExemplaires = new DefaultListModel();
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonEnreg;
	private JButton buttonFermer;
	
	// à ajouter car la vue est observatrice d'un ouvrage
	private Ouvrage _ouvrage ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton RadioButtonConsultable;
	private JRadioButton RadioButtonEmpruntable;
	private JScrollPane scrollExemplaires;
	private JScrollPane scrollIsbn;
	private JList listIsbn;
	private DefaultListModel modeleIsbn = new DefaultListModel();

	public VueSaisieExemplaire(Controleur controleur) {
		super (controleur);
		contentPane = new JPanel();
		getFrame().setTitle("Enregistrement d'un nouvel exemplaire d'ouvrage");
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		getFrame().setBounds(100, 100, 540, 520);
		getFrame().setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setBounds(24, 11, 27, 16);
		contentPane.add(lblNewLabel);
		
		for (String isbn : getControleur().getOuvrages().keySet())
			modeleIsbn.addElement(getControleur().getOuvrage(isbn).afficheOuvrage());
		listIsbn = new JList(modeleIsbn);
		listIsbn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollIsbn = new JScrollPane(listIsbn);
		scrollIsbn.setBounds(173, 11, 317, 96);
		
		JLabel lblNewLabel_1 = new JLabel("Date réception");
		lblNewLabel_1.setBounds(24, 208, 92, 16);
		contentPane.add(lblNewLabel_1);
		
		textFieldDateReception = new JTextField();
		textFieldDateReception.setEditable(false);
		textFieldDateReception.setText("mm/aaaa");
		textFieldDateReception.setBounds(200, 202, 134, 28);
		contentPane.add(textFieldDateReception);
		textFieldDateReception.setColumns(10);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// liaison de la vue avec l'objet observé
				int index = listIsbn.getSelectedIndex();
				if (index == -1) {
					Message dg = new Message("Sélectionnez un ouvrage");
					dg.setVisible(true);
				} else
					getControleur().rechOuvrage(modeleIsbn.get(index).toString());
		}});
		buttonRech.setBounds(24, 52, 113, 29);
		contentPane.add(buttonRech);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statut;
				if (RadioButtonConsultable.isSelected()) {
					statut = "consultable";}
				else {
					statut = "empruntable";}
					getControleur().nouvExemplaire(getOuvrage(), textFieldDateReception.getText(), statut);
				}
		});
		buttonEnreg.setBounds(378, 203, 112, 29);
		contentPane.add(buttonEnreg);
		
		JLabel lblNewLabel_2 = new JLabel("Titre ouvrage");
		lblNewLabel_2.setBounds(24, 152, 83, 16);
		contentPane.add(lblNewLabel_2);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setBounds(200, 145, 291, 28);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		JLabel lblExemplaires = new JLabel("Exemplaires");
		lblExemplaires.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblExemplaires.setBounds(206, 312, 75, 15);
		contentPane.add(lblExemplaires);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre d'exemplaires");
		lblNewLabel_3.setBounds(24, 343, 141, 16);
		contentPane.add(lblNewLabel_3);
		
		textFieldNbExemplaires = new JTextField();
		textFieldNbExemplaires.setEditable(false);
		textFieldNbExemplaires.setBounds(200, 337, 62, 28);
		contentPane.add(textFieldNbExemplaires);
		textFieldNbExemplaires.setColumns(10);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieExemplaire.this);
				getControleur().menuBiblio();
			}
		});
		buttonFermer.setBounds(200, 491, 87, 29);
		contentPane.add(buttonFermer);
		
		listExemplaires = new JList(modeleExemplaires);
		scrollExemplaires= new JScrollPane(listExemplaires);
		scrollExemplaires.setEnabled(false);
		scrollExemplaires.setBounds(24, 371, 467, 108);
		contentPane.add(scrollExemplaires);
		
		
		JLabel lblNewLabel_4 = new JLabel("Statut");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(24, 246, 37, 16);
		contentPane.add(lblNewLabel_4);
		
		RadioButtonConsultable = new JRadioButton("Consultable");
		RadioButtonConsultable.setEnabled(false);
		buttonGroup.add(RadioButtonConsultable);
		RadioButtonConsultable.setBounds(200, 277, 107, 23);
		contentPane.add(RadioButtonConsultable);
		
		RadioButtonEmpruntable = new JRadioButton("Empruntable");
		RadioButtonEmpruntable.setEnabled(false);
		buttonGroup.add(RadioButtonEmpruntable);
		RadioButtonEmpruntable.setSelected(true);
		RadioButtonEmpruntable.setBounds(200, 242, 111, 23);
		contentPane.add(RadioButtonEmpruntable);
		contentPane.add(scrollIsbn);
		getFrame().setVisible(true);
	}
	
	private Ouvrage getOuvrage() { return _ouvrage; }
	public void setOuvrage(Ouvrage ouvrage) { _ouvrage = ouvrage; }
	
	public void alimente(Ouvrage ouv) {
		textFieldTitre.setText(ouv.getTitre());
		textFieldNbExemplaires.setText (String.valueOf(ouv.getNbExemplaires()));
		modeleExemplaires.clear();
		for (int i : ouv.getExemplaires().keySet())
			modeleExemplaires.addElement(ouv.getExemplaire(i).afficheInfos());
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale:
			buttonRech.setEnabled(true);
			break;
		case inter1:
			buttonEnreg.setEnabled(true);
			RadioButtonConsultable.setEnabled(true);
			RadioButtonEmpruntable.setEnabled(true);
			textFieldDateReception.setEditable(true);
			break;
		case finale:
			buttonFermer.setEnabled(true);
			break;
		}
	}

	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getOuvrage());
		super.update(observable,  objet);
	}
}
