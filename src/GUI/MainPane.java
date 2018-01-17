/*
 *       copyright (c)  2018 Raffaele Mignone
 *
 *        This file is part of D Client
 *
 *        D Client is free software: you can redistribute it and/or modify
 *        it under the terms of the GNU General Public License as published by
 *        the Free Software Foundation, either version 3 of the License, or
 *        (at your option) any later version.
 *
 *        D Client is distributed in the hope that it will be useful,
 *        but WITHOUT ANY WARRANTY; without even the implied warranty of
 *        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *        GNU General Public License for more details.
 *
 *        You should have received a copy of the GNU General Public License
 *        along with D Client.  If not, see <http://www.gnu.org/licenses/>.
 */

package GUI;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/01/18
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.FlashMob;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import javax.swing.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainPane {
    private JPanel mainPane;
    private JButton clearButton;
    private JButton okButton;
    private JTextField endTF;
    private JTextField startTF;
    private JPanel formPane;
    private JPanel buttonPane;
    private JLabel name;
    private JTextField nameTF;
    private JLabel description;
    private JTextField descriptionTF;
    private JLabel start;
    private JLabel end;
    private JTextField pathTF;
    private JButton chooseButton;
    private JPanel coverPane;
    private JLabel coverPath;
    private JTextField userNameTF;
    private JPasswordField passwordTF;
    private JLabel userName;
    private JLabel password;

    public MainPane() {
        clearButton.addActionListener(actionEvent -> {
            nameTF.setText("");
            descriptionTF.setText("");
            startTF.setText("");
            endTF.setText("");
            pathTF.setText("");
            userNameTF.setText("");
            passwordTF.setText("");
        });

        chooseButton.addActionListener(actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            pathTF.setText(fileChooser.getSelectedFile().getAbsolutePath());
        });

        okButton.addActionListener(actionEvent -> {
            int status = 202;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String name = nameTF.getText();

            try {
                FlashMob fm = new FlashMob(nameTF.getText(), sdf.parse(startTF.getText()),
                        sdf.parse(endTF.getText()), descriptionTF.getText());

                ClientResource crFlashMob = new ClientResource("http://localhost:8182/"+name);
                crFlashMob.setChallengeResponse(ChallengeScheme.HTTP_BASIC, userNameTF.getText(), passwordTF.getText());
                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
                crFlashMob.put(gson.toJson(fm, FlashMob.class));

                FileRepresentation payload = new FileRepresentation(new File(pathTF.getText()), MediaType.IMAGE_ALL);
                ClientResource crCover = new ClientResource("http://localhost:8182/"+name+"/photo/cover");
                crCover.setChallengeResponse(ChallengeScheme.HTTP_BASIC, userNameTF.getText(), passwordTF.getText());
                crCover.post(payload);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Date iniserite in un formato non riconusciuto");
                startTF.setText("");
                endTF.setText("");
            } catch (ResourceException e){
                status = e.getStatus().getCode();
            }

            if(status == 202)
                JOptionPane.showMessageDialog(null, "Evento creato con successo");
            else if(status == 403)
                JOptionPane.showMessageDialog(null, "User name o password sbagliata");
            else if(status == 404)
                JOptionPane.showMessageDialog(null, "Devi specificare un nome per l'evento");
            else if(status == 409)
                JOptionPane.showMessageDialog(null, "L'evento già esiste");
        });
    }

    public void startFrame(){
        JFrame frame = new JFrame("D client");
        frame.setContentPane(new MainPane().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

