	import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class ParentWindow extends JDialog {
	private boolean ok = false; // har brukeren trykket OK-knappen?
	private JButton okKnapp  = new JButton("OK");
	private ButtonPanel buttonpanel = new ButtonPanel();

	protected ParentWindow(String title) {
		addWindowListener(new Vinduslytter());

		/* Vi vil programmere lukking av vinduet selv: */
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		/* Setter OK-knappen til standardknappen. Reagerer på Enter-tasten. */
		JRootPane tavle = getRootPane();
    	tavle.setDefaultButton(okKnapp);
	}

  /**
   * Til bruk for subklasser som kan kontrollere verdien til ok
   */
  protected boolean isOk() {
    return ok;
  }

  /**
   * Til bruk for subklasser som kan sette verdien til ok
   */
  protected void setOk(boolean verdi) {
    ok = verdi;
  }

  /**
   * Returnerer en referanse til knappepanelet.
   * Subklassen må plassere dette panelet riktig i vinduet.
   */
  protected JPanel getButtonpanel() {
    return buttonpanel;
  }

  /**
   * Subklasser kan ha sin egen utgave av metoden okData() dersom det er
   * ønskelig at dataene skal kontrolleres før de eventuelt godtas.
   */
  protected boolean okData() {
    return true;
  }

  /* Privat klasse med panelet som inneholder de to knappene, OK og Avbryt. */
  private class ButtonPanel extends JPanel {
    public ButtonPanel() {
      JButton cancelButton = new JButton("Avbryt");
      Kommandolytter knappelytter = new Kommandolytter();
      add(okKnapp);
      add(cancelButton);
      okKnapp.addActionListener(knappelytter);
      cancelButton.addActionListener(knappelytter);

      /* Definerer akselerasjonstast til Avbryt-knappen */
    KeyStroke escapeTasten = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
      InputMap tastekart =
             cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      tastekart.put(escapeTasten, "Avbryt"); // knytter en kommando til escape-tasten
      ActionMap aksjonskart = cancelButton.getActionMap();
      aksjonskart.put("Avbryt", knappelytter); // definerer en aksjon
    }
  }

  /*
   * Privat klasse som beskriver hva som skal skje når brukeren trykker OK og Avbryt.
   * Metoden put() i klassen ActionMap (se over) krever Action som andre
   * argument. Action er et sub-interface til ActionListener. Interfacet
   * inneholder mange metoder; klassen AbstractAction implementerer
   * disse metodene, og vi kan bruke den som en adapterklasse.
   */
  private class Kommandolytter extends AbstractAction {
    public void actionPerformed(ActionEvent hendelse) {
      String kommando = hendelse.getActionCommand();
      if (kommando.equals("OK")){
        if (okData()) {  // skal bare lukke vinduet dersom ok data
          ok = true;
          setVisible(false);
        }
      } else {  // brukeren har trykket på Avbryt, lukker vinduet
        ok = false;
        setVisible(false);
      }
    }
  }

  /*
   * Privat klasse som beskriver hva som skal skje når brukeren prøver å lukke
   * vinduet. Vi spør vi om eventuelle data skal lagres. Hvis ja, blir de lagret
   * dersom okData() returnerer true.
   */
  private class Vinduslytter extends WindowAdapter {
    public void windowClosing(WindowEvent hendelse) {
      int svar = showConfirmDialog(ParentWindow.this,
                 "Skal eventuelle registrerte data lagres? ",
                 "Lukking av dialogvindu", YES_NO_OPTION);
      if (svar == YES_OPTION) {
        if (okData()) {  // skal bare lukke vinduet dersom ok data
          ok = true;
          setVisible(false);
        }
      } else {  // data skal ikke lagres, lukker vinduet
        ok = false;
        setVisible(false);
      }
    }
  }
}